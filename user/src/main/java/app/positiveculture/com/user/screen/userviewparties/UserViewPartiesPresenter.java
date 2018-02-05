package app.positiveculture.com.user.screen.userviewparties;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import java.util.List;

import app.positiveculture.com.agent.screen.agentprofile.AgentProfilePresenter;
import app.positiveculture.com.agent.screen.clientlist.clientdetails.ClientDetailsPresenter;
import app.positiveculture.com.agent.screen.seller.viewparties.ViewPartiesAdapter;
import app.positiveculture.com.data.enumdata.OTPStatus;
import app.positiveculture.com.data.enumdata.StatusAndOrNominee;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The UserViewParties Presenter
 */
public class UserViewPartiesPresenter extends Presenter<UserViewPartiesContract.View, UserViewPartiesContract.Interactor>
        implements UserViewPartiesContract.Presenter, ViewPartiesAdapter.OnItemPartiesClickListener {

  private ViewPartiesAdapter mOwnerAdapter;
  private ViewPartiesAdapter mBuyerAdapter;
  private List<User> mListOwner;
  private List<User> mListBuyer;
  private TypeProcess mTypeProcess;
  private OtpDTO mOTP;

  public UserViewPartiesPresenter setListOwner(List<User> mListOwner) {
    this.mListOwner = mListOwner;
    return this;
  }

  public UserViewPartiesPresenter setListBuyer(List<User> mListBuyer) {
    this.mListBuyer = mListBuyer;
    return this;
  }

  public UserViewPartiesPresenter setOTP(OtpDTO mOTP, TypeProcess mTypeProcess) {
    this.mOTP = mOTP;
    this.mTypeProcess = mTypeProcess;
    return this;
  }

  public UserViewPartiesPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public UserViewPartiesContract.View onCreateView() {
    return UserViewPartiesFragment.getInstance();
  }

  @Override
  public void start() {
    User mUser= PrefWrapper.getUser();
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
  }


  @Override
  public UserViewPartiesContract.Interactor onCreateInteractor() {
    return new UserViewPartiesInteractor(this);
  }

  @Override
  public void onItemPartiesClick(User user) {
    MemberDTO mMemberDTO = PrefWrapper.getMember();
    if (mMemberDTO == null) return;
    if (user instanceof AgentDTO) {
      new AgentProfilePresenter(mContainerView)
              .setAgentProfile((AgentDTO) user)
              .pushView();
    } else {
      if (checkUser(mMemberDTO, mListOwner)) {
        if (mListOwner.contains(user) && mMemberDTO.getId() != user.getId()) {
          new ClientDetailsPresenter(mContainerView).setClient((MemberDTO) user).pushView();
        }
      } else {
        if (mListBuyer.contains(user) && mMemberDTO.getId() != user.getId()) {
          new ClientDetailsPresenter(mContainerView).setClient((MemberDTO) user).pushView();
        }
      }
    }
  }

  private boolean checkUser(MemberDTO memberDTO, List<User> userList) {
    for (User user : userList) {
      if (memberDTO.getId() == user.getId()) {
        return true;
      }
    }
    return false;
  }
}
