package app.positiveculture.com.agent.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Vertical Space ItemDecoration
 * Created by NEO on 11/9/2016.
 */

public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

  private final int verticalSpaceHeight;

  public VerticalSpaceItemDecoration(int verticalSpaceHeight) {
    this.verticalSpaceHeight = verticalSpaceHeight;
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                             RecyclerView.State state) {
    outRect.bottom = verticalSpaceHeight;
  }
}