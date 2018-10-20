package com.turingoal.qh.ui.activity;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.barteksc.pdfviewer.PDFView;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.qh.R;
import com.turingoal.qh.constants.ConstantActivityPath;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 服务协议
 */

@Route(path = ConstantActivityPath.AGREEMENT)
public class AgreementActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title
    @BindView(R.id.pdfAgreement)
    PDFView pdfAgreement; // pdf

    @Override
    protected int getLayoutId() {
        return R.layout.activity_agreement;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_agreement);
        pdfAgreement.fromAsset("agreement.pdf")
                .defaultPage(0) // 默认页面
                .enableSwipe(true) // 触摸滑动
                .swipeHorizontal(false) // 水平滑动
                .load();
    }

    /**
     * OnClick
     */
    @OnClick(R.id.ivStart)
    public void onViewClicked() {
        defaultFinish();
    }
}
