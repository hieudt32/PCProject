package app.positiveculture.com.user.screen.newloan;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.user.R;
import app.positiveculture.com.user.R2;
import butterknife.OnClick;

/**
 * The NewLoan Fragment
 */
public class NewLoanFragment extends ViewFragment<NewLoanContract.Presenter> implements NewLoanContract.View {

    public static NewLoanFragment getInstance() {
        return new NewLoanFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_loan;
    }

    @OnClick(R2.id.left_icon_iv)
    public void back(){
        mPresenter.back();
    }
}
