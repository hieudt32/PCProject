package app.positiveculture.com.agent.screen.properties.createotp;

import android.widget.EditText;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.TypeFurnishing;
import app.positiveculture.com.data.enumdata.TypeTenancy;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.CreateOtpDTO;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import app.positiveculture.com.data.response.dto.SolicitorDTO;

/**
 * The CreateOtp Contract
 */
interface CreateOtpContract {

  interface Interactor extends IInteractor<Presenter> {
    void createOTP(CreateOtpDTO createOtpDTO, CommonCallback<OtpDTO> commonCallback);

    void updateOTP(String s, CreateOtpDTO mCreateOtpDTO, CommonCallback<OtpDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {

    void bindViewFromData(PropertyDTO propertyDTO);

    void bindViewFurnishing(TypeFurnishing typeFurnishing);

    void bindViewTenancy(TypeTenancy typeTenancy);

    void selectedAgent(AgentDTO selectedAgent);

    void bindSolicitorInfo(SolicitorDTO solicitorDTO);

    void showDialog(String message, final EditText viewRequestFocus);

    void bindViewForEditOTP(OtpDTO mOTP);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void gotoSelectAgent();

    void gotoSolicitor();

    void gotoFurnishing();

    void gotoTenancy();

    void createOTP(CreateOtpDTO createOtpDTO);

    void updateOTP(CreateOtpDTO createOtpDTO);

  }
}



