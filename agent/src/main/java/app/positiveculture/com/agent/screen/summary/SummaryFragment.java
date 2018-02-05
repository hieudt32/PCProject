package app.positiveculture.com.agent.screen.summary;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.RecyclerUtils;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.ItemSummaryCustom;
import app.positiveculture.com.agent.utils.NumberUtils;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.response.dto.CreatePropertyDTO;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomButton;
import dialog.CustomDialog;
import utils.DialogUtils;

/**
 * The Summary Fragment
 */
public class SummaryFragment extends ViewFragment<SummaryContract.Presenter> implements SummaryContract.View {

  @BindView(R2.id.right_text_tv)
  TextView mPreviewTv;
  @BindView(R2.id.create_listing)
  CustomButton mCreateListing;
  @BindView(R2.id.summary_address)
  ItemSummaryCustom mAddress;
  @BindView(R2.id.summary_property_detail)
  ItemSummaryCustom mDetail;
  @BindView(R2.id.summary_price)
  ItemSummaryCustom mPrice;
  @BindView(R2.id.summary_valuation)
  ItemSummaryCustom mValuation;
  @BindView(R2.id.summary_description)
  ItemSummaryCustom mDescription;
  @BindView(R2.id.summary_thumb_iv)
  SimpleDraweeView mThumb;
  @BindView(R2.id.buyer_summary_rv)
  RecyclerView mBuyerList;


  @OnClick(R2.id.left_text_tv)
  void onDeleteClick() {
    DialogUtils.showDialog(getViewContext(), getString(R.string.delete_listing), getString(R.string.delete_listing_mes)
            , new CustomDialog.OnConfirmSelected() {
              @Override
              public void onConfirmSelected() {
                mPresenter.gotoPropertiesScreen();
              }
            }
            , new CustomDialog.OnCancelSelected() {
              @Override
              public void onCancelSelected() {
              }
            });
  }

  @OnClick(R2.id.summary_thumb_iv)
  void doThumbPhoToClick(){
    mPresenter.backToPhoto();
  }

  @OnClick(R2.id.right_text_tv)
  void onPreviewClick() {
    mPreviewTv.setClickable(false);
    mPresenter.goToDetailScreen();
  }

  @OnClick(R2.id.create_listing)
  void onCreateListingClick() {
    mPresenter.createProperty();
  }

  @OnClick(R2.id.add_owner_iv)
  void onAddOwnersClick() {
    mPresenter.goToSelectOwner();
  }

  public static SummaryFragment getInstance() {
    return new SummaryFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_summary;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mCreateListing.statusEnableButton();
    mValuation.setClickButtonNextItem(new ItemSummaryCustom.OnClickButtonNextItem() {
      @Override
      public void onNextButtonClick() {
        mPresenter.goToPriceScreen();
      }
    });

    mAddress.setClickButtonNextItem(new ItemSummaryCustom.OnClickButtonNextItem() {
      @Override
      public void onNextButtonClick() {
        mAddress.setClickable(false);
        mPresenter.goToLocationAddressScreen();
      }
    });

    mDetail.setClickButtonNextItem(new ItemSummaryCustom.OnClickButtonNextItem() {
      @Override
      public void onNextButtonClick() {
        mPresenter.goToPropertyDetailScreen();
      }
    });

    mDescription.setClickButtonNextItem(new ItemSummaryCustom.OnClickButtonNextItem() {
      @Override
      public void onNextButtonClick() {
        mPresenter.goToDescriptionScreen();
      }
    });

    RecyclerUtils.setupVerticalRecyclerView(getViewContext(), mBuyerList);
  }

  @Override
  public void bindDataToView(CreatePropertyDTO createPropertyDTO) {
    if (createPropertyDTO.getmGallery() != null && !createPropertyDTO.getmGallery().isEmpty()) {
      ViewUtils.loadImageWithFresco(mThumb, createPropertyDTO.getmGallery().get(0).getImgMedium());
    }
    mAddress.setContent(createPropertyDTO.getmStreetLineOne());
    mDescription.setContent(createPropertyDTO.getmDescription());
    setPrice(createPropertyDTO);
    setDetail(createPropertyDTO);
  }

  @Override
  public void bindPriceFromPriceEdit(CreatePropertyDTO createPropertyDTO) {
    setPrice(createPropertyDTO);
  }

  @Override
  public void bindDetailFromDetailEdit(CreatePropertyDTO createPropertyDTO) {
    setDetail(createPropertyDTO);
  }

  @Override
  public void bindDataFromDescriptionEdit(CreatePropertyDTO createPropertyDTO) {
    mDescription.setContent(createPropertyDTO.getmDescription());
  }

  @Override
  public void bindDataFromLocationAddress(CreatePropertyDTO createPropertyDTO) {
    mAddress.setContent(createPropertyDTO.getmStreetLineOne());
    mAddress.setClickable(true);
  }

  private void setPrice(CreatePropertyDTO createPropertyDTO) {
    String price = getString(R.string.dollar) + NumberUtils.formatNumberTwo(createPropertyDTO.getmPrice());
    mPrice.setContent(price);
    String valuation = getString(R.string.dollar) + NumberUtils.formatNumberTwo(createPropertyDTO.getmValuation());
    mValuation.setContent(valuation);
  }

  @Override
  public void enableClick() {
    mPreviewTv.setClickable(true);
  }

  @Override
  public void bindListOwners(AdapterBuyerSummary mAdapterBuyerSummary) {
    mBuyerList.setAdapter(mAdapterBuyerSummary);
  }

  private void setDetail(CreatePropertyDTO createPropertyDTO) {
    int room = createPropertyDTO.getmRooms();
    int plusRoom = createPropertyDTO.getmPlusRooms();
    int bathRoom = createPropertyDTO.getmBathrooms();
    float floorSizeBuiltUp = createPropertyDTO.getmFloorSizeBuilt();
    float floorSizeLanded = createPropertyDTO.getmFloorSizeLanded();
    String floorSizeUnit = createPropertyDTO.getmFloorSizeUnit();

    String detail = "";
    if (room != 0) {
      detail += room;
    } else {
      detail += getString(R.string.studio);
    }
    if (plusRoom != 0) detail += "+" + plusRoom;

    detail += " " + getString(R.string.rooms) + " " + getString(R.string.dot) + " " + bathRoom
            + " " + getString(R.string.bathrooms) + " " + getString(R.string.dot) + " " + floorSizeBuiltUp;
    if (floorSizeLanded == 0) {
      detail = detail + " " + floorSizeUnit;
    } else {
      detail = detail + " " + floorSizeLanded + " " + floorSizeUnit;
    }
    mDetail.setContent(detail);
  }
}
