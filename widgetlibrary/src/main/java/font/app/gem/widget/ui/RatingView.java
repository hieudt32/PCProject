package font.app.gem.widget.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import font.app.gem.widgetlibrary.R;


/**
 * Custom Rating view
 * Created by NEO on 11/9/2016.
 */

public class RatingView extends FrameLayout {
  private ImageView mImageView1, mImageView2, mImageView3;
  private int mImageResId = R.drawable.star_0;

  public RatingView(Context context) {
    super(context);
    init();
  }

  public RatingView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  private void init() {
    View view = LayoutInflater.from(getContext()).inflate(R.layout.rating_view, null);
    mImageView1 = (ImageView) view.findViewById(R.id.rating_iv_1);
    mImageView2 = (ImageView) view.findViewById(R.id.rating_iv_2);
    mImageView3 = (ImageView) view.findViewById(R.id.rating_iv_3);

    addView(view);
  }

  public void setRatingLevel(int ratingLevel) {
    switch (ratingLevel) {
      case 1:
        mImageResId = R.drawable.star_1;
        break;
      case 2:
        mImageResId = R.drawable.star_2;
        break;
      case 3:
        mImageResId = R.drawable.star_3;
        break;
    }
  }

  public void setRatingNumber(int ratingNumber) {

    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
    );
    params.setMargins(0, 0, 0, 0);

    int margin = (int) getResources().getDimension(R.dimen.padding_small);

    LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
    );
    params1.setMargins(0, 0, margin, 0);

    switch (ratingNumber) {
      case 1:
        mImageView1.setImageResource(mImageResId);
        mImageView2.setImageDrawable(null);
        mImageView3.setImageDrawable(null);

        mImageView1.setLayoutParams(params);
//        mImageView3.setLayoutParams(params);
        break;
      case 2:
        mImageView1.setImageResource(mImageResId);
        mImageView2.setImageResource(mImageResId);
        mImageView3.setImageDrawable(null);

        mImageView1.setLayoutParams(params1);
        mImageView2.setLayoutParams(params);
        break;
      case 3:
        mImageView1.setImageResource(mImageResId);
        mImageView2.setImageResource(mImageResId);
        mImageView3.setImageResource(mImageResId);

        mImageView1.setLayoutParams(params1);
        mImageView2.setLayoutParams(params1);
        break;
      default:
        mImageView1.setImageDrawable(null);
        mImageView2.setImageDrawable(null);
        mImageView3.setImageDrawable(null);

        break;
    }
  }
}
