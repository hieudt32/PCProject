package app.positiveculture.com.data.response.dto;

/**
 * Created by HaiLS on 08/09/2017.
 */

public class DocumentDTO {

    private String mDocumentStatus;
    private String mDocumentName;
    private String mDocumentAction;
    private boolean mIsMissing;

    public String getDocumentStatus() {
        return mDocumentStatus;
    }

    public void setDocumentStatus(String mDocumentStatus) {
        this.mDocumentStatus = mDocumentStatus;
    }

    public String getDocumentName() {
        return mDocumentName;
    }

    public void setDocumentName(String mDocumentName) {
        this.mDocumentName = mDocumentName;
    }

    public String getDocumentAction() {
        return mDocumentAction;
    }

    public void setDocumentAction(String mDocumentAction) {
        this.mDocumentAction = mDocumentAction;
    }

    public boolean isMissing() {
        return mIsMissing;
    }

    public void setIsMissing(boolean mIsMissing) {
        this.mIsMissing = mIsMissing;
    }
}
