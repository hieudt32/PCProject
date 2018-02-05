package app.positiveculture.com.user.screen.usereditprofile;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.DeviceUtils;
import com.gemvietnam.utils.PermissionUtils;
import com.gemvietnam.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import app.positiveculture.com.agent.GlobalStuff;
import app.positiveculture.com.agent.utils.CountryCode;
import app.positiveculture.com.agent.utils.FileUtils;
import app.positiveculture.com.agent.utils.MediaUtils;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.enumdata.ResidencyStatus;
import app.positiveculture.com.data.enumdata.TypeID;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.Address;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.Users;
import app.positiveculture.com.user.R;
import app.positiveculture.com.user.R2;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;
import customview.CustomHeaderView;
import dialog.DialogAction;
import utils.PopupUtils;

/**
 * The UserEditProfile Fragment
 */
public class UserEditProfileFragment extends ViewFragment<UserEditProfileContract.Presenter> implements UserEditProfileContract.View {

  private static final int TAKE_PICTURE_REQUEST_CODE = GlobalStuff.getFreshInt();
  private static final int PICK_PICTURE_REQUEST_CODE = GlobalStuff.getFreshInt();
  private static final int TAKE_PICTURE_PERMISSIONS_REQUEST_CODE = GlobalStuff.getFreshInt();
  private static final int READ_EXTERNAL_STORAGE_PERMISSIONS_REQUEST_CODE = GlobalStuff.getFreshInt();
  private static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
  private static final String[] READ_EXTERNAL_STORAGE = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};

  private Uri mSelectedUri;
  private String mCaptureImagePath;
  private String mRealFrontImageFilePath;

  @BindView(R2.id.edit_profile_custom_header)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.edit_profile_avatar_sdv)
  SimpleDraweeView mAvatarSdv;
  @BindView(R2.id.nric_tv)
  TextView mIdNumberTv;
  @BindView(R2.id.fullname_edt)
  EditText mFullnameEdt;
  @BindView(R2.id.phone_edt)
  EditText mPhoneEdt;
  @BindView(R2.id.date_of_birth_picker)
  TextView mDateTv;
  @BindView(R2.id.email_edt)
  EditText mEmailEdt;
  @BindView(R2.id.user_floor_edt)
  EditText mFloorEdt;
  @BindView(R2.id.user_unit_edt)
  EditText mUnitEdt;
  @BindView(R2.id.building_edt)
  EditText mBuildingEdt;
  @BindView(R2.id.street_edt)
  EditText mStreetEdt;
  @BindView(R2.id.street2_edt)
  EditText mStreet2Edt;
  @BindView(R2.id.postal_code_edt)
  EditText mPostalCodeEdt;
  @BindView(R2.id.country_edt)
  EditText mCountryEdt;
  @BindView(R2.id.name_et)
  EditText mNameEdt;
  @BindView(R2.id.com_phone_edt)
  EditText mComPhoneEdt;
  @BindView(R2.id.com_email_et)
  EditText mComEmailEdt;
  @BindView(R2.id.com_floor_edt)
  EditText mComFloorEdtl;
  @BindView(R2.id.com_unit_edt)
  EditText mComUnitEdtl;
  @BindView(R2.id.com_building_edt)
  EditText mComBuildingEdt;
  @BindView(R2.id.com_street_edt)
  EditText mComStreetEdt;
  @BindView(R2.id.com_street2_edt)
  EditText mComStreet2Edt;
  @BindView(R2.id.com_postal_code_edt)
  EditText mComPostalCodeEdt;
  @BindView(R2.id.com_country_edt)
  EditText mComCountryEdt;
  @BindView(R2.id.user_edit_bank_tv)
  TextView mBankTv;
  @BindView(R2.id.new_user_mobile_spinner)
  Spinner mPhoneCodeSp;
  @BindView(R2.id.new_comp_mobile_spinner)
  Spinner mComPhoneCodeSp;
  @BindView(R2.id.residecy_stt_sp)
  Spinner mResidecySp;
  @BindView(R2.id.user_edit_id_type_tv)
  TextView mIdTypeTv;

  @OnTouch(R2.id.user_edit_profile_ll)
  boolean doTouchHeader() {
    getBaseActivity().hideKeyboard();
    return false;
  }

  @OnClick(R2.id.user_edit_bank_tv)
  void doBankClick() {
    mPresenter.goToBankDetailScreen();
  }

  private Calendar mFromDate = Calendar.getInstance();

  public static String PERSONAL = "personal";
  public static String COMPANY = "company";

  public static UserEditProfileFragment getInstance() {
    return new UserEditProfileFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_user_edit_profile;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    String residencyTypes[] = new String[]{getString(R.string.singapore_citizen), getString(R.string.singapore_permanent_resident), getString(R.string.foreigner)};
    ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(getContext(), R.layout.item_spinner, residencyTypes);
    mResidecySp.setAdapter(mAdapter);
  }

  @Override
  public void createProfile(MemberDTO memberDTO) {

    if (memberDTO.getAvatar() != null) {
      mAvatarSdv.setImageURI(memberDTO.getAvatar().getImgSmall());
    }

    List<String> listCode = CountryCode.getCode();
    ArrayAdapter mMobileAdapter = new ArrayAdapter<String>(
            getViewContext(), R.layout.item_country_phone_sp, listCode);
    mPhoneCodeSp.setAdapter(mMobileAdapter);
    mComPhoneCodeSp.setAdapter(mMobileAdapter);

    mFullnameEdt.setText(memberDTO.getUsers().getFullName());

    mPhoneEdt.setText(memberDTO.getUsers().getPhoneNumber());
    mDateTv.setText(memberDTO.getmDateOfBirth());
    mEmailEdt.setText(memberDTO.getUsers().getEmail());

    if (memberDTO.getmResidencyStatus() != null) {
      ResidencyStatus residency = memberDTO.getmResidencyStatus();
      switch (residency) {
        case SINGAPORE_CITIZEN:
          mResidecySp.setSelection(1);
          break;
        case SINGAPORE_PERMANENT_RESIDENT:
          mResidecySp.setSelection(2);
          break;
        case FOREIGNER:
          mResidecySp.setSelection(3);
          break;
      }
    } else {
      mResidecySp.setSelection(0);
    }

    for (int i = 0; i < listCode.size(); i++) {
      if (memberDTO.getUsers().getPhoneCountryCode() != null) {
        if (listCode.get(i).equals(memberDTO.getUsers().getPhoneCountryCode()))
          mPhoneCodeSp.setSelection(i);
      }

      if (memberDTO.getmCompPhoneCountryCode() != null) {
        if (listCode.get(i).equals(memberDTO.getmCompPhoneCountryCode()))
          mComPhoneCodeSp.setSelection(i);
      }
    }

    mBankTv.setText(memberDTO.getBankName());
    mFloorEdt.setText(memberDTO.getmAddrFloor());
    mUnitEdt.setText(memberDTO.getmAddrUnit());
    mBuildingEdt.setText(memberDTO.getmAddrBuilding());
    mStreetEdt.setText(memberDTO.getmAddrStreetLineOne());
    mStreet2Edt.setText(memberDTO.getmAddrStreetLineTwo());
    mPostalCodeEdt.setText(memberDTO.getmAddrPostalCode());
    mCountryEdt.setText(memberDTO.getmAddrCountry());
    if (memberDTO.getIdType().equals(TypeID.co_reg_no)) {
      mIdTypeTv.setText(getString(R.string.co_reg_no));
    } else {
      mIdTypeTv.setText(getString(R.string.nric));
    }
    mIdNumberTv.setText(memberDTO.getmIdNumber());

    mNameEdt.setText(memberDTO.getmCompName());
    mComFloorEdtl.setText(memberDTO.getmCompAddrFloor());
    mComUnitEdtl.setText(memberDTO.getmComAddrUnit());
    mComPhoneEdt.setText(memberDTO.getmCompPhoneNumber());
    mComEmailEdt.setText(memberDTO.getmCompEmail());
    mComBuildingEdt.setText(memberDTO.getmCompAddrBuilding());
    mComStreetEdt.setText(memberDTO.getmCompAddrStreetLineOne());
    mComStreet2Edt.setText(memberDTO.getmCompAddrStreetLineTwo());
    mComPostalCodeEdt.setText(memberDTO.getmCompAddrPostalCode());
    mComCountryEdt.setText(memberDTO.getmCompAddrCountry());

  }

  @OnClick(R2.id.left_text_tv)
  public void cancel() {
    mPresenter.back();
  }

  @OnClick(R2.id.find_addr_bt)
  public void searchAddr() {
    mPresenter.searchAddr(PERSONAL);
  }

  @OnClick(R2.id.find_com_addr_bt)
  public void searchComAddr() {
    mPresenter.searchAddr(COMPANY);
  }

  @OnClick(R2.id.nric_bt)
  public void showNric() {
    mPresenter.showNric();
  }

  @OnClick(R2.id.date_of_birth_picker)
  public void showDatePickerDialog() {

    int year, month, day;

    DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
      public void onDateSet(DatePicker view, int year,
                            int monthOfYear,
                            int dayOfMonth) {

        mFromDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        mFromDate.set(Calendar.MONTH, monthOfYear);
        mFromDate.set(Calendar.YEAR, year);

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        mDateTv.setText(format1.format(mFromDate.getTime()));

      }
    };

    if (!StringUtils.isEmpty(mDateTv.getText().toString())) {
      year = mFromDate.get(Calendar.YEAR);
      month = mFromDate.get(Calendar.MONTH);
      day = mFromDate.get(Calendar.DAY_OF_MONTH);
    } else {
      Calendar c = Calendar.getInstance();
      year = c.get(Calendar.YEAR);
      month = c.get(Calendar.MONTH);
      day = c.get(Calendar.DAY_OF_MONTH);
    }

    DatePickerDialog pic = new DatePickerDialog(
            getContext(),
            callback, year, month, day);
    pic.show();
  }

  @OnClick(R2.id.right_text_tv)
  public void saveProfile() {
    MemberDTO memberDTO = PrefWrapper.getMember();
    if (memberDTO.getUsers() != null) {
      memberDTO.getUsers().setFullName(mFullnameEdt.getText().toString());
      memberDTO.getUsers().setPhoneNumber(mPhoneEdt.getText().toString());
      memberDTO.getUsers().setEmail(mEmailEdt.getText().toString());
      memberDTO.getUsers().setPhoneCountryCode(mPhoneCodeSp.getSelectedItem().toString());
    } else {
      Users users = new Users();
      users.setFullName(mFullnameEdt.getText().toString());
      users.setPhoneNumber(mPhoneEdt.getText().toString());
      users.setEmail(mEmailEdt.getText().toString());
      users.setPhoneCountryCode(mPhoneCodeSp.getSelectedItem().toString());
      memberDTO.setUsers(users);
    }
    memberDTO.setmDateOfBirth(mDateTv.getText().toString());
    switch (mResidecySp.getSelectedItemPosition()) {
      case 1:
        memberDTO.setmResidencyStatus(ResidencyStatus.SINGAPORE_CITIZEN);
        break;
      case 2:
        memberDTO.setmResidencyStatus(ResidencyStatus.SINGAPORE_PERMANENT_RESIDENT);
        break;
      case 3:
        memberDTO.setmResidencyStatus(ResidencyStatus.FOREIGNER);
        break;
    }
    String floor = mFloorEdt.getText().toString();
    String unit = mUnitEdt.getText().toString();
    memberDTO.setmAddrFloor(floor);
    memberDTO.setmAddrUnit(unit);
    memberDTO.setmAddrBuilding(mBuildingEdt.getText().toString());
    memberDTO.setmAddrStreetLineOne(mStreetEdt.getText().toString());
    memberDTO.setmAddrStreetLineTwo(mStreet2Edt.getText().toString());
    memberDTO.setmAddrPostalCode(mPostalCodeEdt.getText().toString());
    memberDTO.setmAddrCountry(mCountryEdt.getText().toString());
    memberDTO.setmCompName(mNameEdt.getText().toString());
    memberDTO.setmCompPhoneCountryCode(mComPhoneCodeSp.getSelectedItem().toString());
    memberDTO.setmCompPhoneNumber(mComPhoneEdt.getText().toString());
    memberDTO.setmCompEmail(mComEmailEdt.getText().toString());
    String compFloor = mComFloorEdtl.getText().toString();
    String compUnit = mComUnitEdtl.getText().toString();
    memberDTO.setmCompAddrFloor(compFloor);
    memberDTO.setmComAddrUnit(compUnit);
    memberDTO.setmCompAddrBuilding(mComBuildingEdt.getText().toString());
    memberDTO.setmCompAddrStreetLineOne(mComStreetEdt.getText().toString());
    memberDTO.setmCompAddrStreetLineTwo(mComStreet2Edt.getText().toString());
    memberDTO.setmCompAddrPostalCode(mComPostalCodeEdt.getText().toString());
    memberDTO.setmCompAddrCountry(mComCountryEdt.getText().toString());
    mPresenter.saveProfile(memberDTO);
  }

  @OnClick(R2.id.edit_profile_avatar_sdv)
  public void takeAvatar() {
    List<DialogAction> dialogActionList = new ArrayList<DialogAction>();

    dialogActionList.add(new DialogAction("Take Photo", new DialogAction.OnActionSelectedListener() {
      @Override
      public void onActionSelected() {
        if (!PermissionUtils.needRequestPermissions(getActivity(), UserEditProfileFragment.this, PERMISSIONS, TAKE_PICTURE_PERMISSIONS_REQUEST_CODE)) {
          captureAvatar();
        }
      }
    }));

    dialogActionList.add(new DialogAction("Choose Photo", new DialogAction.OnActionSelectedListener() {
      @Override
      public void onActionSelected() {
        pickFromGalery();
      }

    }));

    PopupUtils.showActionListDialog(getViewContext(), dialogActionList);
  }

  private void pickFromGalery() {
    if (!PermissionUtils.needRequestPermissions(getActivity(), UserEditProfileFragment.this, READ_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE_PERMISSIONS_REQUEST_CODE)) {
      Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
      photoPickerIntent.setType("image/*");
      startActivityForResult(photoPickerIntent, PICK_PICTURE_REQUEST_CODE);
    }
  }

  private void captureAvatar() {
    if (!PermissionUtils.needRequestPermissions(getActivity(), UserEditProfileFragment.this, PERMISSIONS, TAKE_PICTURE_PERMISSIONS_REQUEST_CODE)) {
      callCameraIntent();
    }
  }

  private void callCameraIntent() {
    try {
//      mImageFile = MediaUtils.createImageFile(getActivity());
      final boolean isNougat = DeviceUtils.isNougat();

      if (!isNougat) {
        mSelectedUri = Uri.fromFile(MediaUtils.createImageFile(getActivity()));
        MediaUtils.dispatchTakePictureIntent(getActivity(), TAKE_PICTURE_REQUEST_CODE, mSelectedUri);
      } else {
        mCaptureImagePath = FileUtils.getPathTempFile(getActivity());
        mSelectedUri = Uri.fromFile(new File(mCaptureImagePath));
        MediaUtils.dispatchTakePictureIntentWithNougat(getActivity(), TAKE_PICTURE_REQUEST_CODE, mCaptureImagePath);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == READ_EXTERNAL_STORAGE_PERMISSIONS_REQUEST_CODE &&
            PermissionUtils.isPermissionsGranted(getActivity(), grantResults)) {
      Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
      photoPickerIntent.setType("image/*");
      startActivityForResult(photoPickerIntent, PICK_PICTURE_REQUEST_CODE);
    } else if (requestCode == TAKE_PICTURE_PERMISSIONS_REQUEST_CODE && PermissionUtils.isPermissionsGranted(getActivity(), grantResults)) {
      callCameraIntent();
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode != Activity.RESULT_OK) {
      mHeaderView.getRightTextTv().setVisibility(View.INVISIBLE);
      mSelectedUri = null;
      return;
    }

    if (PICK_PICTURE_REQUEST_CODE == requestCode) {
      mSelectedUri = data.getData();
      mRealFrontImageFilePath = ViewUtils.getRealPathFromURI(mSelectedUri, getActivity());
      mPresenter.uploadUserAvatar(mSelectedUri, mRealFrontImageFilePath);
    } else if (TAKE_PICTURE_REQUEST_CODE == requestCode) {
      final boolean isNougat = DeviceUtils.isNougat();
      if (isNougat) {
        mSelectedUri = Uri.fromFile(new File(mCaptureImagePath));
      }
      mRealFrontImageFilePath = ViewUtils.getRealPathFromURI(mSelectedUri, getActivity());
      mPresenter.uploadUserAvatar(mSelectedUri, mRealFrontImageFilePath);
    }

    mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
  }

  @Override
  public void setNric(MemberDTO memberDTO) {
    if (memberDTO.getIdType().equals(TypeID.co_reg_no)) {
      mIdTypeTv.setText(getString(R.string.co_reg_no));
    } else {
      mIdTypeTv.setText(getString(R.string.nric));
    }
    mIdNumberTv.setText(memberDTO.getmIdNumber());
  }

  @Override
  public void bindPersonalAddr(Address address) {
    mFloorEdt.setText(address.getFloor());
    mBuildingEdt.setText(address.getBuilding());
    mStreetEdt.setText(address.getStreet());
    mStreet2Edt.setText(address.getStreet2());
    mPostalCodeEdt.setText(address.getPostalCode());
    mCountryEdt.setText(address.getCountry());
  }

  @Override
  public void bindCompanyAddr(Address address) {
    mComFloorEdtl.setText(address.getFloor());
    mComBuildingEdt.setText(address.getBuilding());
    mComStreetEdt.setText(address.getStreet());
    mComStreet2Edt.setText(address.getStreet2());
    mComPostalCodeEdt.setText(address.getPostalCode());
    mComCountryEdt.setText(address.getCountry());

  }

  @Override
  public void bindAvatar(FileDTO data) {
    if (data == null || data.getImgSmall() == null) return;
    ViewUtils.loadAvatarWithFresco(mAvatarSdv, data.getImgSmall());
  }
}
