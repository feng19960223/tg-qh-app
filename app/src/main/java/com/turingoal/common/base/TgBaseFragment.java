package com.turingoal.common.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lzy.okgo.OkGo;
import com.turingoal.common.utils.TgDialogUtil;
import com.turingoal.qh.R;

import butterknife.ButterKnife;

/**
 * 基类Fragment
 */
public abstract class TgBaseFragment extends Fragment {
    protected static final String TAG = TgBaseFragment.class.getSimpleName();
    private View rootView; // 视图
    private String httpTaskKey = "HttpTaskKey_" + hashCode(); // HttpTaskKey
    protected View emptyView; // 数据没有view
    protected View errorView; // 数据出错view
    protected View loadView; // 数据加载中View
    protected int[] colorRes = new int[]{R.color.md_red_600, R.color.md_pink_600, R.color.md_purple_600, R.color.md_deep_purple_600, R.color.md_indigo_600, R.color.md_blue_600, R.color.md_light_blue_600, R.color.md_cyan_600, R.color.md_teal_600, R.color.md_green_600, R.color.md_light_green_600, R.color.md_lime_600, R.color.md_yellow_600, R.color.md_amber_600, R.color.md_orange_600, R.color.md_deep_orange_600, R.color.md_brown_600, R.color.md_grey_600, R.color.md_blue_grey_600}; // 下拉刷新控件的颜色

    /**
     * 设置布局界面的ID
     */
    protected abstract int getLayoutId();

    /**
     * 初始化组件/数据
     */
    protected abstract void initialized();

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, rootView);
            ARouter.getInstance().inject(this);
            initialized();
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        TgDialogUtil.closeLoadingProgress(); //关闭进度条
    }

    /**
     * 显示加载进度条
     */
    public void showLoadingProgress(final String titile, final String content) {
        TgDialogUtil.showLoadingProgress(this.getContext(), titile, content);
    }

    /**
     * 隐藏加载进度条
     */
    public void dismissLoadingProgress() {
        TgDialogUtil.dismissLoadingProgress();
    }

    /**
     * 默认finish
     */
    public void defaultFinish() {
        TgDialogUtil.closeLoadingProgress(); //关闭进度条
        getActivity().finish();
    }

    /**
     * HttpTaskKey
     */
    public String getHttpTaskKey() {
        return httpTaskKey;
    }

    /**
     * 销毁
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        OkGo.getInstance().cancelTag(httpTaskKey); // 取消http请求
    }

    /**
     * 没有数据view
     */
    protected View getEmptyView(final ViewGroup viewGroup) {
        if (emptyView == null) {
            emptyView = getLayoutInflater().inflate(R.layout.common_view_empty, viewGroup, false);
        }
        return emptyView;
    }

    /**
     * 每次新创建没有数据view，防止报错The specified child already has a parent. You must call removeView() on the child's parent first.
     */
    protected View getNewEmptyView(final ViewGroup viewGroup) {
        return getLayoutInflater().inflate(R.layout.common_view_empty, viewGroup, false);
    }

    /**
     * 加载数据出错view
     */
    protected View getErrorView(final ViewGroup viewGroup) {
        if (errorView == null) {
            errorView = getLayoutInflater().inflate(R.layout.common_view_error, viewGroup, false);
        }
        return errorView;
    }

    /**
     * 每次新创建加载数据出错view，防止报错The specified child already has a parent. You must call removeView() on the child's parent first.
     */
    protected View getNewErrorView(final ViewGroup viewGroup) {
        return getLayoutInflater().inflate(R.layout.common_view_error, viewGroup, false);
    }

    /**
     * 数据加载中view
     */
    protected View getNewLoadView(final ViewGroup viewGroup) {
        return getLayoutInflater().inflate(R.layout.common_view_load, viewGroup, false);
    }

    /**
     * 每次新创建数据加载中view，防止报错The specified child already has a parent. You must call removeView() on the child's parent first.
     */
    protected View getLoadView(final ViewGroup viewGroup) {
        if (loadView == null) {
            loadView = getLayoutInflater().inflate(R.layout.common_view_load, viewGroup, false);
        }
        return loadView;
    }
}
