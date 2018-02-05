package font.app.gem.widget.ui.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.ViewUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import font.app.gem.widgetlibrary.R;

/**
 * dialog confirm purchase
 * Created by hungdn on 1/25/2017.
 */

public class ConfirmPurchaseDialog extends TranslucentDialog{

  private ImageView mPurchaseGiftIv;
  private TextView mPurchaseGiftInforTv;
  private TextView mDialogSubmitTv;
  private OnSubmitPurchaseGift mOnSubmitPurchaseItem;

  private String mImage, mName;
  private int mStageMartId, mQuantity;
  private String mPrice;

  /**
   * Create a new instance dialog
   */
  public static ConfirmPurchaseDialog newInstance(String image, String name, int id, String price, int quantity ) {
    return new ConfirmPurchaseDialog().setPurchaseItem(image, name, id, price, quantity);
  }

  @Override
  protected int getLayoutRes() {
    return R.layout.dialog_confirm_purchase;
  }

  public ConfirmPurchaseDialog setPurchaseItem(String image, String name, int id, String price, int quantity ) {

    this.mImage = image;
    this.mName = name;
    this.mStageMartId = id;
    this.mPrice = price;
    this.mQuantity = quantity;
    return this;
  }

  @Override
  protected void injectViews(Dialog dialog, View v) {
    mPurchaseGiftIv = (ImageView) v.findViewById(R.id.purchase_gift_image_iv);
    mPurchaseGiftInforTv = (TextView) v.findViewById(R.id.purchase_gift_infor_tv);
    mDialogSubmitTv = (TextView) v.findViewById(R.id.dialog_submit_tv);
    v.findViewById(R.id.cancellation_policy_tv).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String url = "http://www.stagemetro.com/payment-cancellation-refund-policies";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
      }
    });
    Glide
            .with(getContext())
            .load(mImage)
            .fitCenter()
            .dontAnimate()
            .into(mPurchaseGiftIv);

    String quantityUnit;
    if (mQuantity > 1) {
      quantityUnit = "units";
    } else {
      quantityUnit = "unit";
    }

    String infor = "Please confirm purchase of " + mName + " (" + mQuantity + " " + quantityUnit + ") " + "for " + mPrice;
    mPurchaseGiftInforTv.setText(infor);

    mDialogSubmitTv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        processPurchaseItem();
      }
    });
  }

  private void processPurchaseItem() {
    if (mOnSubmitPurchaseItem != null) {
      mOnSubmitPurchaseItem.onSubmitItem();
    }
  }

  public ConfirmPurchaseDialog setOnItemSelected(OnSubmitPurchaseGift onItemSelected) {
    mOnSubmitPurchaseItem = onItemSelected;
    return this;
  }

  /**
   * Dialog callback
   */
  public interface OnSubmitPurchaseGift {
    void onSubmitItem();
  }
}
