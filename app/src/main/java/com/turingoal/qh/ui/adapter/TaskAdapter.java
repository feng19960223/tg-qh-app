package com.turingoal.qh.ui.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.TaskBean;
import com.turingoal.qh.constants.ConstantActivityPath;

/**
 * 我的任务adapter
 */

public class TaskAdapter extends BaseQuickAdapter<TaskBean, TgBaseViewHolder> {
    public TaskAdapter() {
        super(R.layout.item_task);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final TaskBean taskBean) {
        helper.setText(R.id.tv, taskBean.getStr());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + taskBean.getStr());
                TgSystemHelper.handleIntentWithObj(ConstantActivityPath.TASK, "taskBean", taskBean);
            }
        });
    }
}
