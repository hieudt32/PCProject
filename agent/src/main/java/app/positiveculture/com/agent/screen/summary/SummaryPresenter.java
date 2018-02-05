package app.positiveculture.com.agent.screen.summary;

import android.support.v4.app.Fragment;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.eventbus.EventBusWrapper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import app.positiveculture.com.agent.event.ListingCreatedEvent;
import app.positiveculture.com.agent.screen.description.DescriptionPresenter;
import app.positiveculture.com.agent.screen.locationaddress.LocationAddressPresenter;
import app.positiveculture.com.agent.screen.main.MainPresenterAgent;
import app.positiveculture.com.agent.screen.price.PricePresenter;
import app.positiveculture.com.agent.screen.properties.detail.DetailPresenter;
import app.positiveculture.com.agent.screen.propertydetails.PropertyDetailsPresenter;
import app.positiveculture.com.agent.screen.selectowners.SelectOwnersPresenter;
import app.positiveculture.com.agent.utils.FragmentUtils;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.CreatePropertyDTO;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import utils.DateTimeUtils;

/**
 * The Summary Presenter
 */
public class SummaryPresenter extends Presenter<SummaryContract.View, SummaryContract.Interactor>
        implements SummaryContract.Presenter, PricePresenter.OnGetPriceListener,
        PropertyDetailsPresenter.OnGetPropertyDetailListener,
        DescriptionPresenter.OnGetPropertyDescriptionListener,
        LocationAddressPresenter.OnGetLocationPropertyListener, AdapterBuyerSummary.OnRemoveItemListener,
        SelectOwnersPresenter.OnGetOwnersListener, DetailPresenter.OnBackToSummaryScreenListener {
  private CreatePropertyDTO createPropertyDTO;
  private AdapterBuyerSummary mAdapterBuyerSummary;
  private List<MemberDTO> mListOwner;
  private Date now = Calendar.getInstance().getTime();
  private OnBackPhotoFromSummaryListener mOnBackPhotoFromSummaryListener;

  public SummaryPresenter setOnBackPhotoFromSummaryListener(OnBackPhotoFromSummaryListener mOnBackPhotoFromSummaryListener) {
    this.mOnBackPhotoFromSummaryListener = mOnBackPhotoFromSummaryListener;
    return this;
  }

  public SummaryPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public SummaryContract.View onCreateView() {
    return SummaryFragment.getInstance();
  }

  @Override
  public void start() {
    if (createPropertyDTO != null) {
      mView.bindDataToView(createPropertyDTO);
      if (!createPropertyDTO.getmListOwner().isEmpty()) {
        mListOwner = createPropertyDTO.getmListOwner();
        mAdapterBuyerSummary = new AdapterBuyerSummary(mListOwner);
        mAdapterBuyerSummary.setOnRemoveItemListener(this);
        mView.bindListOwners(mAdapterBuyerSummary);
      }
    }
  }

  @Override
  public void goToDetailScreen() {
    new DetailPresenter(mContainerView)
            .setProperty(buildFromCreateProperty(createPropertyDTO))
            .setOnBackToSummaryScreenListener(this)
            .pushView();
  }

  private PropertyDTO buildFromCreateProperty(CreatePropertyDTO createPropertyDTO) {
    PropertyDTO propertyDTO = new PropertyDTO();
    propertyDTO.setmCreateAt(DateTimeUtils.dateTimeFormatOne().format(now));
    propertyDTO.setmUpdateAt(DateTimeUtils.dateTimeFormatOne().format(now));
    propertyDTO.setmRooms(createPropertyDTO.getmRooms());
    propertyDTO.setmPlusRooms(createPropertyDTO.getmPlusRooms());
    propertyDTO.setmBathrooms(createPropertyDTO.getmBathrooms());
    propertyDTO.setmBuilding(createPropertyDTO.getmBuilding());
    propertyDTO.setmCountry(createPropertyDTO.getmCountry());
    propertyDTO.setmDescription(createPropertyDTO.getmDescription());
    propertyDTO.setmFloorSizeBuilt(createPropertyDTO.getmFloorSizeBuilt());
    propertyDTO.setmFloorSizeLanded(createPropertyDTO.getmFloorSizeLanded());
    propertyDTO.setmFloorSizeUnit(createPropertyDTO.getmFloorSizeUnit());
    propertyDTO.setmFeatrureImage(createPropertyDTO.getmGallery().get(0));
    ArrayList<FileDTO> list = new ArrayList<>();
    list.addAll(createPropertyDTO.getmGallery());
    if (list.size() > 1) list.remove(0);
    propertyDTO.setmGallery(list);
    propertyDTO.setmLatitude(createPropertyDTO.getmLatitude());
    propertyDTO.setmLongitude(createPropertyDTO.getmLongitude());
    propertyDTO.setmStreetLineOne(createPropertyDTO.getmStreetLineOne());
    propertyDTO.setmStreetLineTwo(createPropertyDTO.getmStreetLineTwo());
    propertyDTO.setmType(createPropertyDTO.getmType());
    propertyDTO.setmPrice(createPropertyDTO.getmPrice());
    propertyDTO.setmValuation(createPropertyDTO.getmValuation());
    return propertyDTO;
  }

  @Override
  public void gotoPropertiesScreen() {
    Fragment fragment = FragmentUtils.getFragmentMain(mContainerView.getBaseActivity());
    if (fragment != null) {
      mContainerView.getBaseActivity().getSupportFragmentManager().popBackStackImmediate(fragment.getClass().getSimpleName(), 0);
    } else {
      new MainPresenterAgent(mContainerView).pushView();
    }
  }

  @Override
  public void createProperty() {
    mView.showProgress();
    mInteractor.createProperty(createPropertyDTO, new CommonCallback<PropertyDTO>(getViewContext()) {
      @Override
      public void onSuccess(PropertyDTO data) {
        super.onSuccess(data);
        EventBusWrapper.post(new ListingCreatedEvent());
        gotoPropertiesScreen();
        mView.hideProgress();
      }

      @Override
      public void onError(String errorCode, ErrorDTO errorMessage) {
        super.onError(errorCode, errorMessage);
        mView.hideProgress();
      }
    });
  }


  @Override
  public void goToPriceScreen() {
    new PricePresenter(mContainerView)
            .setmOnGetPriceListener(this)
            .setCreatePropertyDTO(createPropertyDTO)
            .pushView();
  }

  @Override
  public void goToLocationAddressScreen() {
    new LocationAddressPresenter(mContainerView)
            .setCreatePropertyDTO(createPropertyDTO)
            .setmOnGetLocationPropertyListener(this)
            .pushView();
  }

  @Override
  public void goToPropertyDetailScreen() {
    new PropertyDetailsPresenter(mContainerView)
            .setGetPropertyDetailListener(this)
            .setCreatePropertyDTO(createPropertyDTO)
            .pushView();
  }

  @Override
  public void goToDescriptionScreen() {
    new DescriptionPresenter(mContainerView)
            .setmOnGetPropertyDescriptionListener(this)
            .setCreatePropertyDTO(createPropertyDTO)
            .pushView();
  }

  @Override
  public SummaryContract.Interactor onCreateInteractor() {
    return new SummaryInteractor(this);
  }

  public SummaryPresenter setCreatePropertyDTO(CreatePropertyDTO createPropertyDTO) {
    this.createPropertyDTO = createPropertyDTO;
    return this;
  }

  @Override
  public void onGetPriceSuccessful(CreatePropertyDTO createPropertyDTO) {
    this.createPropertyDTO = createPropertyDTO;
    mView.bindPriceFromPriceEdit(this.createPropertyDTO);
  }

  @Override
  public void onGetDetailSuccessful(CreatePropertyDTO createPropertyDTO) {
    this.createPropertyDTO = createPropertyDTO;
    mView.bindDetailFromDetailEdit(this.createPropertyDTO);
  }

  @Override
  public void onGetDescriptionSuccessful(CreatePropertyDTO createPropertyDTO) {
    this.createPropertyDTO = createPropertyDTO;
    mView.bindDataFromDescriptionEdit(this.createPropertyDTO);
  }

  @Override
  public void onGetLocationPropertySuccessful(CreatePropertyDTO createPropertyDTO) {
    this.createPropertyDTO = createPropertyDTO;
    mView.bindDataFromLocationAddress(this.createPropertyDTO);
  }

  @Override
  public void goToSelectOwner() {
    createPropertyDTO.setmListOwner(mListOwner);
    new SelectOwnersPresenter(mContainerView)
            .setPropertyFromSummary(createPropertyDTO)
            .setOnGetOwnersListener(this)
            .pushView();
  }

  @Override
  public void backToPhoto() {
    if(mOnBackPhotoFromSummaryListener!=null){
      mOnBackPhotoFromSummaryListener.onBackPhotoScreen();
      mContainerView.back();
    }
  }

  @Override
  public void onRemove(MemberDTO memberDTO) {
    mListOwner.remove(memberDTO);
    mAdapterBuyerSummary.notifyDataSetChanged();
  }

  @Override
  public void onGetOwnersSuccessful(CreatePropertyDTO createPropertyDTO) {
    this.createPropertyDTO = createPropertyDTO;
    mListOwner = createPropertyDTO.getmListOwner();
    mAdapterBuyerSummary = new AdapterBuyerSummary(mListOwner);
    mAdapterBuyerSummary.setOnRemoveItemListener(this);
    mView.bindListOwners(mAdapterBuyerSummary);
  }

  @Override
  public void onResetPreviewClick() {
    mView.enableClick();
  }

  public interface OnBackPhotoFromSummaryListener{
    void onBackPhotoScreen();
  }
}
