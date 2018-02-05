package app.positiveculture.com.user.screen.userbankdetail;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.agent.dto.BankDetail;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The UserBankDetail Presenter
 */
public class UserBankDetailPresenter extends Presenter<UserBankDetailContract.View, UserBankDetailContract.Interactor>
        implements UserBankDetailContract.Presenter {

  private BankDetail mBankDetail;

  private OnSaveBankDetailListener mOnSaveBankDetailListener;

  public UserBankDetailPresenter setOnSaveBankDetailListener(OnSaveBankDetailListener mOnSaveBankDetailListener) {
    this.mOnSaveBankDetailListener = mOnSaveBankDetailListener;
    return this;
  }

  public UserBankDetailPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public UserBankDetailContract.View onCreateView() {
    return UserBankDetailFragment.getInstance();
  }

  @Override
  public void start() {
    if (mBankDetail != null) {
      mView.bindView(mBankDetail);
    }
  }

  @Override
  public void saveBankDetail(BankDetail mBankDetail) {
    mView.showProgress();
    mInteractor.saveBankDetails(mBankDetail, new CommonCallback<User>(getViewContext()) {
      @Override
      public void onSuccess(User data) {
        super.onSuccess(data);
        mView.hideProgress();
        if (mOnSaveBankDetailListener != null) {
          mOnSaveBankDetailListener.onSaveBankDetailSuccessful();
        }
        back();
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
        mView.hideProgress();
      }
    });
  }

  @Override
  public UserBankDetailContract.Interactor onCreateInteractor() {
    return new UserBankDetailInteractor(this);
  }

  public UserBankDetailPresenter setBankDetail(BankDetail mBankDetail) {
    this.mBankDetail = mBankDetail;
    return this;
  }

  public interface OnSaveBankDetailListener {
    void onSaveBankDetailSuccessful();
  }
}
