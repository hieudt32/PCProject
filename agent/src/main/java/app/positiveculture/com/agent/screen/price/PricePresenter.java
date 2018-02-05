package app.positiveculture.com.agent.screen.price;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.agent.screen.description.DescriptionPresenter;
import app.positiveculture.com.data.response.dto.CreatePropertyDTO;

/**
 * The Price Presenter
 */
public class PricePresenter extends Presenter<PriceContract.View, PriceContract.Interactor>
        implements PriceContract.Presenter {

  private CreatePropertyDTO createPropertyDTO;

  private OnGetPriceListener mOnGetPriceListener;

  public PricePresenter(ContainerView containerView) {
    super(containerView);
  }

  public PricePresenter setmOnGetPriceListener(OnGetPriceListener mOnGetPriceListener) {
    this.mOnGetPriceListener = mOnGetPriceListener;
    return this;
  }

  @Override
  public PriceContract.View onCreateView() {
    return PriceFragment.getInstance();
  }

  @Override
  public void start() {
    double price = createPropertyDTO.getmPrice();
    double valuation = createPropertyDTO.getmValuation();
    if (price != 0 && valuation != 0 && mOnGetPriceListener != null) {
      mView.setPriceFromSummary(price, valuation);
    }
  }

  @Override
  public void goBack() {
    mContainerView.back();
  }

  @Override
  public void goBackToSummary(double price, double valuation) {
    if (mOnGetPriceListener != null) {
      createPropertyDTO.setmValuation(valuation);
      createPropertyDTO.setmPrice(price);
      if (mOnGetPriceListener != null) mOnGetPriceListener.onGetPriceSuccessful(createPropertyDTO);
      mContainerView.back();
    }
  }

  @Override
  public void goToDescriptionScreen(double price, double valuation) {
    createPropertyDTO.setmPrice(price);
    createPropertyDTO.setmValuation(valuation);
    new DescriptionPresenter(mContainerView)
            .setCreatePropertyDTO(createPropertyDTO)
            .pushView();
  }

  @Override
  public PriceContract.Interactor onCreateInteractor() {
    return new PriceInteractor(this);
  }

  public PricePresenter setCreatePropertyDTO(CreatePropertyDTO createPropertyDTO) {
    this.createPropertyDTO = createPropertyDTO;
    return this;
  }

  public interface OnGetPriceListener {
    void onGetPriceSuccessful(CreatePropertyDTO createPropertyDTO);
  }
}
