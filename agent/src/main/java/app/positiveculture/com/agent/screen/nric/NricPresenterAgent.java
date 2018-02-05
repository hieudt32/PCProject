package app.positiveculture.com.agent.screen.nric;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.utils.StringUtils;

import app.positiveculture.com.agent.ObjectType;
import app.positiveculture.com.agent.screen.bankdetails.BankDetailsPresenterAgent;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The Nric Presenter
 */
public class NricPresenterAgent extends Presenter<NricContractAgent.View, NricContractAgent.Interactor>
        implements NricContractAgent.Presenter {

  private OnSaveNric mOnSaveNric;
  private String mNricNumber;

  public NricPresenterAgent(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public NricContractAgent.View onCreateView() {
    return NricFragmentAgent.getInstance();
  }

  @Override
  public void start() {
    mView.setupDataForEditProfile(mNricNumber);
  }

  @Override
  public void saveEditedNricNumber(final String nric) {
    mView.showProgress();
    mInteractor.saveNric(nric, new CommonCallback<User>(getViewContext()) {
      @Override
      public void onSuccess(User data) {
        super.onSuccess(data);
        mView.hideProgress();
        if (mOnSaveNric != null) {
          mOnSaveNric.onSaveNricClick(nric);
        }
        back();
      }

      @Override
      public void onError(String errorCode, ErrorDTO errorMessage) {
        super.onError(errorCode, errorMessage);
        mView.hideProgress();
      }
    });
  }

  public NricPresenterAgent setOnSaveNric(OnSaveNric onSaveNric) {
    this.mOnSaveNric = onSaveNric;
    return this;
  }

  @Override
  public NricContractAgent.Interactor onCreateInteractor() {
    return new NricInteractorAgent(this);
  }

  public NricPresenterAgent setNricNumber(String nric) {
    this.mNricNumber = nric;
    return this;
  }

  public interface OnSaveNric {
    void onSaveNricClick(String nric);
  }

}
