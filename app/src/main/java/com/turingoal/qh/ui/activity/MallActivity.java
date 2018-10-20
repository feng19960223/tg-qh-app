package com.turingoal.qh.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.MallBackBean;
import com.turingoal.qh.bean.MallLifeBean;
import com.turingoal.qh.bean.MallStaminaBean;
import com.turingoal.qh.constants.ConstantActivityPath;
import com.turingoal.qh.TestData;
import com.turingoal.qh.ui.adapter.MallBackAdapter;
import com.turingoal.qh.ui.adapter.MallLifeAdapter;
import com.turingoal.qh.ui.adapter.MallStaminaAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 道具商城页面
 */
@Route(path = ConstantActivityPath.MALL)
public class MallActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title
    @BindView(R.id.rvMallLife)
    RecyclerView rvMallLife; // 生命道具
    @BindView(R.id.rvMallStamina)
    RecyclerView rvMallStamina; // 体力道具
    @BindView(R.id.rvMallBack)
    RecyclerView rvMallBack; // 回城道具
    private static final int SPAN_COUNT = 2; // 道具列数
    private MallLifeAdapter mallLifeAdapter = new MallLifeAdapter(); // 生命道具adapter
    private MallStaminaAdapter mallStaminaAdapter = new MallStaminaAdapter(); // 体力道具adapter
    private MallBackAdapter mallBackAdapter = new MallBackAdapter(); // 回城道具adapter

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mall;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_mall);
        initMallLifeAdapter();
        initMallLifeData();
        initMallStaminaAdapter();
        initMallStaminaData();
        initMallBackAdapter();
        initMallBackData();
    }

    /**
     * 初始化生命道具adapter
     */
    private void initMallLifeAdapter() {
        rvMallLife.setNestedScrollingEnabled(false);
        rvMallLife.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
        rvMallLife.setAdapter(mallLifeAdapter);
        mallLifeAdapter.setEmptyView(getNewEmptyView((ViewGroup) rvMallLife.getParent()));
    }

    /**
     * 初始化生命道具数据
     */
    private void initMallLifeData() {
        List<MallLifeBean> mallLifeBeans = TestData.getMallLifeBeans();
        mallLifeAdapter.setNewData(mallLifeBeans);
    }

    /**
     * 初始化体力道具adapter
     */
    private void initMallStaminaAdapter() {
        rvMallStamina.setNestedScrollingEnabled(false);
        rvMallStamina.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
        rvMallStamina.setAdapter(mallStaminaAdapter);
        mallStaminaAdapter.setEmptyView(getNewEmptyView((ViewGroup) rvMallStamina.getParent()));
    }

    /**
     * 初始化体力道具数据
     */
    private void initMallStaminaData() {
        List<MallStaminaBean> mallStaminaBeans = TestData.getMallStaminaBeans();
        mallStaminaAdapter.setNewData(mallStaminaBeans);
    }

    /**
     * 初始化回城道具adapter
     */
    private void initMallBackAdapter() {
        rvMallBack.setNestedScrollingEnabled(false);
        rvMallBack.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
        rvMallBack.setAdapter(mallBackAdapter);
        mallBackAdapter.setEmptyView(getNewEmptyView((ViewGroup) rvMallBack.getParent()));
    }

    /**
     * 初始化回城道具数据
     */
    private void initMallBackData() {
        List<MallBackBean> mallBackBeans = TestData.getMallBackBeans();
        mallBackAdapter.setNewData(mallBackBeans);
    }

    /**
     * OnClick
     */
    @OnClick({R.id.ivStart, R.id.cvMallLook})
    public void onViewClicked(final View view) {
        switch (view.getId()) {
            case R.id.ivStart:
                defaultFinish();
                break;
            case R.id.cvMallLook: // 我的道具
                TgSystemHelper.handleIntent(ConstantActivityPath.MALL_MY);
                break;
            default:
                break;
        }
    }
}
