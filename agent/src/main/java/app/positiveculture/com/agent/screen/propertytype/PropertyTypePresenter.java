package app.positiveculture.com.agent.screen.propertytype;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.agent.screen.location.LocationPresenter;
import app.positiveculture.com.data.enumdata.TypeProperty;
import app.positiveculture.com.data.response.dto.CreatePropertyDTO;

/**
 * The PropertyType Presenter
 */
public class PropertyTypePresenter extends Presenter<PropertyTypeContract.View, PropertyTypeContract.Interactor>
        implements PropertyTypeContract.Presenter {

  private CreatePropertyDTO createPropertyDTO;

  public PropertyTypePresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public PropertyTypeContract.View onCreateView() {
    return PropertyTypeFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public void goBack() {
    mContainerView.back();
  }

  @Override
  public void goToLocation(TypeProperty mSelected) {
    createPropertyDTO.setmType(mSelected);
    new LocationPresenter(mContainerView)
            .setCreatePropertyDTO(createPropertyDTO)
            .pushView();
  }

  @Override
  public PropertyTypeContract.Interactor onCreateInteractor() {
    return new PropertyTypeInteractor(this);
  }

  public PropertyTypePresenter setCreatePropertyDTO(CreatePropertyDTO createPropertyDTO) {
    this.createPropertyDTO=createPropertyDTO;
    return this;
  }
}
