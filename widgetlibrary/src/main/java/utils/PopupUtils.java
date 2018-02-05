package utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;

import dialog.*;
import dialog.ActionDialogAdapter;
import font.app.gem.widgetlibrary.R;

/**
 * Created by luong on 24/08/2017.
 */

public class PopupUtils {

    public static void showActionListDialog(final Context context, final List<dialog.DialogAction> actions) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_action);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        RecyclerView mActionDialogRv = (RecyclerView) dialog.findViewById(R.id.rv_action_list);
        dialog.ActionDialogAdapter mAdapter = new ActionDialogAdapter(actions, dialog);
        mActionDialogRv.setLayoutManager(new GridLayoutManager(context, 1));
        mActionDialogRv.setAdapter(mAdapter);

        TextView cancelTv = (TextView) dialog.findViewById(R.id.tv_cancel_dialog);
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

}
