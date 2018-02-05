package app.positiveculture.com.user.screen.loan;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.user.R;
import app.positiveculture.com.user.R2;
import butterknife.OnClick;

/**
 * The Loan Fragment
 */
public class LoanFragment extends ViewFragment<LoanContract.Presenter> implements LoanContract.View {

    public static LoanFragment getInstance() {
        return new LoanFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_loan;
    }

    @OnClick(R2.id.left_icon_iv)
    public void back() {
        mPresenter.back();
    }

    @OnClick(R2.id.new_loan_bt)
    public void newLoan(){
        mPresenter.newLoan();
    }

    @OnClick(R2.id.refinance_bt)
    public void newRefinance(){
        mPresenter.newRefinance();
    }

}
