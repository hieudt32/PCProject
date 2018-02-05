
package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

public class Users {

    @SerializedName("date_joined")
    private String mDateJoined;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("first_name")
    private String mFirstName;
    @SerializedName("full_name")
    private String mFullName;
    @SerializedName("is_active")
    private Boolean mIsActive;
    @SerializedName("is_staff")
    private Boolean mIsStaff;
    @SerializedName("last_login")
    private String mLastLogin;
    @SerializedName("last_name")
    private String mLastName;
    @SerializedName("phone_country_code")
    private String mPhoneCountryCode;
    @SerializedName("phone_number")
    private String mPhoneNumber;
    @SerializedName("role")
    private String mRole;

    public String getDateJoined() {
        return mDateJoined;
    }

    public void setDateJoined(String dateJoined) {
        mDateJoined = dateJoined;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public Boolean getIsActive() {
        return mIsActive;
    }

    public void setIsActive(Boolean isActive) {
        mIsActive = isActive;
    }

    public Boolean getIsStaff() {
        return mIsStaff;
    }

    public void setIsStaff(Boolean isStaff) {
        mIsStaff = isStaff;
    }

    public String getLastLogin() {
        return mLastLogin;
    }

    public void setLastLogin(String lastLogin) {
        mLastLogin = lastLogin;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getPhoneCountryCode() {
        return mPhoneCountryCode;
    }

    public void setPhoneCountryCode(String phoneCountryCode) {
        mPhoneCountryCode = phoneCountryCode;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public String getRole() {
        return mRole;
    }

    public void setRole(String role) {
        mRole = role;
    }

}
