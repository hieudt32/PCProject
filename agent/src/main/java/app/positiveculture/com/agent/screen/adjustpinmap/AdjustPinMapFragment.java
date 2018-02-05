package app.positiveculture.com.agent.screen.adjustpinmap;

import android.view.View;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.StepIndicatorHorizontal;
import app.positiveculture.com.agent.screen.locationaddress.map.MapManager;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomHeaderView;

/**
 * The AdjustPinMap Fragment
 */
public class AdjustPinMapFragment extends ViewFragment<AdjustPinMapContract.Presenter> implements AdjustPinMapContract.View {

  private MapManager mapManager;
  private MapFragment mapFragment;
  private LatLng mLatLng;
  private LatLng oldLatLng;

  @BindView(R2.id.adjust_pin_hd)
  CustomHeaderView mHeader;
  @BindView(R2.id.adjust_pin_step)
  StepIndicatorHorizontal mStep;
  @BindView(R2.id.right_text_tv)
  TextView mDoneBt;

  @OnClick(R2.id.right_text_tv)
  void doneLocation() {
    if (mLatLng == null) return;
    mPresenter.setLocation(mLatLng);
  }

  private OnBackLocationAddrListener onBackLocationAddrListener;

  public void setOnBackLocationAddrListener(OnBackLocationAddrListener onBackLocationAddrListener) {
    this.onBackLocationAddrListener = onBackLocationAddrListener;
  }

  public static AdjustPinMapFragment getInstance() {
    return new AdjustPinMapFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_adjust_pin_map;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mDoneBt.setVisibility(View.GONE);
    initMap();
    mHeader.setOnHeaderEventListener(new CustomHeaderView.OnHeaderEventListener() {
      @Override
      public void onLeftIconClick() {
        mPresenter.goBack();
      }


      @Override
      public void onRightIconClick() {

      }
    });
    mStep.setCurrentStep(2);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    MapFragment f = (MapFragment) getViewContext().getFragmentManager()
            .findFragmentById(R.id.adjust_pin_map);
    if (f != null)
      getViewContext().getFragmentManager().beginTransaction().remove(f).commit();
    onBackLocationAddrListener.onBackAddr();
  }

  @Override
  public void onPause() {
    super.onPause();
    MapFragment f = (MapFragment) getViewContext().getFragmentManager()
            .findFragmentById(R.id.adjust_pin_map);
    if (f != null)
      getViewContext().getFragmentManager().beginTransaction().remove(f).commit();
  }

  private void initMap() {
    mapFragment = (MapFragment) getViewContext().getFragmentManager().findFragmentById(R.id.adjust_pin_map);
    mapFragment.getMapAsync(new OnMapReadyCallback() {
      @Override
      public void onMapReady(GoogleMap googleMap) {
        mapManager = new MapManager(getViewContext(), googleMap);
        oldLatLng = mPresenter.getLatlng();
        mapManager.addMarker(oldLatLng, "");
        googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
          @Override
          public void onMarkerDragStart(Marker marker) {

          }

          @Override
          public void onMarkerDrag(Marker marker) {

          }

          @Override
          public void onMarkerDragEnd(Marker marker) {
            mLatLng = marker.getPosition();
            setVisibleDone();
          }
        });

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
          @Override
          public void onMapClick(LatLng latLng) {
            mLatLng = latLng;
            mapManager.addMarker(latLng, "");
            setVisibleDone();
          }
        });
      }
    });
  }

  private void setVisibleDone() {
    if (mLatLng == null || oldLatLng == null) return;
    if (oldLatLng.latitude != mLatLng.latitude || oldLatLng.longitude != mLatLng.longitude) {
      mDoneBt.setVisibility(View.VISIBLE);
    } else {
      mDoneBt.setVisibility(View.GONE);
    }
  }

  @Override
  public void hideStepIndicator() {
    mStep.setVisibility(View.GONE);
  }

  public interface OnBackLocationAddrListener {
    void onBackAddr();
  }
}
