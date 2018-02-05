
package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

public class FileDTO {

    @SerializedName("alt_attribute")
    private String mAltAttribute;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("extension")
    private String mExtension;
    @SerializedName("file_name")
    private String mFileName;
    @SerializedName("file_size")
    private long mFileSize;
    @SerializedName("folder_id")
    private long mFolderId;
    @SerializedName("id")
    private long mId;
    @SerializedName("img_large")
    private String mImgLarge;
    @SerializedName("img_medium")
    private String mImgMedium;
    @SerializedName("img_medium_thumb")
    private String mImgMediumThumb;
    @SerializedName("img_small")
    private String mImgSmall;
    @SerializedName("img_small_thumb")
    private String mImgSmallThumb;
    @SerializedName("keywords")
    private String mKeywords;
    @SerializedName("mimetype")
    private String mMimetype;
    @SerializedName("path")
    private String mPath;
    @SerializedName("slug")
    private String mSlug;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public String getAltAttribute() {
        return mAltAttribute;
    }

    public void setAltAttribute(String altAttribute) {
        mAltAttribute = altAttribute;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getExtension() {
        return mExtension;
    }

    public void setExtension(String extension) {
        mExtension = extension;
    }

    public String getFileName() {
        return mFileName;
    }

    public void setFileName(String fileName) {
        mFileName = fileName;
    }

    public long getFileSize() {
        return mFileSize;
    }

    public void setFileSize(long fileSize) {
        mFileSize = fileSize;
    }

    public long getFolderId() {
        return mFolderId;
    }

    public void setFolderId(long folderId) {
        mFolderId = folderId;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getImgLarge() {
        return mImgLarge;
    }

    public void setImgLarge(String imgLarge) {
        mImgLarge = imgLarge;
    }

    public String getImgMedium() {
        return mImgMedium;
    }

    public void setImgMedium(String imgMedium) {
        mImgMedium = imgMedium;
    }

    public String getImgMediumThumb() {
        return mImgMediumThumb;
    }

    public void setImgMediumThumb(String imgMediumThumb) {
        mImgMediumThumb = imgMediumThumb;
    }

    public String getImgSmall() {
        return mImgSmall;
    }

    public void setImgSmall(String imgSmall) {
        mImgSmall = imgSmall;
    }

    public String getImgSmallThumb() {
        return mImgSmallThumb;
    }

    public void setImgSmallThumb(String imgSmallThumb) {
        mImgSmallThumb = imgSmallThumb;
    }

    public String getKeywords() {
        return mKeywords;
    }

    public void setKeywords(String keywords) {
        mKeywords = keywords;
    }

    public String getMimetype() {
        return mMimetype;
    }

    public void setMimetype(String mimetype) {
        mMimetype = mimetype;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }

    public String getSlug() {
        return mSlug;
    }

    public void setSlug(String slug) {
        mSlug = slug;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
