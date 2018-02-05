package font.app.gem.widget.ui.dialog;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import font.app.gem.widgetlibrary.R;

/**
 * dialog for choose service type to booking
 * Created by hungdn on 2/8/2017.
 */

public class ChooseServiceTypeDialog extends TranslucentDialog implements View.OnClickListener{

  private OnSubmitChooseService mOnsubmitChooseService;
  private Button menuType;
  private Button eventType;
  private int mMenuAvailable, mEventAvailable;

  public ChooseServiceTypeDialog(int menuAvailable, int eventAvailable) {
    super();
    mMenuAvailable = menuAvailable;
    mEventAvailable = eventAvailable;
  }


  @Override
  protected int getLayoutRes() {
    return R.layout.dialog_choose_service_type;
  }

  @Override
  protected void injectViews(Dialog dialog, View v) {
    menuType = (Button) v.findViewById(R.id.menu_service_bt);
    eventType = (Button) v.findViewById(R.id.event_service_bt);
    TextView cancel = (TextView) v.findViewById(R.id.cancel_dialog_tv);

    menuType.setSelected(true);
    menuType.setPressed(true);

    menuType.setOnClickListener(this);
    eventType.setOnClickListener(this);
    cancel.setOnClickListener(this);

    if (mMenuAvailable == 0) {
      menuType.setBackgroundResource(R.drawable.round_grey_solid);
      menuType.setEnabled(false);
    } else if (mEventAvailable == 0) {
      eventType.setBackgroundResource(R.drawable.round_grey_solid);
      eventType.setEnabled(false);
    }
  }

  @Override
  public void onClick(View view) {
    int Service;
    int i = view.getId();
    if (i == R.id.menu_service_bt) {
      menuType.setSelected(true);
      eventType.setSelected(false);
      Service = 1;
      chooseService(Service);

    } else if (i == R.id.event_service_bt) {
      menuType.setPressed(false);
      menuType.setSelected(false);
      eventType.setSelected(true);
      eventType.setPressed(true);
      Service = 2;
      chooseService(Service);

    } else if (i == R.id.cancel_dialog_tv) {
      cancelDialog();
    }
  }

  private void cancelDialog() {
    if (mOnsubmitChooseService != null) {
      mOnsubmitChooseService.onDismisDialog();
    }
  }

  private void chooseService(int service) {
    if (mOnsubmitChooseService != null) {
      mOnsubmitChooseService.onSubmitChooseService(service);
    }
  }

  public ChooseServiceTypeDialog setOnItemSelected(OnSubmitChooseService onItemSelected) {
    mOnsubmitChooseService = onItemSelected;
    return this;
  }


  /**
   * Dialog callback
   */
  public interface OnSubmitChooseService {
    void onSubmitChooseService(int service);
    void onDismisDialog();
  }
}
