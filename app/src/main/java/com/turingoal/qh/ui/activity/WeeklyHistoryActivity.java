package com.turingoal.qh.ui.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.request.PostRequest;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.app.TgUserPreferences;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.common.bean.TgResponseBean;
import com.turingoal.common.constants.TgConstantGetDataType;
import com.turingoal.common.utils.TgDialogUtil;
import com.turingoal.common.utils.TgHttpCallback;
import com.turingoal.common.utils.TgHttpUtil;
import com.turingoal.common.utils.TgJsonUtil;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.WeeklyBean;
import com.turingoal.qh.constants.ConstantActivityPath;
import com.turingoal.qh.constants.ConstantUrls;
import com.turingoal.qh.ui.adapter.WeeklyAdapter;
import com.turingoal.qh.ui.adapter.WeeklyAdapter;
import com.turingoal.qh.ui.fragment.TaskFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 周报历史页面
 */

@Route(path = ConstantActivityPath.WEEKLY_HISTORY)
public class WeeklyHistoryActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView; // recyclerView
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout; // 下拉刷新
    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton; // 填写周报
    private WeeklyAdapter adapter = new WeeklyAdapter(); // adapter
    private int limitSize = 40; // 一次加载多少条数据
    private int pageSize = 1; // 第几页

    @Override
    protected int getLayoutId() {
        return R.layout.activity_weekly_history;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_daily_history);
        swipeRefreshLayout.setColorSchemeResources(colorRes);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData(TgConstantGetDataType.REFRESH);
            }
        });
        initAdapter();
        initData(TgConstantGetDataType.INIT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TaskFragment.STATE_DAILY) {
            floatingActionButton.setVisibility(View.VISIBLE);
        } else {
            floatingActionButton.setVisibility(View.GONE);
        }
    }

    /**
     * adapter
     */
    private void initAdapter() {
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM); // 动画
        recyclerView.setAdapter(adapter);
        getErrorView((ViewGroup) recyclerView.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData(TgConstantGetDataType.INIT);
            }
        });
        getEmptyView((ViewGroup) recyclerView.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData(TgConstantGetDataType.REFRESH);
            }
        });
        adapter.setEmptyView(getLoadView((ViewGroup) recyclerView.getParent()));
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                initData(TgConstantGetDataType.LOAD_MORE);
            }
        }, recyclerView);
    }

    /**
     * 数据
     */
    private void initData(final int getDataType) {
        adapter.setEmptyView(getLoadView((ViewGroup) recyclerView.getParent()));
        if (TgConstantGetDataType.INIT == getDataType || TgConstantGetDataType.REFRESH == getDataType) {
            pageSize = 1;
            swipeRefreshLayout.setRefreshing(true);
            adapter.setEnableLoadMore(false); // 这里的作用是防止下拉刷新的时候还可以上拉加载
        }
        PostRequest request = TgHttpUtil.requestPost(ConstantUrls.URL_WEEKLY_HISTORY, getHttpTaskKey());
        request.params("userId", TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_ID));
        request.params("page", pageSize); // 第几页
        request.params("limit", limitSize); // 多少条
        request.execute(new TgHttpCallback(null) {
            @Override
            public void successHandler(TgResponseBean result) { // 加载成功
                if (result.isSuccess()) {
                    if (result.getData() == null) {
                        return;
                    }
                    List<WeeklyBean> data = TgJsonUtil.jsonResultBean2List(result, WeeklyBean.class);
                    if (data != null) {
                        if (TgConstantGetDataType.INIT == getDataType || TgConstantGetDataType.REFRESH == getDataType) {
                            adapter.setNewData(data);
                            if (data.size() < limitSize) {
                                // 第一页如果不够一页就不显示没有更多数据布局
                                adapter.loadMoreEnd(true);
                            } else {
                                adapter.loadMoreComplete();
                            }
                            adapter.setEnableLoadMore(true);
                            swipeRefreshLayout.setRefreshing(false);
                            if (adapter.getItemCount() == 0) { //　没有数据
                                adapter.setEmptyView(getEmptyView((ViewGroup) recyclerView.getParent()));
                            }
                        } else if (TgConstantGetDataType.LOAD_MORE == getDataType) {
                            // 去重
                            List<WeeklyBean> dataList = new ArrayList<>();
                            dataList.addAll(data);
                            dataList.removeAll(adapter.getData());
                            adapter.addData(dataList);
                            if (data.size() < limitSize) {
                                // 第一页如果不够一页就 显示没有更多数据布局
                                adapter.loadMoreEnd(false);
                            } else {
                                adapter.loadMoreComplete();
                            }
                        }
                    }
                } else {
                    if (adapter.getData().size() == 0) {
                        adapter.setEmptyView(getEmptyView((ViewGroup) recyclerView.getParent()));
                    } else {
                        adapter.loadMoreFail(); // 获取更多数据失败，点击重试
                    }
                    TgDialogUtil.showToast(result.getMsg()); // 弹出错误信息
                }
            }

            @Override
            public void errorHandler() { // 加载错误
                if (TgConstantGetDataType.INIT == getDataType || TgConstantGetDataType.REFRESH == getDataType) {
                    adapter.setEnableLoadMore(true);
                    swipeRefreshLayout.setRefreshing(false);
                } else if (TgConstantGetDataType.LOAD_MORE == getDataType) {
                    adapter.loadMoreFail();
                }
                adapter.setEmptyView(getErrorView((ViewGroup) recyclerView.getParent()));
            }

            @Override
            public void finishHandler() { // 加载结束
                pageSize++;
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    /**
     * OnClick
     */
    @OnClick({R.id.ivStart, R.id.floatingActionButton})
    public void onViewClicked(final View view) {
        switch (view.getId()) {
            case R.id.ivStart:
                defaultFinish();
                break;
            case R.id.floatingActionButton: // 填写周报
                TgSystemHelper.handleIntent(ConstantActivityPath.DAILY_ADD);
                break;
            default:
                break;
        }
    }
}
