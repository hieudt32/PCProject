package app.positiveculture.com.agent.screen.searchproperty;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

/**
 * The SearchProperty Presenter
 */
public class SearchPropertyPresenter extends Presenter<SearchPropertyContract.View, SearchPropertyContract.Interactor>
        implements SearchPropertyContract.Presenter {

  public SearchPropertyPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public SearchPropertyContract.View onCreateView() {
    return SearchPropertyFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public SearchPropertyContract.Interactor onCreateInteractor() {
    return new SearchPropertyInteractor(this);
  }
}
