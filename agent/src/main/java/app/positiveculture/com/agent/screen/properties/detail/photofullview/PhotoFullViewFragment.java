package app.positiveculture.com.agent.screen.properties.detail.photofullview;

import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * The PhotoFullView Fragment
 */
public class PhotoFullViewFragment extends ViewFragment<PhotoFullViewContract.Presenter> implements PhotoFullViewContract.View {

  @BindView(R2.id.photo_full_view_vp)
  ViewPager mViewPager;
  @BindView(R2.id.count_caption_tv)
  TextView mCountCaptionTv;

  @OnClick(R2.id.close_photo_full_view_iv)
  void onCloseClick() {
    mPresenter.back();
  }

  private String sizeCount;

  public static PhotoFullViewFragment getInstance() {
    return new PhotoFullViewFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_photo_full_view;
  }

  @Override
  public void initLayout() {
    super.initLayout();

    mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {
        String text = position + 1 + sizeCount;
        mCountCaptionTv.setText(text);
      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });
  }

  @Override
  public void bindPhoto(PhotoFullViewAdapter mAdapterPhoto, int size) {
    mViewPager.setAdapter(mAdapterPhoto);
    sizeCount = "/" + size + " " + getString(R.string._caption);
    mCountCaptionTv.setText((mViewPager.getCurrentItem()+1) + sizeCount);
  }
}
