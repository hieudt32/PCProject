package app.positiveculture.com.agent.screen.photo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.DeviceUtils;
import com.gemvietnam.utils.PermissionUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.positiveculture.com.agent.GlobalStuff;
import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.StepIndicatorHorizontal;
import app.positiveculture.com.agent.utils.FileUtils;
import app.positiveculture.com.agent.utils.MediaUtils;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.PhotoDTO;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomButton;
import customview.CustomHeaderView;
import dialog.DialogAction;
import utils.PopupUtils;

/**
 * The Photo Fragment
 */
public class PhotoFragment extends ViewFragment<PhotoContract.Presenter>
        implements PhotoContract.View, PhotoAdapter.OnPhotoItemClickListener, PhotoAdapter.OnDragEventListener {

  @BindView(R2.id.photo_rv)
  RecyclerView mPhotoRv;
  @BindView(R2.id.photo_hd)
  CustomHeaderView mHeader;
  @BindView(R2.id.photo_step)
  StepIndicatorHorizontal mStep;
  @BindView(R2.id.summary_cb)
  CustomButton mNextCb;
  private boolean typePhoTo;

  @OnClick(R2.id.summary_cb)
  void onNext() {
    mPresenter.goToSummaryProperties(mPhotos);
  }

  private PhotoAdapter mPhotoAdapter;
  private ArrayList<FileDTO> mPhotos = new ArrayList<>();
  private Uri mSelectedUri;
  private String mCaptureImagePath;
  private String mRealFrontImageFilePath;
  private ItemTouchHelper mItemTouchHelper;

  private static final int TAKE_PICTURE_REQUEST_CODE = GlobalStuff.getFreshInt();
  private static final int PICK_PICTURE_REQUEST_CODE = GlobalStuff.getFreshInt();
  private static final int TAKE_PICTURE_PERMISSIONS_REQUEST_CODE = GlobalStuff.getFreshInt();
  private static final int READ_EXTERNAL_STORAGE_PERMISSIONS_REQUEST_CODE = GlobalStuff.getFreshInt();
  private static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
  private static final String[] READ_EXTERNAL_STORAGE = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};

  @Override
  public void initLayout() {
    super.initLayout();
    getBaseActivity().hideKeyboard();
    mNextCb.statusDisabledButton();
    GridLayoutManager layoutManager = new GridLayoutManager(getViewContext(), 3);
    mPhotoRv.setLayoutManager(layoutManager);
    mPhotoRv.setClipToPadding(true);
    mPhotoRv.setHasFixedSize(true);

//    int space = getResources().getDimensionPixelSize(R.dimen.item_space_vertical);
//    mPhotoRv.addItemDecoration(new GridSpacingItemDecoration(3, space, true));

    layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override
      public int getSpanSize(int position) {
        return (position == 0) ? 3 : 1;
      }
    });

    mPhotoAdapter = new PhotoAdapter(mPhotos);
    mPhotoAdapter.setOnPhotoItemClickListener(this);
    mPhotoRv.setAdapter(mPhotoAdapter);
    mHeader.setOnHeaderEventListener(new CustomHeaderView.OnHeaderEventListener() {
      @Override
      public void onLeftIconClick() {
        if (mHeader.getRightTextTv().getText().equals(getString(R.string.done))) {
          mHeader.getRightTextTv().setText(getString(R.string.edit));
          mPhotos = mPhotoAdapter.getPhotoList();
          mNextCb.setVisibility(View.VISIBLE);
          if (mPhotos.size() == 0) {
            mHeader.getRightTextTv().setVisibility(View.GONE);
            mNextCb.setTextButton(getString(R.string.next));
            mNextCb.statusDisabledButton();
          }
          mPhotoAdapter.setIsEditing(false);
          mPhotoAdapter.notifyDataSetChanged();
//          mPhotoAdapter.setOnDragListener(null);
        } else {
          mPresenter.goBack();
        }
      }

      @Override
      public void onRightIconClick() {

      }
    });
    mStep.setCurrentStep(6);
  }

  public static PhotoFragment getInstance() {
    return new PhotoFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_photo;
  }

  @Override
  public void onAddNewPhoto() {
    List<DialogAction> dialogActionList = new ArrayList<DialogAction>();

    dialogActionList.add(new DialogAction("Take Photo", new DialogAction.OnActionSelectedListener() {
      @Override
      public void onActionSelected() {
        takePicture();
      }
    }));

    dialogActionList.add(new DialogAction("Choose Photo", new DialogAction.OnActionSelectedListener() {
      @Override
      public void onActionSelected() {
        pickFromGallery();
      }
    }));

    PopupUtils.showActionListDialog(getViewContext(), dialogActionList);
  }

  /**
   * Take a picture from camera
   */
  private void takePicture() {
    if (!PermissionUtils.needRequestPermissions(getActivity(), this, PERMISSIONS, TAKE_PICTURE_PERMISSIONS_REQUEST_CODE)) {
      callCameraIntent();
    }
  }

  /**
   * Pick image from gallery
   */
  private void pickFromGallery() {
    if (!PermissionUtils.needRequestPermissions(getActivity(), PhotoFragment.this, READ_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE_PERMISSIONS_REQUEST_CODE)) {
      Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
      photoPickerIntent.setType("image/*");
      startActivityForResult(photoPickerIntent, PICK_PICTURE_REQUEST_CODE);
    }
  }

  /**
   * Call camera intent
   */
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
      mSelectedUri = null;
      return;
    }

    if (PICK_PICTURE_REQUEST_CODE == requestCode) {
      PhotoDTO photo = new PhotoDTO();
      mSelectedUri = data.getData();
      mRealFrontImageFilePath = ViewUtils.getRealPathFromURI(mSelectedUri, getActivity());

      ViewUtils.rotateImage(mSelectedUri);
      photo.setPhotoUrl(mSelectedUri);
      photo.setRealImagePath(mRealFrontImageFilePath);
      mPresenter.uploadGalleryImage(photo);
    } else if (TAKE_PICTURE_REQUEST_CODE == requestCode) {

      final boolean isNougat = DeviceUtils.isNougat();
      PhotoDTO photo = new PhotoDTO();
      if (isNougat) {
        mSelectedUri = Uri.fromFile(new File(mCaptureImagePath));
        photo.setPhotoUrl(mSelectedUri);
      } else {
        photo.setPhotoUrl(mSelectedUri);
      }
      mRealFrontImageFilePath = ViewUtils.getRealPathFromURI(mSelectedUri, getActivity());
      ViewUtils.rotateImage(mSelectedUri);
      photo.setRealImagePath(mRealFrontImageFilePath);
      mPresenter.uploadGalleryImage(photo);
    }
  }

  private void enableEditPhoto() {
    final String edit = getString(R.string.edit);
    final String done = getString(R.string.done);
    initDragAndDrop();
    mPhotoAdapter.setOnDragListener(PhotoFragment.this);
    mNextCb.statusEnableButton();
    mNextCb.setTextButton(getString(R.string.summary));
    mHeader.getRightTextTv().setVisibility(View.VISIBLE);
    mHeader.getRightTextTv().setText(edit);
    mHeader.getRightTextTv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (mHeader.getRightTextTv().getText().equals(edit)) {
          mHeader.getRightTextTv().setText(done);
          mNextCb.setVisibility(View.GONE);
          mPhotoAdapter.setIsEditing(true);
          mPhotoAdapter.notifyDataSetChanged();
        } else { // done
          mHeader.getRightTextTv().setText(edit);
          mPhotos = mPhotoAdapter.getPhotoList();
          if(typePhoTo){
            mNextCb.setVisibility(View.GONE);
          }else{
            mNextCb.setVisibility(View.VISIBLE);
          }
          if (mPhotos.size() == 0) {
            mHeader.getRightTextTv().setVisibility(View.GONE);
            mNextCb.setTextButton(getString(R.string.next));
            mNextCb.statusDisabledButton();
          }
          mPhotoAdapter.setIsEditing(false);
          mPhotoAdapter.notifyDataSetChanged();
        }
      }
    });
  }

  private void initDragAndDrop() {
    ItemTouchHelper.Callback mIthCallback = new ItemTouchHelper.Callback() {
      @Override
      public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(
                ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT,
                0
        );
      }

      @Override
      public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        final int fromPosition = viewHolder.getAdapterPosition();
        final int toPosition = target.getAdapterPosition();
        int lastPosition = mPhotoAdapter.getItemCount() - 1;
        if (!mPhotoAdapter.isEditing() && fromPosition == lastPosition || toPosition == lastPosition) {
          return true;
        }
        if (fromPosition < toPosition) {
          for (int i = fromPosition; i < toPosition; i++) {
            Collections.swap(mPhotoAdapter.getPhotoList(), i, i + 1);
          }
        } else {
          for (int i = fromPosition; i > toPosition; i--) {
            Collections.swap(mPhotoAdapter.getPhotoList(), i, i - 1);
          }
        }
        mPhotoAdapter.notifyItemMoved(fromPosition, toPosition);
        return true;
      }

      @Override
      public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        return;
      }

      @Override
      public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled();
      }


      @Override
      public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        for (int i = 0; i < mPhotoAdapter.getPhotoList().size(); i++) {
          mPhotoAdapter.notifyItemChanged(i);
        }
      }
    };

    mItemTouchHelper = new ItemTouchHelper(mIthCallback);
    mItemTouchHelper.attachToRecyclerView(mPhotoRv);
  }

  @Override
  public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
    mItemTouchHelper.startDrag(viewHolder);
  }

  @Override
  public void bindImageGallery(FileDTO data) {
    mPhotos.add(data);
    mPhotoAdapter.notifyDataSetChanged();
    if (mPhotos.size() >= 1) {
      enableEditPhoto();
      initDragAndDrop();
    }
  }

  @Override
  public void bindViewForEditPhoto() {
    this.typePhoTo = true;
    mNextCb.setVisibility(View.GONE);
    mHeader.getLeftIconIv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.goToSummaryProperties(mPhotos);
      }
    });
  }
}
