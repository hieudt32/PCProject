package app.positiveculture.com.agent.screen.searchlocation;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.dto.SearchLocationDTO;
import app.positiveculture.com.agent.screen.location.SearchAdapter;

/**
 * The SearchLocation Presenter
 */
public class SearchLocationPresenter extends Presenter<SearchLocationContract.View, SearchLocationContract.Interactor>
        implements SearchLocationContract.Presenter {

  private List<SearchLocationDTO> searchLocationDTOs;
  private SearchAdapter searchAdapter;

  public SearchLocationPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public SearchLocationContract.View onCreateView() {
    return SearchLocationFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
   ;
  }

  @Override
  public void goBack() {
    mContainerView.back();
  }

  @Override
  public SearchLocationContract.Interactor onCreateInteractor() {
    return new SearchLocationInteractor(this);
  }
}
