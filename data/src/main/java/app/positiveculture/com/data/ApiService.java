package app.positiveculture.com.data;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

import app.positiveculture.com.data.enumdata.PropertiesCountDTO;
import app.positiveculture.com.data.enumdata.ResidencyStatus;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.CEAProfileDTO;
import app.positiveculture.com.data.response.dto.CeaSmsDTO;
import app.positiveculture.com.data.response.dto.CreateMemberDTO;
import app.positiveculture.com.data.response.dto.CreateOtpDTO;
import app.positiveculture.com.data.response.dto.CreatePropertyDTO;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.MemberVerifySmsDTO;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import app.positiveculture.com.data.response.dto.PropertyDisplayDTO;
import app.positiveculture.com.data.response.dto.RegisterAgentDTO;
import app.positiveculture.com.data.response.dto.ResponseDTO;
import app.positiveculture.com.data.response.dto.SettingDTO;
import app.positiveculture.com.data.response.dto.SolicitorDTO;
import app.positiveculture.com.data.response.dto.User;
import app.positiveculture.com.data.response.dto.Users;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Restful Services
 * Created by hungdn on 8/07/2016.
 */
public interface ApiService {

  @GET("frontend-user/setting")
  Call<ResponseDTO<SettingDTO>> getAppSetting();

  @GET("cea/check-cea")
  Call<ResponseDTO<CEAProfileDTO>> checkCEANumber(@Query("cea_no") String mCeaNumber);

  @POST("agent/register")
  Call<ResponseDTO<User>> createPassword(@Body RegisterAgentDTO registerAgentDTO);

  @FormUrlEncoded
  @POST("agent/send-sms-agent")
  Call<ResponseDTO<ResponseDTO>> sendSMSAgent(@Field("cea_no") String mCeaNumber);

  @FormUrlEncoded
  @POST("agent/check-sms-code")
  Call<ResponseDTO<CeaSmsDTO>> checkSMSAgent(
          @Field("cea_no") String mCeaNumber,
          @Field("code") String code);

  @POST("member/verify/")
  Call<ResponseDTO<MemberVerifySmsDTO>> sendSMSMember();

  @GET("member/verify/")
  Call<ResponseDTO<MemberVerifySmsDTO>> checkSMSMember(@Query("code") String code);

  @GET("frontend-user/forgot")
  Call<ResponseDTO<JsonElement>> forgotPassword(@Query("email") String email);

  @FormUrlEncoded
  @POST("frontend-user/login")
  Call<ResponseDTO<User>> login(@Field("email") String email, @Field("password") String password);

  @FormUrlEncoded
  @POST("frontend-user/change-password")
  Call<ResponseDTO<Users>> changePassword(@Field("password") String newPass,
                                          @Field("verify") String confirmPass);

  @FormUrlEncoded
  @POST("frontend-user/update-profile")
  Call<ResponseDTO<User>> saveNric(@Field("nric") String mNric);


  @POST("frontend-user/update-profile")
  @FormUrlEncoded
  Call<ResponseDTO<User>> saveNricProfile(@Field("nric") String nric,
                                          @Field("nric_front_image") long idNricFront,
                                          @Field("nric_back_image") long idNricBack);

  @POST("frontend-user/update-profile")
  @FormUrlEncoded
  Call<ResponseDTO<User>> saveIdUser(@Field("id_number") String idNumber,
                                     @Field("id_type") String idType,
                                     @Field("nric_front_image") long idFront,
                                     @Field("nric_back_image") long idBack);

  @FormUrlEncoded
  @POST("frontend-user/update-profile")
  Call<ResponseDTO<User>> saveBankDetail(@Field("bank_name") String bankName,
                                         @Field("bank_account_number") String bankAccountNumber,
                                         @Field("bank_account_type") String bankAccountType);

  @FormUrlEncoded
  @POST("frontend-user/update-profile")
  Call<ResponseDTO<User>> saveSetupProfile(@Field("avatar") long idAvatar,
                                           @Field("email") String email,
                                           @Field("full_name") String fullName,
                                           @Field("phone_country_code") String countryCode,
                                           @Field("phone_number") String phoneNumber);

  @FormUrlEncoded
  @POST("frontend-user/update-profile")
  Call<ResponseDTO<User>> editProfileMember(@Field("avatar") long idUserAvatar,
                                            @Field("full_name") String fullName,
                                            @Field("phone_country_code") String phoneCountryCode,
                                            @Field("phone_number") String phoneNumber,
                                            @Field("date_of_birth") String dateOfBirth,
                                            @Field("email") String email,
                                            @Field("residency_status") ResidencyStatus residencyStt,
                                            @Field("address_floor") String floor,
                                            @Field("address_unit") String unit,
                                            @Field("address_building") String building,
                                            @Field("address_street_line_one") String street1,
                                            @Field("address_street_line_two") String street2,
                                            @Field("address_postal_code") String postalCode,
                                            @Field("address_country") String country,
                                            @Field("company_name") String companyName,
                                            @Field("company_phone_country_code") String comCountryCode,
                                            @Field("company_phone_number") String companyPhone,
                                            @Field("company_email") String companyEmail,
                                            @Field("company_address_floor") String compFloor,
                                            @Field("company_address_unit") String compUnit,
                                            @Field("company_address_building") String compBuilding,
                                            @Field("company_address_street_line_one") String compStreet1,
                                            @Field("company_address_street_line_two") String compStreet2,
                                            @Field("company_address_postal_code") String comPostalCode,
                                            @Field("company_address_country") String compCountry);

  @GET("member/members")
  Call<ResponseDTO<List<MemberDTO>>> getListOwner(@Query("limit") int limit,
                                                  @Query("offset") int offset,
                                                  @Query("search") String name);


  @POST("property/properties/")
  Call<ResponseDTO<PropertyDTO>> createProperty(@Body CreatePropertyDTO propertyDTO);

  @DELETE("property/properties/{propertyId}/")
  Call<ResponseDTO<ResponseDTO>> deleteProperty(@Path("propertyId") String propertyId);

  @GET("agent/agents/")
  Call<ResponseDTO<List<AgentDTO>>> getListAgent(@Query("offset") int offset,
                                                 @Query("limit") int limit,
                                                 @Query("search") String search);

  @GET("agent/check-member-contact")
  Call<ResponseDTO<MemberDTO>> checkClient(@Query("id_type") String idType,
                                           @Query("id_number") String idNumber);

  @FormUrlEncoded
  @POST("agent/add-member-contact")
  Call<ResponseDTO<AgentDTO>> addExistingClient(@Field("member_id") Long clientId);

  @POST("agent/create-new-member")
  Call<ResponseDTO<MemberDTO>> createNewClient(@Body CreateMemberDTO createMemberDTO);

  @GET("frontend-user/logout")
  Call<ResponseDTO<ErrorDTO>> logout();

  @GET("frontend-user/get-profile")
  Call<ResponseDTO<MemberDTO>> getProfileMember();

  @GET("frontend-user/get-profile")
  Call<ResponseDTO<AgentDTO>> getProfileAgent();

  @GET("property/properties/")
  Call<ResponseDTO<List<PropertyDTO>>> getListProperty(@Query("limit") int limit,
                                                       @Query("offset") int offset,
                                                       @Query("filter_type") String filter);

  @GET("property/properties/member/{idClient}/")
  Call<ResponseDTO<List<PropertyDTO>>> getListPropertyClient(@Path("idClient") String idClient,
                                                             @Query("limit") int limit,
                                                             @Query("offset") int offset,
                                                             @Query("filter_type") String filter);

  @GET("property/properties-count/")
  Call<ResponseDTO<PropertiesCountDTO>> getPropertiesCount();

  @GET("otp/solicitor")
  Call<ResponseDTO<List<SolicitorDTO>>> getListSolicitor(@Query("limit") int limit,
                                                         @Query("offset") int offset,
                                                         @Query("search") String search);

  @Multipart
  @POST("media/image-upload/")
  Call<ResponseDTO<FileDTO>> imageUpload(@Part MultipartBody.Part image);


  @GET("property/property-title-display/")
  Call<ResponseDTO<PropertyDisplayDTO>> getPropertyDisplay(@Query("floor") String floor,
                                                           @Query("unit") String unit,
                                                           @Query("building") String building,
                                                           @Query("street_line_one") String streetOne,
                                                           @Query("street_line_two") String streetTwo,
                                                           @Query("country") String country,
                                                           @Query("postal_code") String postalCode);

  @POST("otp/otps/")
  Call<ResponseDTO<OtpDTO>> createOTP(@Body CreateOtpDTO createOtpDTO);


  @POST("otp/send-out-otp/{otpId}/")
  Call<ResponseDTO<OtpDTO>> sendOutOTP(@Path("otpId") String idOtp);

  @FormUrlEncoded
  @POST("otp/sign-otp/{otpId}/")
  Call<ResponseDTO<OtpDTO>> signOTP(@Path("otpId") String idOTP,
                                    @Field("signature_image") long idSignImage);

  @GET("otp/get-otp-by-property/{property_id}/")
  Call<ResponseDTO<OtpDTO>> getOtpProperty(@Path("property_id") String propertyId);

  @POST("otp/agent-verify-signing/{otpId}/")
  Call<ResponseDTO<OtpDTO>> agentVerifySigning(@Path("otpId") String idOTP);

  @POST("otp/seller-reject-signing/{otpId}/")
  Call<ResponseDTO<OtpDTO>> sellerRejectSigning(@Path("otpId") String idOTP);

  @PUT("otp/otps/{otpId}/")
  Call<ResponseDTO<OtpDTO>> updateOTP(@Path("otpId") String idOTP,
                                      @Body CreateOtpDTO createOtpDTO);

  @POST("otp/buyer-agent-assign-buyers/{otpId}/")
  Call<ResponseDTO<OtpDTO>> buyerAgentAssignBuyer(@Path("otpId") String idOTP,
                                                  @Body JsonObject propertyBuyer);

  @POST("otp/buyer-agent-reassign-buyers/{otpId}/")
  Call<ResponseDTO<OtpDTO>> buyerAgentReassignBuyer(@Path("otpId") String idOTP,
                                                    @Body JsonObject propertyBuyer);

  @FormUrlEncoded
  @POST("otp/extend-otp/{otpId}/")
  Call<ResponseDTO<OtpDTO>> buyerExtendOTP(@Path("otpId") String idOTP,
                                           @Field("otp_date_completion") String dateCompletion);

  @DELETE("otp/otps/{otpId}/")
  Call<ResponseDTO<ResponseDTO>> sellerAgentDeleteOTP(@Path("otpId") String idOTP);

  @FormUrlEncoded
  @POST("agent/set-note-member-contact/{memberId}/")
  Call<ResponseDTO<MemberDTO>> agentNoteMember(@Path("memberId") String idMember,
                                           @Field("note") String note);

}