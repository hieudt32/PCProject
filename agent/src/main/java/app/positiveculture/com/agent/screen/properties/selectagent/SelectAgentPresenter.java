package app.positiveculture.com.agent.screen.properties.selectagent;

import android.support.v7.widget.RecyclerView;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.screen.properties.selectagent.adapteragent.SelectAgentAdapter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.AgentDTO;

/**
 * The SelectAgent Presenter
 */
public class SelectAgentPresenter extends Presenter<SelectAgentContract.View, SelectAgentContract.Interactor>
        implements SelectAgentContract.Presenter, SelectAgentAdapter.OnAgentClickListener {

  private final int LIMIT = 15;

  private ArrayList<AgentDTO> mAgentList = new ArrayList<>();
  private AgentDTO mSelectedAgent;
  private SelectAgentAdapter mAgentAdapter;
  private OnSelectAgentListener mOnSelectAgentListener;
  private int mOffset = 0;
  private String mSearchKey;

  private CommonCallback<List<AgentDTO>> mAgentCallBack;
  private CommonCallback<List<AgentDTO>> mMoreAgentCallBack;

  public SelectAgentPresenter(ContainerView containerView) {
    super(containerView);
  }

  public SelectAgentPresenter setOnSelectAgentListener(OnSelectAgentListener onSelectAgentListener) {
    this.mOnSelectAgentListener = onSelectAgentListener;
    return this;
  }

  @Override
  public SelectAgentContract.View onCreateView() {
    return SelectAgentFragment.getInstance();
  }

  @Override
  public void start() {
    setupCallBacks();
    getAgentList(mOffset, LIMIT, "", mAgentCallBack);
  }

  private void setupCallBacks() {
    mAgentCallBack = new CommonCallback<List<AgentDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<AgentDTO> data) {
        super.onSuccess(data);
        mAgentList = (ArrayList<AgentDTO>) data;
        if (mSelectedAgent != null) checkSelectedAgent(mAgentList);
        mAgentAdapter = new SelectAgentAdapter(mAgentList);
        if (mSelectedAgent != null) {
          mAgentAdapter.setSelectedAgent(mSelectedAgent);
        }
        mAgentAdapter.setOnAgentClickListener(SelectAgentPresenter.this);
        mView.bindData();
        mOffset += LIMIT;
      }
    };

    mMoreAgentCallBack = new CommonCallback<List<AgentDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<AgentDTO> data) {
        super.onSuccess(data);
        mAgentAdapter.loadMore(data);
        if (mSelectedAgent != null) checkSelectedAgent(data);
        mOffset += LIMIT;
        mView.onLoadMoreAgentDone(data.size() > 0);
      }
    };
  }

  private void checkSelectedAgent(List<AgentDTO> listAgent) {
    for (AgentDTO agent : listAgent) {
      if (mSelectedAgent.getId() == agent.getId()) {
        agent.setSelected(true);
      } else {
        agent.setSelected(false);
      }
    }
  }

  @Override
  public SelectAgentContract.Interactor onCreateInteractor() {
    return new SelectAgentInteractor(this);
  }

  @Override
  public void goBack() {
    back();
  }

  @Override
  public RecyclerView.Adapter getAgentAdapter() {
    return mAgentAdapter;
  }

  @Override
  public void onMoreAgentAsked() {
    getAgentList(mOffset, LIMIT, mSearchKey, mMoreAgentCallBack);
  }

  @Override
  public void searchAgent(String searchKey) {
    mSearchKey = searchKey;
    mOffset = 0;
    getAgentList(mOffset, LIMIT, mSearchKey, mAgentCallBack);
  }

  private void getAgentList(int offset, int limit, String searchKey, CommonCallback<List<AgentDTO>> callback) {
    mInteractor.getListAgent(offset, limit, mSearchKey, callback);
  }

  @Override
  public void doneSelectAgent() {
    if (mOnSelectAgentListener != null) {
      mOnSelectAgentListener.onSelected(mSelectedAgent);
    }
  }

  @Override
  public void onAgentClick(AgentDTO agentDTO) {
    mSelectedAgent = agentDTO;
    mAgentAdapter.notifyDataSetChanged();
  }

  public SelectAgentPresenter setSelectedAgent(AgentDTO mSelectedAgent) {
    this.mSelectedAgent = mSelectedAgent;
    return this;
  }

  public interface OnSelectAgentListener {
    void onSelected(AgentDTO selectedAgent);
  }

}
