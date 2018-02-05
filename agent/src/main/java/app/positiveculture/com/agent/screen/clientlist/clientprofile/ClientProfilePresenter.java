package app.positiveculture.com.agent.screen.clientlist.clientprofile;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.screen.clientlist.clientdetails.ClientDetailsPresenter;
import app.positiveculture.com.agent.screen.clientlist.clientprofile.clientbuying.ClientBuyingPresenter;
import app.positiveculture.com.agent.screen.clientlist.clientprofile.clientcompleted.ClientCompletedPresenter;
import app.positiveculture.com.agent.screen.clientlist.clientprofile.clientselling.ClientSellingPresenter;
import app.positiveculture.com.agent.screen.properties.PropertiesPagerAdapter;
import app.positiveculture.com.data.response.dto.MemberDTO;

/**
 * The ClientProfile Presenter
 */
public class ClientProfilePresenter extends Presenter<ClientProfileContract.View, ClientProfileContract.Interactor>
        implements ClientProfileContract.Presenter, ClientDetailsPresenter.OnNoteMemberListener {

  private PropertiesPagerAdapter mPagerAdapter;
  private MemberDTO mClient;
  private OnNoteClientClientProfileListener mOnNoteClientClientProfileListener;

  public ClientProfilePresenter setOnNoteClientClientProfileListener(OnNoteClientClientProfileListener mOnNoteClientClientProfileListener) {
    this.mOnNoteClientClientProfileListener = mOnNoteClientClientProfileListener;
    return this;
  }

  public ClientProfilePresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public ClientProfileContract.View onCreateView() {
    return ClientProfileFragment.getInstance();
  }

  @Override
  public void start() {
    if (mClient != null) {
      mView.setupData(mClient);
    }
  }

  public ClientProfilePresenter setClient(MemberDTO client) {
    mClient = client;
    return this;
  }

  @Override
  public ClientProfileContract.Interactor onCreateInteractor() {
    return new ClientProfileInteractor(this);
  }

  @Override
  public void goToClientDetails() {
    new ClientDetailsPresenter(mContainerView)
            .setClient(mClient)
            .setOnNoteMemberListener(this)
            .pushView();
  }

  @Override
  public PropertiesPagerAdapter setupPager() {
    mPagerAdapter = new PropertiesPagerAdapter(mView.getFragmentManager());
    mPagerAdapter.addFragment(new ClientSellingPresenter(mContainerView).setClient(mClient).getFragment(), getViewContext().getString(R.string.selling));
    mPagerAdapter.addFragment(new ClientBuyingPresenter(mContainerView).setClient(mClient).getFragment(), getViewContext().getString(R.string.buying));
    mPagerAdapter.addFragment(new ClientCompletedPresenter(mContainerView).setClient(mClient).getFragment(), getViewContext().getString(R.string.tab_completed));
    return mPagerAdapter;
  }

  @Override
  public void onNoteSuccess(MemberDTO client) {
    this.mClient = client;
    if (mOnNoteClientClientProfileListener != null) {
      mOnNoteClientClientProfileListener.onNoteEdited();
    }
  }

  public interface OnNoteClientClientProfileListener {
    void onNoteEdited();
  }
}
