package font.app.gem.widget.ui.dialog;

import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import font.app.gem.widgetlibrary.R;

/**
 * dialog when gift was send to user
 * Created by hungdn on 1/25/2017.
 */

public class GiftDeliveredDialog extends TranslucentDialog {

  private ConfirmPurchaseDialog.OnSubmitPurchaseGift mOnSubmitPurchaseItem;

  private String mImage, mName, mReceiverName;

  /**
   * Create a new instance dialog
   */
  public static GiftDeliveredDialog newInstance(String image, String name, String receiver ) {
    return new GiftDeliveredDialog().setPurchaseItem(image, name, receiver);
  }

  public GiftDeliveredDialog setPurchaseItem(String image, String name, String receiver) {

    this.mImage = image;
    this.mName = name;
    this.mReceiverName = receiver;
    return this;
  }

  @Override
  protected void injectViews(Dialog dialog, View v) {
    ImageView purchaseGiftIv = (ImageView) v.findViewById(R.id.purchase_gift_image_iv);
    TextView purchaseGiftInforTv = (TextView) v.findViewById(R.id.purchase_gift_infor_tv);
    TextView dialogSubmitTv = (TextView) v.findViewById(R.id.dialog_submit_tv);

    Glide
            .with(getContext())
            .load(mImage)
            .fitCenter()
            .dontAnimate()
            .into(purchaseGiftIv);

    String infor = "You have successfully send a " + mName + " to " + mReceiverName;
    purchaseGiftInforTv.setText(infor);

    dialogSubmitTv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finishDialog();
      }
    });
  }

  private void finishDialog() {
    if (mOnSubmitPurchaseItem != null) {
      mOnSubmitPurchaseItem.onSubmitItem();
    }
  }

  public GiftDeliveredDialog setOnItemSelected(ConfirmPurchaseDialog.OnSubmitPurchaseGift onItemSelected) {
    mOnSubmitPurchaseItem = onItemSelected;
    return this;
  }

  /**
   * Dialog callback
   */
  public interface OnSubmitPurchaseGift {
    void onSubmitItem();
  }

  @Override
  protected int getLayoutRes() {
    return R.layout.dialog_gift_delivered;
  }

}
