package app.positiveculture.com.agent.screen.selectowners;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.Constant;
import app.positiveculture.com.agent.screen.clientlist.createnewclient.CreateNewClientPresenter;
import app.positiveculture.com.agent.screen.clientlist.existinguser.ExistingUserPresenter;
import app.positiveculture.com.agent.screen.clientlist.newuser.NewUserPresenter;
import app.positiveculture.com.agent.screen.propertytype.PropertyTypePresenter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.CreatePropertyDTO;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.OtpDTO;

/**
 * The SelectOwners Presenter
 */
public class SelectOwnersPresenter extends Presenter<SelectOwnersContract.View, SelectOwnersContract.Interactor>
        implements SelectOwnersContract.Presenter, SelectOwnersAdapter.OnOwnerClickListener, NewUserPresenter.OnBackToClientListListener, ExistingUserPresenter.OnInvitationSentSuccessful {

  private CreatePropertyDTO createPropertyDTO;
  private List<MemberDTO> mListSearch = new ArrayList<>();
  private List<MemberDTO> mListSelected = new ArrayList<>();
  private SelectOwnersAdapter mAdapter;
  private OnGetOwnersListener mOnGetOwnersListener;
  private final int mLimit = Constant.PAGE_LIMIT;
  private int mOffSet = 0;
  private boolean mAllOwner = false;
  private OnAssignClientListener mOnAssignClientListener;
  private OnReassignClientListener mOnReassignClientListener;
  private OtpDTO mOTP;

  public SelectOwnersPresenter setOnReassignClientListener(OnReassignClientListener mOnReassignClientListener) {
    this.mOnReassignClientListener = mOnReassignClientListener;
    return this;
  }

  public SelectOwnersPresenter setOnAssignClientListener(OnAssignClientListener mOnAssignClientListener) {
    this.mOnAssignClientListener = mOnAssignClientListener;
    return this;
  }

  public SelectOwnersPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public SelectOwnersContract.View onCreateView() {
    return SelectOwnersFragment.getInstance();
  }

  @Override
  public void start() {
    mOffSet = 0;
    mAllOwner = false;
    mListSearch.clear();
    getListOwner(mLimit, mOffSet, null);
  }

  public SelectOwnersPresenter setOnGetOwnersListener(OnGetOwnersListener mOnGetOwnersListener) {
    this.mOnGetOwnersListener = mOnGetOwnersListener;
    return this;
  }

  private void getListOwner(int limit, int offSet, String nameSearch) {
    mInteractor.getListOwners(limit, offSet, nameSearch, new CommonCallback<List<MemberDTO>>(mView.getViewContext()) {
      @Override
      public void onSuccess(List<MemberDTO> data) {
        super.onSuccess(data);
        mListSearch.clear();
        mListSearch = data;
        checkOwnerFromSummary();
        mAdapter = new SelectOwnersAdapter(mListSearch);
        mAdapter.setOwnerClickListener(SelectOwnersPresenter.this);
        mView.bindSearchData(mAdapter);

        if (!mListSelected.isEmpty()) {
          setSelectedFromSummary();
        }

        if (mOnGetOwnersListener != null) {
          mView.bindViewFromSummary();
        }

        if (mOnAssignClientListener != null && mOTP != null) {
          mView.bindViewForAssignClient();
        }

        if (mOnReassignClientListener != null && mOTP != null) {
          mView.bindViewForReassignClient();
        }

        mAdapter.notifyDataSetChanged();
      }

      @Override
      public void onError(String errorCode, ErrorDTO errorMessage) {
        super.onError(errorCode, errorMessage);
      }
    });
  }

  private void checkOwnerFromSummary() {
    if (mOTP == null && createPropertyDTO != null) {
      mListSelected = createPropertyDTO.getmListOwner();
    } else if (mOTP != null) {
      if (mOTP.getPropertyBuyer() != null) {
        mListSelected = mOTP.getPropertyBuyer();
      }
    }

  }

  private void setSelectedFromSummary() {
    for (int i = 0; i < mListSearch.size(); i++) {
      for (MemberDTO memberDTO : mListSelected) {
        if (memberDTO.getId() == mListSearch.get(i).getId()) {
          mListSearch.get(i).setSelected(true);
        }
      }
    }
    mAdapter.notifyDataSetChanged();
    mView.bindDataSelectedOwner(mListSelected);
  }

  @Override
  public void goToPropertyType() {
    if (mOTP != null) {
      if (mOnAssignClientListener != null) {
        assignBuyer();
      } else if (mOnReassignClientListener != null) {
        reassignBuyer();
      }

    } else {
      createPropertyDTO = new CreatePropertyDTO();
      createPropertyDTO.setmListOwner(mListSelected);
      new PropertyTypePresenter(mContainerView)
              .setCreatePropertyDTO(createPropertyDTO)
              .pushView();
    }
  }

  private void reassignBuyer() {
    mView.showProgress();
    mInteractor.reassignedClient(mOTP.getId(), mListSelected, new CommonCallback<OtpDTO>(getViewContext()) {
      @Override
      public void onSuccess(OtpDTO data) {
        super.onSuccess(data);
        mOnReassignClientListener.onReassignClientSuccess(data);
        back();
      }
    });
  }

  private void assignBuyer() {
    mView.showProgress();
    mInteractor.assignClient(mOTP.getId(), mListSelected, new CommonCallback<OtpDTO>(getViewContext()) {
      @Override
      public void onSuccess(OtpDTO data) {
        super.onSuccess(data);
        mOnAssignClientListener.onAssignClientSuccess(data);
        back();
      }
    });
  }

  @Override
  public void onSearchOwner(String name) {
    mOffSet = 0;
    mAllOwner = false;
    getListOwner(mLimit, mOffSet, name);
  }

  @Override
  public void goToCreateNewClient() {
    new CreateNewClientPresenter(mContainerView)
            .setSelectOwnersPresenter(this)
            .pushView();
  }

  @Override
  public void goBack() {
    if (mOnGetOwnersListener != null) {
      createPropertyDTO.setmListOwner(mListSelected);
      mOnGetOwnersListener.onGetOwnersSuccessful(createPropertyDTO);
    }
    mContainerView.back();
  }

  @Override
  public SelectOwnersContract.Interactor onCreateInteractor() {
    return new SelectOwnersInteractor(this);
  }

  public SelectOwnersPresenter setPropertyFromSummary(CreatePropertyDTO createPropertyDTO) {
    this.createPropertyDTO = createPropertyDTO;
    return this;
  }

  @Override
  public void onOwnerClick(int position) {
    if (mListSearch.get(position).isSelected()) {
      mListSearch.get(position).setSelected(false);
      removeObjectInList(mListSearch.get(position), mListSelected);
    } else {
      mListSearch.get(position).setSelected(true);
      mListSelected.add(mListSearch.get(position));
    }

    mAdapter.notifyItemChanged(position);
    mView.bindDataSelectedOwner(mListSelected);
  }

  private void removeObjectInList(MemberDTO mMemberDTO, List<MemberDTO> mListMember) {
    for (MemberDTO memberDTO : mListMember) {
      if (memberDTO.getId() == mMemberDTO.getId()) {
        mListMember.remove(memberDTO);
        break;
      }
    }
  }

  @Override
  public void refreshList(String mNameSearch) {
    mOffSet = 0;
    mAllOwner = false;
    mListSearch.clear();
    getListOwner(mLimit, mOffSet, mNameSearch);
  }

  @Override
  public void loadMoreOwner(String nameSearch) {
    if (mAllOwner) {
      mView.onLoadMoreSuccessful();
      return;
    }
    mOffSet += mLimit;
    mInteractor.getListOwners(mLimit, mOffSet, nameSearch, new CommonCallback<List<MemberDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<MemberDTO> data) {
        super.onSuccess(data);
        if (!data.isEmpty()) {
          int startIndex = mListSearch.size();
          mListSearch.addAll(data);
          if (!mListSelected.isEmpty()) {
            setSelectedFromSummary();
          }
          mAdapter.notifyItemRangeInserted(startIndex, mListSearch.size());
          mView.onLoadMoreSuccessful();
        } else {
          mAllOwner = true;
          mView.onLoadMoreSuccessful();
        }
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
      }
    });
  }

  @Override
  public void onBackToClientList() {
    start();
  }

  @Override
  public void onSentSuccess() {
    start();
  }

  public SelectOwnersPresenter setOTP(OtpDTO mOTP) {
    this.mOTP = mOTP;
    return this;
  }

  public interface OnGetOwnersListener {
    void onGetOwnersSuccessful(CreatePropertyDTO createPropertyDTO);
  }

  public interface OnAssignClientListener {
    void onAssignClientSuccess(OtpDTO otpDTO);
  }

  public interface OnReassignClientListener {
    void onReassignClientSuccess(OtpDTO otpDTO);
  }
}
