package app.positiveculture.com.agent.screen.seller.conveyancing;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

/**
 * The Conveyancing Presenter
 */
public class ConveyancingPresenter extends Presenter<ConveyancingContract.View, ConveyancingContract.Interactor>
        implements ConveyancingContract.Presenter {

  public ConveyancingPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public ConveyancingContract.View onCreateView() {
    return ConveyancingFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public ConveyancingContract.Interactor onCreateInteractor() {
    return new ConveyancingInteractor(this);
  }
}
