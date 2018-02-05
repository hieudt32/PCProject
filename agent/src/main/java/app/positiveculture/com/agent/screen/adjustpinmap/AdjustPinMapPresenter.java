package app.positiveculture.com.agent.screen.adjustpinmap;

import android.widget.Toast;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.google.android.gms.maps.model.LatLng;

import app.positiveculture.com.data.GoogleServiceBuilder;
import app.positiveculture.com.data.response.dto.AddrComponent;
import app.positiveculture.com.data.response.dto.Address;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The AdjustPinMap Presenter
 */
public class AdjustPinMapPresenter extends Presenter<AdjustPinMapContract.View, AdjustPinMapContract.Interactor>
        implements AdjustPinMapContract.Presenter {

  private LatLng latLng;
  private Address address;
  private OnDragMarkerEndListener onDragMarkerEndListener;
  private boolean isFromSummary = false;

  public void setOnDragMarkerEndListener(OnDragMarkerEndListener onDragMarkerEndListener) {
    this.onDragMarkerEndListener = onDragMarkerEndListener;
  }

  public AdjustPinMapPresenter setFromSummary(boolean fromSummary) {
    this.isFromSummary = fromSummary;
    return this;
  }

  public AdjustPinMapPresenter setAddress(Address address) {
    this.address = address;
    return this;
  }

  public AdjustPinMapPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public AdjustPinMapContract.View onCreateView() {
    return AdjustPinMapFragment.getInstance();
  }

  @Override
  public void start() {
    if (isFromSummary) {
      mView.hideStepIndicator();
    }
  }

  public AdjustPinMapPresenter setLatlng(LatLng latLng) {
    this.latLng = latLng;
    return this;
  }

  @Override
  public LatLng getLatlng() {
    return this.latLng;
  }

  @Override
  public void setLocation(LatLng latlng) {
    this.latLng = latlng;
    back();
  }

  @Override
  public void back() {
    if (this.address != null && onDragMarkerEndListener != null) {
      onDragMarkerEndListener.onDragEnd(this.latLng);
    }
    super.back();
  }

  public interface OnDragMarkerEndListener {
    void onDragEnd(LatLng latLng);
  }

  @Override
  public void goBack() {
    mContainerView.back();
  }

  @Override
  public AdjustPinMapContract.Interactor onCreateInteractor() {
    return new AdjustPinMapInteractor(this);
  }

}
