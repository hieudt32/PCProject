package app.positiveculture.com.agent.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gemvietnam.utils.StringUtils;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ItemSummaryCustom
 * Created by hieudt on 8/22/2017.
 */

public class ItemSummaryCustom extends LinearLayout {

  @BindView(R2.id.title_summary_item_tv)
  TextView mTitle;
  @BindView(R2.id.content_summary_item_tv)
  TextView mContent;
  @BindView(R2.id.next_summary_iv)
  ImageView mFirstIv;
  @BindView(R2.id.summary_second_iv)
  ImageView mSecondIv;
  @BindView(R2.id.summary_status_iv)
  ImageView mStatusIv;
  OnClickButtonNextItem mClickButtonNextItem;

  @OnClick(R2.id.item_summary)
  void onNextClick() {
    if (mClickButtonNextItem != null) {
      mClickButtonNextItem.onNextButtonClick();
    }
  }

  public void setClickButtonNextItem(OnClickButtonNextItem clickButtonNextItem) {
    this.mClickButtonNextItem = clickButtonNextItem;
  }

  public ItemSummaryCustom(Context context) {
    super(context);
    init(null, 0);
  }

  public ItemSummaryCustom(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(attrs, 0);
  }

  public ItemSummaryCustom(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(attrs, defStyleAttr);
  }

  private void init(AttributeSet attrs, int defStyleAttr) {
    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.item_summary, this, true);
    ButterKnife.bind(this);
    final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomItemSummary, defStyleAttr, 0);
    String sTitle = a.getString(R.styleable.CustomItemSummary_title_item_summary);
    String sContent = a.getString(R.styleable.CustomItemSummary_content_item_summary);
    int textColor = a.getInteger(R.styleable.CustomItemSummary_text_color_item_summary, 0);
    boolean contentIsBold = a.getBoolean(R.styleable.CustomItemSummary_content_item_bold, false);
    if (!StringUtils.isEmpty(sTitle)) mTitle.setText(sTitle);
    if (!StringUtils.isEmpty(sContent)) mContent.setText(sContent);
    if (contentIsBold) {
      mContent.setTypeface(null, Typeface.BOLD);
    }
    if (textColor != 0) mContent.setTextColor(textColor);
    if (a.hasValue(R.styleable.CustomItemSummary_icon_next_summary)) {
      mFirstIv.setVisibility(VISIBLE);
      mFirstIv.setImageDrawable(a.getDrawable(R.styleable.CustomItemSummary_icon_next_summary));
    } else {
      mFirstIv.setVisibility(GONE);
    }
    if (a.hasValue(R.styleable.CustomItemSummary_second_icon)) {
      mSecondIv.setVisibility(VISIBLE);
      mSecondIv.setImageDrawable(a.getDrawable(R.styleable.CustomItemSummary_second_icon));
    }
    if (a.hasValue(R.styleable.CustomItemSummary_icon_status)) {
      mFirstIv.setVisibility(GONE);
      mStatusIv.setVisibility(VISIBLE);
      mStatusIv.setImageDrawable(a.getDrawable(R.styleable.CustomItemSummary_icon_status));
    }
    a.recycle();
  }

  public void setTitle(String title) {
    mTitle.setText(title);
  }

  public void setContent(String content) {
    mContent.setText(content);
  }

  public String getTitle() {
    return mTitle.getText().toString();
  }

  public String getContent() {
    return mContent.getText().toString();
  }

  public ImageView getFirstImageView() {
    return mFirstIv;
  }

  public ImageView getSecondImageView() {
    return mSecondIv;
  }

  public ImageView getStatusImageView() {
    return mStatusIv;
  }

  public interface OnClickButtonNextItem {
    void onNextButtonClick();
  }
}
