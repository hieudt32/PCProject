package app.positiveculture.com.agent.screen.clientlist.clientdetails;

import android.widget.Toast;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.MemberDTO;

/**
 * The ClientDetails Presenter
 */
public class ClientDetailsPresenter extends Presenter<ClientDetailsContract.View, ClientDetailsContract.Interactor>
        implements ClientDetailsContract.Presenter {

  private MemberDTO mClient;
  private OnNoteMemberListener mOnNoteMemberListener;

  public ClientDetailsPresenter setOnNoteMemberListener(OnNoteMemberListener mOnNoteMemberListener) {
    this.mOnNoteMemberListener = mOnNoteMemberListener;
    return this;
  }

  public ClientDetailsPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public ClientDetailsContract.View onCreateView() {
    return ClientDetailsFragment.getInstance();
  }

  @Override
  public void start() {
    if (mClient != null) {
      if (mOnNoteMemberListener != null) {
        mView.setupData(mClient, true);
      } else {
        mView.setupData(mClient, false);
      }

    }
  }

  public ClientDetailsPresenter setClient(MemberDTO client) {
    mClient = client;
    return this;
  }

  @Override
  public void saveNoteMember(String note) {
    mView.showProgress();
    mInteractor.saveNoteClient(String.valueOf(mClient.getId()), note, new CommonCallback<MemberDTO>(getViewContext()) {
      @Override
      public void onSuccess(MemberDTO data) {
        super.onSuccess(data);
        mClient = data;
        Toast.makeText(getViewContext(), "Note client success", Toast.LENGTH_SHORT).show();
        mView.setupData(data, true);
        if (mOnNoteMemberListener != null) {
          mOnNoteMemberListener.onNoteSuccess(mClient);
        }
      }
    });
  }

  @Override
  public ClientDetailsContract.Interactor onCreateInteractor() {
    return new ClientDetailsInteractor(this);
  }

  public interface OnNoteMemberListener {
    void onNoteSuccess(MemberDTO client);
  }
}
