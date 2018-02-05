package dialog;

import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import font.app.gem.widgetlibrary.R;
import font.app.gem.widgetlibrary.R2;

/**
 * Created by luong on 24/08/2017.
 */

public class ActionDialogAdapter extends RecyclerView.Adapter<ActionDialogAdapter.ActionDialogViewHolder> {

    private List<DialogAction> actionList = new ArrayList<>();
    private Dialog mDialog;

    public ActionDialogAdapter(List<DialogAction> actionList, Dialog dialog) {
        this.actionList = actionList;
        mDialog = dialog;
    }

    @Override
    public ActionDialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_action_dialog, parent, false);
        return new ActionDialogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ActionDialogViewHolder holder, final int position) {
        holder.tvActionDialog.setText(actionList.get(position).getActionName());
        holder.tvActionDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionList.get(position).getOnActionClickListener().onActionSelected();
                mDialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return actionList.size();
    }

    class ActionDialogViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.action_name_tv)
        TextView tvActionDialog;

        public ActionDialogViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
