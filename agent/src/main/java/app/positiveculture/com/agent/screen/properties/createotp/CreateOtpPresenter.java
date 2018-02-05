package app.positiveculture.com.agent.screen.properties.createotp;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.agent.screen.furnishingtenancy.FurnishingTenancyPresenter;
import app.positiveculture.com.agent.screen.properties.selectagent.SelectAgentPresenter;
import app.positiveculture.com.agent.screen.properties.solicitor.SolicitorPresenter;
import app.positiveculture.com.agent.screen.seller.otpcontract.OTPContractPresenter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.TypeFurnishing;
import app.positiveculture.com.data.enumdata.TypeTenancy;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.CreateOtpDTO;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import app.positiveculture.com.data.response.dto.SolicitorDTO;

/**
 * The CreateOtp Presenter
 */
public class CreateOtpPresenter extends Presenter<CreateOtpContract.View, CreateOtpContract.Interactor>
        implements CreateOtpContract.Presenter, SelectAgentPresenter.OnSelectAgentListener,
        SolicitorPresenter.OnDoneSelectSolicitorListener, FurnishingTenancyPresenter.OnChooseTypeFurnishingOrTenancyListener {

  private AgentDTO mSelectedAgent;
  private PropertyDTO mPropertyDTO;
  private TypeTenancy mTypeTenancy = TypeTenancy.existing_tenancy;
  private TypeFurnishing mTypeFurnishing = TypeFurnishing.sold_furnished;
  private CreateOtpDTO mCreateOtpDTO;
  private SolicitorDTO mSolicitorDTO;
  private OtpDTO mOTP;


  public CreateOtpPresenter setOTP(OtpDTO mOTP) {
    this.mOTP = mOTP;
    return this;
  }

  public CreateOtpPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public CreateOtpContract.View onCreateView() {
    return CreateOtpFragment.getInstance();
  }

  @Override
  public void start() {
    if (mPropertyDTO != null) {
      mView.bindViewFromData(mPropertyDTO);
    }

    if (mOTP != null) {
      mTypeTenancy = mOTP.getTenancy();
      mTypeFurnishing = mOTP.getFurnishing();
      mView.bindViewFurnishing(mTypeFurnishing);
      mSelectedAgent = mOTP.getBuyerAgent();
      mView.selectedAgent(mSelectedAgent);
      mSolicitorDTO = mOTP.getSolicitorOrigin();
      mSolicitorDTO.setSolicitorName(mOTP.getSolicitorName());
      mSolicitorDTO.setSolicitorAddress(mOTP.getSolicitorAddress());
      mView.bindSolicitorInfo(mSolicitorDTO);
      mView.bindViewForEditOTP(mOTP);
    }
  }

  @Override
  public CreateOtpContract.Interactor onCreateInteractor() {
    return new CreateOtpInteractor(this);
  }

  @Override
  public void gotoSelectAgent() {
    new SelectAgentPresenter(mContainerView)
            .setOnSelectAgentListener(this)
            .setSelectedAgent(mSelectedAgent)
            .pushView();
  }

  @Override
  public void gotoSolicitor() {
    new SolicitorPresenter(mContainerView)
            .setSelectedSolicitor(mSolicitorDTO)
            .setOnDoneSelectSolicitorListener(this)
            .pushView();
  }

  @Override
  public void gotoFurnishing() {
    new FurnishingTenancyPresenter(mContainerView)
            .setOnChooseTypeFurnishingOrTenancyListener(this)
            .pushView();
  }

  @Override
  public void gotoTenancy() {
    new FurnishingTenancyPresenter(mContainerView)
            .setTenancy(true)
            .setOnChooseTypeFurnishingOrTenancyListener(this)
            .pushView();
  }

  @Override
  public void createOTP(CreateOtpDTO createOtpDTO) {
    mCreateOtpDTO = createOtpDTO;
    mCreateOtpDTO.setTenancy(mTypeTenancy.toString());
    mCreateOtpDTO.setFurnishing(mTypeFurnishing.toString());
    mCreateOtpDTO.setPropertyId(mPropertyDTO.getmId());
    if (mSolicitorDTO != null) {
      mCreateOtpDTO.setSolicitorId(mSolicitorDTO.getId());
      mCreateOtpDTO.setSolicitorName(mSolicitorDTO.getSolicitorName());
      mCreateOtpDTO.setSolicitorAddress(mSolicitorDTO.getSolicitorAddress());
    } else {
      mView.showDialog("Choose Solicitor", null);
      return;
    }
    if (mSelectedAgent != null) {
      mCreateOtpDTO.setBuyerAgentId(mSelectedAgent.getId());
    } else {
      mView.showDialog("Choose Agent", null);
      return;
    }
    mView.showProgress();
    mInteractor.createOTP(mCreateOtpDTO, new CommonCallback<OtpDTO>(getViewContext()) {
      @Override
      public void onSuccess(OtpDTO data) {
        super.onSuccess(data);
        mView.hideProgress();
        goToOtpContactScreen(data);
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
        mView.hideProgress();
      }
    });

  }

  private void goToOtpContactScreen(OtpDTO otpDTO) {
    new OTPContractPresenter(mContainerView)
            .setOtpDTO(otpDTO)
            .pushView();
  }

  @Override
  public void updateOTP(CreateOtpDTO createOtpDTO) {
    mCreateOtpDTO = createOtpDTO;
    mCreateOtpDTO.setTenancy(mTypeTenancy.toString());
    mCreateOtpDTO.setFurnishing(mTypeFurnishing.toString());
    mCreateOtpDTO.setPropertyId(mPropertyDTO.getmId());
    if (mSolicitorDTO != null) {
      mCreateOtpDTO.setSolicitorId(mSolicitorDTO.getId());
      mCreateOtpDTO.setSolicitorName(mSolicitorDTO.getSolicitorName());
      mCreateOtpDTO.setSolicitorAddress(mSolicitorDTO.getSolicitorAddress());
    } else {
      mView.showDialog("Choose Solicitor", null);
      return;
    }
    if (mSelectedAgent != null) {
      mCreateOtpDTO.setBuyerAgentId(1);
    } else {
      mView.showDialog("Choose Agent", null);
      return;
    }
    mView.showProgress();
    mInteractor.updateOTP(String.valueOf(mOTP.getId()), mCreateOtpDTO, new CommonCallback<OtpDTO>(getViewContext()) {
      @Override
      public void onSuccess(OtpDTO data) {
        super.onSuccess(data);
        mView.hideProgress();
        goToOtpContactScreen(data);
      }
    });
  }

  @Override
  public void onDone(SolicitorDTO solicitorDTO) {
    mSolicitorDTO = solicitorDTO;
    mView.bindSolicitorInfo(solicitorDTO);
  }

  @Override
  public void onSelected(AgentDTO agentDTO) {
    mSelectedAgent = agentDTO;
    mView.selectedAgent(mSelectedAgent);
  }

  public CreateOtpPresenter setPropertyDTO(PropertyDTO mPropertyDTO) {
    this.mPropertyDTO = mPropertyDTO;
    return this;
  }

  @Override
  public void onChooseTypeFurnishing(TypeFurnishing typeFurnishing) {
    mTypeFurnishing = typeFurnishing;
    mView.bindViewFurnishing(typeFurnishing);
  }

  @Override
  public void onChooseTypeTenancy(TypeTenancy typeTenancy) {
    mTypeTenancy = typeTenancy;
    mView.bindViewTenancy(typeTenancy);
  }
}
