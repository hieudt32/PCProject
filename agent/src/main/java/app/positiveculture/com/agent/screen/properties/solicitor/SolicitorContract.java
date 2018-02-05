package app.positiveculture.com.agent.screen.properties.solicitor;

import android.support.v7.widget.RecyclerView;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import java.util.List;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.SolicitorDTO;

/**
 * The Solicitor Contract
 */
interface SolicitorContract {

  interface Interactor extends IInteractor<Presenter> {
    void getListSolicitor(int limit, int offset, String search, CommonCallback<List<SolicitorDTO>> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void bindData();

    void onLoadMoreSolicitorDone(boolean canLoadMore);

    void showDoneTextView(SolicitorDTO solicitorDTO);

    void fillSolicitorInfo(SolicitorDTO solicitorDTO);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goBack();

    void onMoreSolicitorAsked();

    void searchSolicitor(String searchKey);

    RecyclerView.Adapter getSolicitorAdapter();

    void doneSelectSolicitor();
  }
}



