package app.positiveculture.com.agent.screen.properties;

import android.support.v4.app.Fragment;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.utils.StringUtils;

import org.greenrobot.eventbus.Subscribe;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.event.ListingCreatedEvent;
import app.positiveculture.com.agent.screen.properties.buyer.BuyerPresenter;
import app.positiveculture.com.agent.screen.properties.complete.CompletePresenter;
import app.positiveculture.com.agent.screen.properties.property.PropertyPresenter;
import app.positiveculture.com.agent.screen.searchproperty.SearchPropertyPresenter;
import app.positiveculture.com.agent.screen.selectowners.SelectOwnersPresenter;
import app.positiveculture.com.agent.screen.seller.SellerPresenter;
import app.positiveculture.com.agent.screen.verifyaccount.VerifyAccountPresenter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.PropertiesCountDTO;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.ErrorDTO;

/**
 * The Properties Presenter
 */
public class PropertiesPresenter extends Presenter<PropertiesContract.View, PropertiesContract.Interactor>
        implements PropertiesContract.Presenter {

  private PropertiesPagerAdapter adapterProperty;
  private PropertiesPagerAdapter adapterPayingClient;
  private AgentDTO mAgent;

  public PropertiesPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public PropertiesContract.View onCreateView() {
    return PropertiesFragment.getInstance();
  }

  @Override
  public void start() {
    checkListProperties();
  }

  private void checkListProperties() {
    mView.showProgress();
    mInteractor.checkListProperties(new CommonCallback<PropertiesCountDTO>(getViewContext()) {
      @Override
      public void onSuccess(PropertiesCountDTO data) {
        super.onSuccess(data);
        if (data.getmTotalPropertiesCount() > 0) {
          mView.showPropertiesEmptyList();
          mView.hideProgress();
        } else {
          mView.setupTabLayout();
          mView.hideProgress();
        }
      }
    });
  }

  @Override
  public PropertiesContract.Interactor onCreateInteractor() {
    return new PropertiesInteractor(this);
  }

  @Override
  public Fragment setPageFragment(int i) {
    Presenter presenter = null;
    switch (i) {
      case 0:
        presenter = new PropertyPresenter(mContainerView);
        break;
      case 1:
        presenter = new SellerPresenter(mContainerView);
        break;
      case 2:
        presenter = new BuyerPresenter(mContainerView);
        break;
      case 3:
        presenter = new CompletePresenter(mContainerView);
        break;
    }
    return presenter.getFragment();
  }

  @Override
  public PropertiesPagerAdapter showPayingClient() {
    if (adapterPayingClient == null) {
      adapterPayingClient = new PropertiesPagerAdapter(mView.getFragmentManager());
      adapterPayingClient.addFragment(new SellerPresenter(mContainerView).getFragment(), getViewContext().getString(R.string.tab_seller));
      adapterPayingClient.addFragment(new BuyerPresenter(mContainerView).getFragment(), getViewContext().getString(R.string.tab_Buyer));
    }
    return adapterPayingClient;
  }

  @Override
  public void goToSelectOwners() {
    mView.showProgress();
    mInteractor.getProfileAgent(new CommonCallback<AgentDTO>(getViewContext()) {
      @Override
      public void onSuccess(AgentDTO data) {
        super.onSuccess(data);
        mView.hideProgress();
        mAgent = data;
        PrefWrapper.saveAgent(data);
        if (mAgent != null) {
          if (mAgent.getStatus() != null) {
            if (!mAgent.getStatus().equals("unverified") && !StringUtils.isEmpty(mAgent.getNric())) {
              new SelectOwnersPresenter(mContainerView).pushView();
            } else {
              new VerifyAccountPresenter(mContainerView).presentView();
            }
          }
        }
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
      }
    });
  }

  @Override
  public void goToSearchProperty() {
    new SearchPropertyPresenter(mContainerView).pushView();
  }

  @Override
  public PropertiesPagerAdapter showProperties() {
    if (adapterProperty == null) {
      adapterProperty = new PropertiesPagerAdapter(mView.getFragmentManager());
      adapterProperty.addFragment(new PropertyPresenter(mContainerView).getFragment(), getViewContext().getString(R.string.tab_property));
      adapterProperty.addFragment(new SellerPresenter(mContainerView).getFragment(), getViewContext().getString(R.string.tab_seller));
      adapterProperty.addFragment(new BuyerPresenter(mContainerView).getFragment(), getViewContext().getString(R.string.tab_Buyer));
      adapterProperty.addFragment(new CompletePresenter(mContainerView).getFragment(), getViewContext().getString(R.string.tab_completed));
    }
    return adapterProperty;
  }

  @Subscribe
  public void onShowNoticeEvent(ListingCreatedEvent createdEvent) {
    mView.listingCreated();
  }
}
