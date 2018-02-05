package app.positiveculture.com.agent.screen.seller.congratulation;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.utils.StringUtils;

/**
 * The Congratulation Presenter
 */
public class CongratulationPresenter extends Presenter<CongratulationContract.View, CongratulationContract.Interactor>
        implements CongratulationContract.Presenter {

  private String mThumbUrl;

  public CongratulationPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public CongratulationContract.View onCreateView() {
    return CongratulationFragment.getInstance();
  }

  @Override
  public void start() {
    if (!StringUtils.isEmpty(mThumbUrl)) {
      mView.bindView(mThumbUrl);
    }
  }

  @Override
  public CongratulationContract.Interactor onCreateInteractor() {
    return new CongratulationInteractor(this);
  }

  public CongratulationPresenter setThumbUrlProperty(String largeUrl) {
    mThumbUrl = largeUrl;
    return this;
  }
}
