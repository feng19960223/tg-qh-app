package com.turingoal.qh.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.qh.R;
import com.turingoal.qh.TestData;
import com.turingoal.qh.bean.HonorBean;
import com.turingoal.qh.constants.ConstantActivityPath;
import com.turingoal.qh.ui.adapter.HonorAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 头衔规则
 */

@Route(path = ConstantActivityPath.HONOR)
public class HonorActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title
    @BindView(R.id.rvHonor)
    RecyclerView rvHonor; // 等级规则
    private HonorAdapter honorAdapter = new HonorAdapter(); // adapter

    @Override
    protected int getLayoutId() {
        return R.layout.activity_honor;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_honor);
        initAdapter();
        initData();
    }

    /**
     * 初始化头衔规则adapter
     */
    private void initAdapter() {
        rvHonor.setNestedScrollingEnabled(false);
        rvHonor.setLayoutManager(new LinearLayoutManager(this));
        rvHonor.setAdapter(honorAdapter);
        honorAdapter.setEmptyView(getEmptyView((ViewGroup) rvHonor.getParent()));
    }


    /**
     * 初始化头衔规则数据
     */
    private void initData() {
        List<HonorBean> honorBeans = TestData.getHonorBeans();
        honorAdapter.setNewData(honorBeans);
    }

    /**
     * OnClick
     */
    @OnClick(R.id.ivStart)
    public void onViewClicked() {
        defaultFinish();
    }
}
