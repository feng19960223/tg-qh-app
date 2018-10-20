package com.turingoal.qh.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.qh.R;
import com.turingoal.qh.TestData;
import com.turingoal.qh.bean.LevelBean;
import com.turingoal.qh.constants.ConstantActivityPath;
import com.turingoal.qh.ui.adapter.LevelAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 等级规则
 */

@Route(path = ConstantActivityPath.LEVEL)
public class LevelActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title
    @BindView(R.id.rvLevel)
    RecyclerView rvLevel; // 等级规则
    private LevelAdapter levelAdapter = new LevelAdapter(); // adapter

    @Override
    protected int getLayoutId() {
        return R.layout.activity_level;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_level);
        initAdapter();
        initData();
    }

    /**
     * 初始化等级规则adapter
     */
    private void initAdapter() {
        rvLevel.setNestedScrollingEnabled(false);
        rvLevel.setLayoutManager(new LinearLayoutManager(this));
        rvLevel.setAdapter(levelAdapter);
        levelAdapter.setEmptyView(getEmptyView((ViewGroup) rvLevel.getParent()));
    }


    /**
     * 初始化等级规则数据
     */
    private void initData() {
        List<LevelBean> levelBeans = TestData.getLevelBeans();
        levelAdapter.setNewData(levelBeans);
    }

    /**
     * OnClick
     */
    @OnClick(R.id.ivStart)
    public void onViewClicked() {
        defaultFinish();
    }
}
