package app.positiveculture.com.agent.screen.seller.congratulation;

import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.utils.ViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * The Congratulation Fragment
 */
public class CongratulationFragment extends ViewFragment<CongratulationContract.Presenter> implements CongratulationContract.View {

  @BindView(R2.id.thumb_complete_process_iv)
  SimpleDraweeView mThumbProperty;
  @BindView(R2.id.title_message_complete_tv)
  TextView mTitleMessageTv;
  @BindView(R2.id.content_message_complete_tv)
  TextView mContentMessageTv;

  @OnClick(R2.id.close_complete_process)
  void doCloseClick() {
    mPresenter.back();
  }

  public static CongratulationFragment getInstance() {
    return new CongratulationFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_congratulation;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mThumbProperty.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override
      public void onGlobalLayout() {
        mThumbProperty.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mThumbProperty.getLayoutParams();
        if (mThumbProperty.getHeight() > mThumbProperty.getWidth()) {
          params.height = mThumbProperty.getWidth();
        } else {
          params.width = mThumbProperty.getHeight();
        }
        mThumbProperty.setLayoutParams(params);
      }
    });

  }

  @Override
  public void bindView(String mThumbUrl) {
    ViewUtils.loadAvatarWithFresco(mThumbProperty, mThumbUrl);
  }
}
