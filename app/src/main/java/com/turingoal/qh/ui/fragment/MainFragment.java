package com.turingoal.qh.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.request.PostRequest;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.app.TgUserPreferences;
import com.turingoal.common.base.TgBaseFragment;
import com.turingoal.common.bean.TgResponseBean;
import com.turingoal.common.utils.TgDialogUtil;
import com.turingoal.common.utils.TgHttpCallback;
import com.turingoal.common.utils.TgHttpUtil;
import com.turingoal.common.utils.TgJsonUtil;
import com.turingoal.qh.R;
import com.turingoal.qh.TestData;
import com.turingoal.qh.bean.BillboardBean;
import com.turingoal.qh.bean.DynamicBean;
import com.turingoal.qh.bean.FunItemBean;
import com.turingoal.qh.bean.WebBean;
import com.turingoal.qh.constants.ConstantActivityPath;
import com.turingoal.qh.constants.ConstantUrls;
import com.turingoal.qh.ui.adapter.BillboardAdapter;
import com.turingoal.qh.ui.adapter.DynamicAdapter;
import com.turingoal.qh.ui.adapter.MainFunAdapter;
import com.turingoal.qh.ui.adapter.WebAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首页
 */

public class MainFragment extends TgBaseFragment {
    @BindView(R.id.ivStart)
    ImageView ivStart; // 返回按钮
    @BindView(R.id.tvTitle)
    TextView tvTitle; // 标题
    @BindView(R.id.rvFun)
    RecyclerView rvFun; // 主菜单
    @BindView(R.id.rvBillboard)
    RecyclerView rvBillboard; // 公告牌
    @BindView(R.id.rvWeb)
    RecyclerView rvWeb; // 常用网址
    @BindView(R.id.rvDynamic)
    RecyclerView rvDynamic; // 公司动态
    private MainFunAdapter mAdapter = new MainFunAdapter(); // adapter
    private static final int SPAN_COUNT = 4; // 功能列数
    private List<FunItemBean> funItemBeanList = new ArrayList<>(); // 功能菜单数据list
    private BillboardAdapter billboardAdapter = new BillboardAdapter(); // 公告牌adapter
    private WebAdapter webAdapter = new WebAdapter(); // 常用网址adapter
    private DynamicAdapter dynamicAdapter = new DynamicAdapter(); // 公司动态adapter

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initialized() {
        ivStart.setVisibility(View.INVISIBLE);
        tvTitle.setText(R.string.toolbar_main);

        initFunItemData();
        initFunItemAdapter();

        initBillboardAdapter();
        initWebAdapter();
        initDynamicAdapter();

        initErrorAndEmptyView();

        initBillboardData();
        initWebData();
        initDynamicData();
    }

    /**
     * 初始化菜单数据
     */
    private void initFunItemData() {
        funItemBeanList.add(new FunItemBean(R.drawable.ic_mall, R.string.fun_mall, ConstantActivityPath.MALL));
        funItemBeanList.add(new FunItemBean(R.drawable.ic_rank, R.string.fun_rank, ConstantActivityPath.RANK));
        funItemBeanList.add(new FunItemBean(R.drawable.ic_leave, R.string.fun_leave, ConstantActivityPath.LEAVE));
        funItemBeanList.add(new FunItemBean(R.drawable.ic_overtime, R.string.fun_overtime, ConstantActivityPath.OVERTIME));
    }

    /**
     * 初始化菜单adapter
     */
    private void initFunItemAdapter() {
        rvFun.setLayoutManager(new GridLayoutManager(getContext(), SPAN_COUNT));
        rvFun.setAdapter(mAdapter);
        mAdapter.setNewData(funItemBeanList);
    }

    private View errorViewBillboard;
    private View emptyViewBillboard;
    private View errorViewWeb;
    private View emptyViewWeb;
    private View errorViewDynamic;
    private View emptyViewDynamic;

    private void initErrorAndEmptyView() {
        errorViewBillboard = getNewErrorView((ViewGroup) rvBillboard.getParent());
        errorViewBillboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initBillboardData();
            }
        });
        emptyViewBillboard = getNewEmptyView((ViewGroup) rvBillboard.getParent());
        emptyViewBillboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initBillboardData();
            }
        });
        errorViewWeb = getNewErrorView((ViewGroup) rvWeb.getParent());
        errorViewWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initWebData();
            }
        });
        emptyViewWeb = getNewEmptyView((ViewGroup) rvWeb.getParent());
        emptyViewWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initWebData();
            }
        });
        errorViewDynamic = getNewErrorView((ViewGroup) rvDynamic.getParent());
        errorViewDynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDynamicData();
            }
        });
        emptyViewDynamic = getNewEmptyView((ViewGroup) rvDynamic.getParent());
        emptyViewDynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDynamicData();
            }
        });
    }

    /**
     * 初始化公告牌adapter
     */
    private void initBillboardAdapter() {
        rvBillboard.setNestedScrollingEnabled(false);
        rvBillboard.setLayoutManager(new LinearLayoutManager(getContext()));
        rvBillboard.setAdapter(billboardAdapter);
        billboardAdapter.setEmptyView(getNewLoadView((ViewGroup) rvBillboard.getParent()));
    }


    /**
     * 初始化公告牌数据
     */
    private void initBillboardData() {
        billboardAdapter.setEmptyView(getNewLoadView((ViewGroup) rvBillboard.getParent()));
        PostRequest request = TgHttpUtil.requestPost(ConstantUrls.URL_MAIN_BILLBOARD, getHttpTaskKey());
        request.params("userId", TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_ID));
        request.execute(new TgHttpCallback(null) {
            @Override
            public void successHandler(TgResponseBean result) { // 加载成功
                if (result.isSuccess()) {
                    if (result.getData() == null) {
                        return;
                    }
                    List<BillboardBean> data = TgJsonUtil.jsonResultBean2List(result, BillboardBean.class);
                    if (data != null) {
                        billboardAdapter.setNewData(data);
                    }
                    if (billboardAdapter.getItemCount() == 0) { //　没有数据
                        billboardAdapter.setEmptyView(emptyViewBillboard);
                    }
                } else {
                    billboardAdapter.setEmptyView(errorViewBillboard);
                    TgDialogUtil.showToast(result.getMsg()); // 弹出错误信息
                }

            }

            @Override
            public void errorHandler() { // 加载错误
                billboardAdapter.setEmptyView(errorViewBillboard);
            }
        });
    }

    /**
     * 初始化常用网址adapter
     */
    private void initWebAdapter() {
        rvWeb.setNestedScrollingEnabled(false);
        rvWeb.setLayoutManager(new LinearLayoutManager(getContext()));
        rvWeb.setAdapter(webAdapter);
        webAdapter.setEmptyView(getNewLoadView((ViewGroup) rvWeb.getParent()));
    }

    /**
     * 初始化常用网址数据
     */
    private void initWebData() {
        webAdapter.setEmptyView(getNewLoadView((ViewGroup) rvWeb.getParent()));
        PostRequest request = TgHttpUtil.requestPost(ConstantUrls.URL_MAIN_WEB, getHttpTaskKey());
        request.params("userId", TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_ID));
        request.execute(new TgHttpCallback(null) {
            @Override
            public void successHandler(TgResponseBean result) { // 加载成功
                if (result.isSuccess()) {
                    if (result.getData() == null) {
                        return;
                    }
                    List<WebBean> data = TgJsonUtil.jsonResultBean2List(result, WebBean.class);
                    if (data != null) {
                        webAdapter.setNewData(data);
                    }
                    if (webAdapter.getItemCount() == 0) { //　没有数据
                        webAdapter.setEmptyView(emptyViewWeb);
                    }
                } else {
                    webAdapter.setEmptyView(errorViewWeb);
                    TgDialogUtil.showToast(result.getMsg()); // 弹出错误信息
                }

            }

            @Override
            public void errorHandler() { // 加载错误
                webAdapter.setEmptyView(errorViewWeb);
            }
        });
    }

    /**
     * 初始化公司动态adapter
     */
    private void initDynamicAdapter() {
        rvDynamic.setNestedScrollingEnabled(false);
        rvDynamic.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDynamic.setAdapter(dynamicAdapter);
        dynamicAdapter.setEmptyView(getNewLoadView((ViewGroup) rvDynamic.getParent()));
    }

    /**
     * 初始化公司动态数据
     */
    private void initDynamicData() {
        dynamicAdapter.setEmptyView(getNewLoadView((ViewGroup) rvDynamic.getParent()));
        PostRequest request = TgHttpUtil.requestPost(ConstantUrls.URL_MAIN_DYNAMIC, getHttpTaskKey());
        request.params("userId", TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_ID));
        request.execute(new TgHttpCallback(null) {
            @Override
            public void successHandler(TgResponseBean result) { // 加载成功
                if (result.isSuccess()) {
                    if (result.getData() == null) {
                        return;
                    }
                    List<DynamicBean> data = TgJsonUtil.jsonResultBean2List(result, DynamicBean.class);
                    if (data != null) {
                        dynamicAdapter.setNewData(data);
                    }
                    if (dynamicAdapter.getItemCount() == 0) { //　没有数据
                        dynamicAdapter.setEmptyView(emptyViewDynamic);
                    }
                } else {
                    dynamicAdapter.setEmptyView(errorViewDynamic);
                    TgDialogUtil.showToast(result.getMsg()); // 弹出错误信息
                }

            }

            @Override
            public void errorHandler() { // 加载错误
                dynamicAdapter.setEmptyView(errorViewDynamic);
            }
        });
    }

    /**
     * OnClick
     */
    @OnClick({R.id.tvBillboardMore, R.id.tvWebMore, R.id.tvDynamicMore, R.id.cvSystemDetails})
    public void onViewClicked(final View view) {
        switch (view.getId()) {
            case R.id.tvBillboardMore: // 公告牌
                TgSystemHelper.handleIntent(ConstantActivityPath.BILLBOARD);
                break;
            case R.id.tvWebMore: // 常用网址
                TgSystemHelper.handleIntent(ConstantActivityPath.WEB);
                break;
            case R.id.tvDynamicMore: // 公司动态
                TgSystemHelper.handleIntent(ConstantActivityPath.DYNAMIC);
                break;
            case R.id.cvSystemDetails: // 规章制度
                TgSystemHelper.handleIntent(ConstantActivityPath.SYSTEM);
                break;
            default:
                break;
        }
    }
}
