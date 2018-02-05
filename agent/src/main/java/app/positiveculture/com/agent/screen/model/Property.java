package app.positiveculture.com.agent.screen.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 8/17/2017.
 */

public class Property {
    @SerializedName("ThumbPropertyUrl")
    String mThumbPropertyUrl;
    @SerializedName("NameProperty")
    String mNameProperty;
    @SerializedName("LocationProperty")
    String mLocationProperty;
    @SerializedName("AgentProperty")
    String mAgentProperty;
    @SerializedName("PriceProperty")
    String mPriceProperty;
    @SerializedName("AcreageProperty")
    String mAcreageProperty;
    @SerializedName("StatusProperty")
    String mStatusProperty;

    public Property() {
    }

    public String getmThumbPropertyUrl() {
        return mThumbPropertyUrl;
    }

    public void setmThumbPropertyUrl(String mThumbPropertyUrl) {
        this.mThumbPropertyUrl = mThumbPropertyUrl;
    }

    public String getmNameProperty() {
        return mNameProperty;
    }

    public void setmNameProperty(String mNameProperty) {
        this.mNameProperty = mNameProperty;
    }

    public String getmLocationProperty() {
        return mLocationProperty;
    }

    public void setmLocationProperty(String mLocationProperty) {
        this.mLocationProperty = mLocationProperty;
    }

    public String getmAgentProperty() {
        return mAgentProperty;
    }

    public void setmAgentProperty(String mAgentProperty) {
        this.mAgentProperty = mAgentProperty;
    }

    public String getmPriceProperty() {
        return mPriceProperty;
    }

    public void setmPriceProperty(String mPriceProperty) {
        this.mPriceProperty = mPriceProperty;
    }

    public String getmAcreageProperty() {
        return mAcreageProperty;
    }

    public void setmAcreageProperty(String mAcreageProperty) {
        this.mAcreageProperty = mAcreageProperty;
    }

    public String getmStatusProperty() {
        return mStatusProperty;
    }

    public void setmStatusProperty(String mStatusProperty) {
        this.mStatusProperty = mStatusProperty;
    }
}
