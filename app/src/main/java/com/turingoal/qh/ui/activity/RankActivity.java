package com.turingoal.qh.ui.activity;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.common.utils.GlideUtil;
import com.turingoal.common.widget.SquareImageView;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.RankBean;
import com.turingoal.qh.constants.ConstantActivityPath;
import com.turingoal.qh.TestData;
import com.turingoal.qh.ui.adapter.RankAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 排行榜页面
 */
@Route(path = ConstantActivityPath.RANK)
public class RankActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title
    @BindView(R.id.tvExperience)
    TextView tvExperience; // 经验榜
    @BindView(R.id.tvWealth)
    TextView tvWealth; // 财富榜
    @BindView(R.id.sivAvatar1)
    SquareImageView sivAvatar1; // 第一名头像
    @BindView(R.id.tvValue1)
    TextView tvValue1; // 第一名值
    @BindView(R.id.tvName1)
    TextView tvName1; // 第一名名字
    @BindView(R.id.sivAvatar2)
    SquareImageView sivAvatar2; // 第二名头像
    @BindView(R.id.tvValue2)
    TextView tvValue2; // 第二名值
    @BindView(R.id.tvName2)
    TextView tvName2; // 第二名名字
    @BindView(R.id.sivAvatar3)
    SquareImageView sivAvatar3; // 第三名头像
    @BindView(R.id.tvValue3)
    TextView tvValue3; // 第三名值
    @BindView(R.id.tvName3)
    TextView tvName3; // 第三名名字
    @BindView(R.id.rlTop)
    RelativeLayout rlTop; // 屏幕前3的控制视图
    @BindView(R.id.rvRank)
    RecyclerView rvRank; // 其他排行榜
    public static TYPE currentType = TYPE.TYPE_EXPERIENCE; // 当前排名
    private RankAdapter mAdapter = new RankAdapter(); // adapter
    private List<RankBean> rankBeans = new ArrayList<>(); // 数据

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rank;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_rank);
        initAdapter();
        initData();
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        rvRank.setNestedScrollingEnabled(false);
        rvRank.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setEmptyView(getEmptyView((ViewGroup) rvRank.getParent()));
        rvRank.setAdapter(mAdapter);
    }

    /**
     * 加载数据
     */
    private void initData() {
        List<RankBean> rankBeanList = TestData.getRankBeans();
        rankBeans = rankBeanList;
        dataSupplement();
        dataSort();
        setRank123();
        mAdapter.setNewData(rankBeans.subList(3, 10)); // 截取3-10位的数据
    }

    /**
     * 数据补充，如果数据不足10个时，自动补全
     */
    private void dataSupplement() {
        if (rankBeans == null) {
            rankBeans = new ArrayList<>();
        }
        int i = 0;
        while (rankBeans.size() < 10) {
            RankBean rankBean = new RankBean();
            rankBean.setId("id" + i++);
            rankBean.setAvatar("");
            rankBean.setName(getString(R.string.rank_name_default));
            rankBean.setValue(0);
            rankBeans.add(rankBean);
        }
    }

    /**
     * 数据排序
     */
    private void dataSort() {
        Collections.sort(rankBeans, new Comparator<RankBean>() {
            @Override
            public int compare(RankBean rankBean1, RankBean rankBean2) {
                return rankBean2.getValue() - rankBean1.getValue();
            }
        });
    }

    /**
     * 设置冠亚季数据
     */
    private void setRank123() {
        GlideUtil.loadImage(this, rankBeans.get(0).getAvatar(), sivAvatar1);
        tvValue1.setText(String.valueOf(rankBeans.get(0).getValue()));
        tvName1.setText(rankBeans.get(0).getName());
        GlideUtil.loadImage(this, rankBeans.get(1).getAvatar(), sivAvatar2);
        tvValue2.setText(String.valueOf(rankBeans.get(1).getValue()));
        tvName2.setText(rankBeans.get(1).getName());
        GlideUtil.loadImage(this, rankBeans.get(2).getAvatar(), sivAvatar3);
        tvValue3.setText(String.valueOf(rankBeans.get(2).getValue()));
        tvName3.setText(rankBeans.get(2).getName());
    }

    /**
     * OnClick
     */
    @OnClick({R.id.ivStart, R.id.tvExperience, R.id.tvWealth})
    public void onViewClicked(final View view) {
        switch (view.getId()) {
            case R.id.ivStart:
                defaultFinish();
                break;
            case R.id.tvExperience:
                updateSwitchUi(TYPE.TYPE_EXPERIENCE);
                break;
            case R.id.tvWealth:
                updateSwitchUi(TYPE.TYPE_WEALTH);
                break;
            default:
                break;
        }
    }

    /**
     * 更新switch的ui
     */
    private void updateSwitchUi(TYPE type) {
        switch (type) {
            case TYPE_EXPERIENCE: // 经验
                tvExperience.setBackgroundResource(R.drawable.bg_switch_start_on);
                tvExperience.setTextColor(Color.parseColor("#FFFFFFFF"));
                tvWealth.setBackgroundResource(R.drawable.bg_switch_end_off);
                tvWealth.setTextColor(Color.parseColor("#FF8A8A8A"));
                break;
            case TYPE_WEALTH: // 财富
                tvExperience.setBackgroundResource(R.drawable.bg_switch_start_off);
                tvExperience.setTextColor(Color.parseColor("#FF8A8A8A"));
                tvWealth.setBackgroundResource(R.drawable.bg_switch_end_on);
                tvWealth.setTextColor(Color.parseColor("#FFFFFFFF"));
                break;
            default:
                break;
        }
        currentType = type;
        initData();
    }

    public enum TYPE { // swithch类型
        TYPE_EXPERIENCE, TYPE_WEALTH;
    }
}
