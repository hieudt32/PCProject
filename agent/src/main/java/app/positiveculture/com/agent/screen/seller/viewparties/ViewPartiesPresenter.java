package app.positiveculture.com.agent.screen.seller.viewparties;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.screen.agentprofile.AgentProfilePresenter;
import app.positiveculture.com.agent.screen.clientlist.clientdetails.ClientDetailsPresenter;
import app.positiveculture.com.agent.screen.clientlist.clientprofile.ClientProfilePresenter;
import app.positiveculture.com.agent.screen.selectowners.SelectOwnersPresenter;
import app.positiveculture.com.data.enumdata.OTPStatus;
import app.positiveculture.com.data.enumdata.StatusAndOrNominee;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The ViewParties Presenter
 */
public class ViewPartiesPresenter extends Presenter<ViewPartiesContract.View, ViewPartiesContract.Interactor>
        implements ViewPartiesContract.Presenter, ViewPartiesAdapter.OnItemPartiesClickListener, SelectOwnersPresenter.OnReassignClientListener {

  private ViewPartiesAdapter mOwnerAdapter;
  private ViewPartiesAdapter mBuyerAdapter;
  private List<User> mListOwner;
  private List<User> mListBuyer;
  private TypeProcess mTypeProcess;
  private OtpDTO mOTP;
  private OnReassignClientSuccessfulListener mOnReassignClientSuccessfulListener;

  public ViewPartiesPresenter setOnReassignClientSuccessfulListener(OnReassignClientSuccessfulListener mOnReassignClientSuccessfulListener) {
    this.mOnReassignClientSuccessfulListener = mOnReassignClientSuccessfulListener;
    return this;
  }

  public ViewPartiesPresenter setListBuyer(List<User> mListBuyer) {
    this.mListBuyer = mListBuyer;
    return this;
  }

  public ViewPartiesPresenter setListOwner(List<User> mListOwner) {
    this.mListOwner = mListOwner;
    return this;
  }

  public ViewPartiesPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public ViewPartiesContract.View onCreateView() {
    return ViewPartiesFragment.getInstance();
  }

  @Override
  public void start() {
    User mUser = PrefWrapper.getUser();
    if (mListOwner != null && !mListOwner.isEmpty()) {
      mOwnerAdapter = new ViewPartiesAdapter(mListOwner, true, mUser);
      mOwnerAdapter.setOnItemPartiesClickListener(this);
      mView.bindOwnerSide(mOwnerAdapter);
    }

    if (mListBuyer != null && !mListBuyer.isEmpty()) {
      mBuyerAdapter = new ViewPartiesAdapter(mListBuyer, false, mUser);
      mBuyerAdapter.setOnItemPartiesClickListener(this);
      mView.bindBuyerSide(mBuyerAdapter);
    }

    if (mTypeProcess == TypeProcess.Buying && mOTP.getAndOrNominee() == StatusAndOrNominee.on) {
      if (mOTP.getStatus() == OTPStatus.buyers_assigned || mOTP.getStatus() == OTPStatus.buyers_assigned_hardcopy) {
        mView.showButtonReassign();
      }
    }
  }

  @Override
  public ViewPartiesContract.Interactor onCreateInteractor() {
    return new ViewPartiesInteractor(this);
  }

  @Override
  public void onItemPartiesClick(User user) {
    AgentDTO agentDTO = PrefWrapper.getAgent();
    if (agentDTO == null) return;
    if (user instanceof AgentDTO) {
      if (user.getId() != agentDTO.getId()) {
        new AgentProfilePresenter(mContainerView)
                .setAgentProfile((AgentDTO) user).pushView();
      }
    } else {
      if (checkAgent(agentDTO, mListOwner)) {
        if (mListOwner.contains(user)) {
          new ClientDetailsPresenter(mContainerView).setClient((MemberDTO) user).pushView();
        }
      } else {
        if (mListBuyer.contains(user)) {
          new ClientDetailsPresenter(mContainerView).setClient((MemberDTO) user).pushView();
        }
      }
    }
  }

  private boolean checkAgent(AgentDTO mAgentDTO, List<User> userList) {
    for (User user : userList) {
      if (mAgentDTO.getId() == user.getId()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void reassignOTP() {
    new SelectOwnersPresenter(mContainerView)
            .setOnReassignClientListener(this)
            .setOTP(mOTP)
            .pushView();
  }

  public ViewPartiesPresenter setTypeProcess(TypeProcess mTypeProcess, OtpDTO mOTP) {
    this.mTypeProcess = mTypeProcess;
    this.mOTP = mOTP;
    return this;
  }

  @Override
  public void onReassignClientSuccess(OtpDTO otpDTO) {
    this.mOTP = otpDTO;
    if (mOnReassignClientSuccessfulListener != null) {
      mOnReassignClientSuccessfulListener.onReassignSuccess(mOTP);
    }
    List<User> listOwners = new ArrayList<>();
    if (mOTP.getProperty() != null) {
      if (mOTP.getProperty().getPropertySeller() != null) {
        listOwners.addAll(mOTP.getProperty().getPropertySeller());
      }
      if (mOTP.getProperty().getmAgent() != null) {
        listOwners.add(mOTP.getProperty().getmAgent());
      }
    }

    List<User> listBuyer = new ArrayList<>();
    if (mOTP.getPropertyBuyer() != null && !mOTP.getPropertyBuyer().isEmpty()) {
      listBuyer.addAll(mOTP.getPropertyBuyer());
      if (mOTP.getBuyerAgent() != null) {
        listBuyer.add(mOTP.getBuyerAgent());
      }
    }
    mListBuyer = listBuyer;
    mListOwner = listOwners;
    start();
  }

  public interface OnReassignClientSuccessfulListener {
    void onReassignSuccess(OtpDTO otpDTO);
  }
}
