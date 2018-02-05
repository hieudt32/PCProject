package app.positiveculture.com.agent.screen.location;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.data.response.dto.GoogleMapSuggestionDTO;
import app.positiveculture.com.data.response.dto.Location;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by BB on 8/22/2017.
 */

public class SearchAdapter extends RecyclerView.Adapter {

  private Context mContext;
  private List<GoogleMapSuggestionDTO> searchLocationDTOs;
  private OnItemLocationClickListener onItemLocationClickListener;

  public void setOnItemLocationClickListener(OnItemLocationClickListener onItemLocationClickListener) {
    this.onItemLocationClickListener = onItemLocationClickListener;
  }

  public SearchAdapter(Context mContext, List<GoogleMapSuggestionDTO> searchLocationDTOs) {
    this.mContext = mContext;
    this.searchLocationDTOs = searchLocationDTOs;
  }

  public class SearchVH extends RecyclerView.ViewHolder {

    @BindView(R2.id.title_tv)
    TextView mTitleTv;

    public SearchVH(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);

    }
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View item = View.inflate(mContext, R.layout.item_location_search, null);
    RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    item.setLayoutParams(params);
    return new SearchVH(item);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
    SearchVH searchVH = (SearchVH) holder;
    searchVH.mTitleTv.setText(searchLocationDTOs.get(position).getName());

    searchVH.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (onItemLocationClickListener != null) {
          onItemLocationClickListener.onItemClick(searchLocationDTOs.get(position).getPlaceId());
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return searchLocationDTOs.size();
  }

  public interface OnItemLocationClickListener {
    void onItemClick(String placeId);
  }
}
