package app.positiveculture.com.user.screen.refinance;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.user.R;
import app.positiveculture.com.user.R2;
import butterknife.OnClick;

/**
 * The Refinance Fragment
 */
public class RefinanceFragment extends ViewFragment<RefinanceContract.Presenter> implements RefinanceContract.View {

    public static RefinanceFragment getInstance() {
        return new RefinanceFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_refinance;
    }

    @OnClick(R2.id.left_icon_iv)
    public void back(){
        mPresenter.back();
    }
}
