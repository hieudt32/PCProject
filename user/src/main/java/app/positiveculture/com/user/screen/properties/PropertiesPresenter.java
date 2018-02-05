package app.positiveculture.com.user.screen.properties;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.PropertiesCountDTO;
import app.positiveculture.com.user.R;
import app.positiveculture.com.user.screen.buying.BuyingPresenter;
import app.positiveculture.com.user.screen.complete.CompletePresenter;
import app.positiveculture.com.user.screen.properties.adapter.PropertiesPagerAdapter;
import app.positiveculture.com.user.screen.selling.SellingPresenter;

/**
 * The Properties Presenter
 */
public class PropertiesPresenter extends Presenter<PropertiesContract.View, PropertiesContract.Interactor>
        implements PropertiesContract.Presenter {

  private PropertiesPagerAdapter propertiesPagerAdapter;

  public PropertiesPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public PropertiesContract.View onCreateView() {
    return PropertiesFragment.getInstance();
  }

  @Override
  public void start() {
    getPropertiesCount();
  }

  private void getPropertiesCount() {
    mView.showProgress();
    mInteractor.getPropertiesCount(new CommonCallback<PropertiesCountDTO>(getViewContext()) {
      @Override
      public void onSuccess(PropertiesCountDTO data) {
        super.onSuccess(data);
        if (data.getmTotalPropertiesCount() > 0) {
          mView.showEmptyProperty();
        } else {
          mView.setupTabLayout();
        }
      }
    });
  }

  @Override
  public PropertiesContract.Interactor onCreateInteractor() {
    return new PropertiesInteractor(this);
  }

  @Override
  public PropertiesPagerAdapter getViewPageAdapter() {
    propertiesPagerAdapter = new PropertiesPagerAdapter(mView.getFragmentManager());
    propertiesPagerAdapter.addFragment(new SellingPresenter(mContainerView).getFragment(), getViewContext().getString(R.string.selling));
    propertiesPagerAdapter.addFragment(new BuyingPresenter(mContainerView).getFragment(), getViewContext().getString(R.string.buying));
    propertiesPagerAdapter.addFragment(new CompletePresenter(mContainerView).getFragment(), getViewContext().getString(R.string.completed));
    return propertiesPagerAdapter;
  }
}
