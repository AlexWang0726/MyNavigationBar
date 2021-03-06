package com.wonluue.mynavigationbar.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.AppBarLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wonluue.mynavigationbar.R;
import com.wonluue.mynavigationbar.utils.StatusBarUtil;
import com.wonluue.mynavigationbar.utils.UiUtils;

import java.util.ArrayList;

/**
 * Created by Echo.W on 2016/8/1 0001.
 * 原则上作为二级页面使用
 */
public abstract class LocalActivity extends SwipeBackActivity {
    public static final int LEFT_TEXTVIEW = 0;// 左侧文字
    public static final int CENTER_TEXTVIEW = 1;// 中间文字
    public static final int RIGHT_TEXTVIEW = 2;// 右侧文字
    public static final int LEFT_IMAGEVIEW = 0;// 左侧图标
    public static final int RIGHT_IMAGEVIEW = 1;// 右侧图标
    public static final int LEFT_VIEW = 0;// 左侧View
    public static final int RIGHT_VIEW = 1;// 右侧View

    public AppBarLayout toolbarDefault;
    public FrameLayout flToolbarDefault;
    public LinearLayout llToolbarLeft;
    public ImageView ivToolbarLeftImg;
    public TextView tvToolbarLeftText;
    public LinearLayout llToolbarCenter;
    public TextView tvToolbarCenterText;
    public LinearLayout llToolbarRight;
    public ImageView ivToolbarRightImg;
    public TextView tvToolbarRightText;

    public LinearLayout llBack;
    public ImageView ivBackImg;
    public TextView tvBackText;

    protected FrameLayout localFrame;

    private boolean isToolbarShow = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_local);

        initToolbarView();

        localFrame = (FrameLayout) findViewById(R.id.local_frame);
        localFrame.addView(onCreatView(),0);
    }

    /**
     * 加载toolbar布局
     */
    protected void initToolbarView() {
        toolbarDefault = (AppBarLayout) findViewById(R.id.toolbar_default);
        flToolbarDefault = (FrameLayout) findViewById(R.id.fl_toolbar_default);
        llToolbarLeft = (LinearLayout) findViewById(R.id.ll_toolbar_left);
        ivToolbarLeftImg = (ImageView) findViewById(R.id.iv_toolbar_left_img);
        tvToolbarLeftText = (TextView) findViewById(R.id.tv_toolbar_left_text);
        llToolbarCenter = (LinearLayout) findViewById(R.id.ll_toolbar_center);
        tvToolbarCenterText = (TextView) findViewById(R.id.tv_toolbar_center_text);
        llToolbarRight = (LinearLayout) findViewById(R.id.ll_toolbar_right);
        ivToolbarRightImg = (ImageView) findViewById(R.id.iv_toolbar_right_img);
        tvToolbarRightText = (TextView) findViewById(R.id.tv_toolbar_right_text);

        llBack = (LinearLayout) findViewById(R.id.ll_back);
        ivBackImg = (ImageView) findViewById(R.id.iv_back_img);
        tvBackText = (TextView) findViewById(R.id.tv_back_text);

        toolbarDefault.setPadding(0, StatusBarUtil.getStatusBarHeight(this), 0, 0);

        ivToolbarLeftImg.setVisibility(View.VISIBLE);// 显示左边图标
        ivToolbarLeftImg.setImageResource(R.mipmap.toolbar_back);
        ivBackImg.setImageResource(R.mipmap.toolbar_back);

        llToolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                UiUtils.hideInputMethod(LocalActivity.this);
            }
        });
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                UiUtils.hideInputMethod(LocalActivity.this);
            }
        });
    }

    /**
     * 设置显示toolbar
     * 默认即显示
     */
    public void showToolbar() {
        // 显示toolbar时要设置paddingTop，防止toolbar顶到屏幕顶端
        localFrame.setPadding(0, 0, 0, 0);
        toolbarDefault.setPadding(0, StatusBarUtil.getStatusBarHeight(LocalActivity.this), 0, 0);
        toolbarDefault.setVisibility(View.VISIBLE);
        flToolbarDefault.setVisibility(View.VISIBLE);
        isToolbarShow = true;
        llBack.setVisibility(View.GONE);
    }

    /**
     * 隐藏toolbar
     */
    public void hideToolbar() {
        // 隐藏toolbar时paddingTop要为0，原因是某些需求要显示banner，此时如果状态栏是纯色影响效果
        toolbarDefault.setPadding(0, 0, 0, 0);
        toolbarDefault.setVisibility(View.GONE);
        flToolbarDefault.setVisibility(View.GONE);
        isToolbarShow = false;
        localFrame.setPadding(0, StatusBarUtil.getStatusBarHeight(LocalActivity.this), 0, 0);
        llBack.setVisibility(View.VISIBLE);
    }

    /**
     * 根据参数返回对应的TextView
     *
     * @param i
     * @return 返回自己
     */
    public TextView getToolbarTextView(int i) {
        if (i == LEFT_TEXTVIEW) {
            if(isToolbarShow) {
                tvToolbarLeftText.setVisibility(View.VISIBLE);
                return tvToolbarLeftText;
            }else {
                tvBackText.setVisibility(View.VISIBLE);
                return tvBackText;
            }
        }
        if (i == CENTER_TEXTVIEW) {
            tvToolbarCenterText.setVisibility(View.VISIBLE);
            return tvToolbarCenterText;
        }
        if (i == RIGHT_TEXTVIEW) {
            tvToolbarRightText.setVisibility(View.VISIBLE);
            return tvToolbarRightText;
        }
        return null;
    }

    /**
     * 根据参数返回对应的ImageView
     *
     * @param i
     * @return
     */
    public ImageView getToolbarImageView(int i) {
        if (i == LEFT_IMAGEVIEW) {
            ivToolbarLeftImg.setVisibility(View.VISIBLE);
            return ivToolbarLeftImg;
        }
        if (i == RIGHT_IMAGEVIEW) {
            ivToolbarRightImg.setVisibility(View.VISIBLE);
            return ivToolbarRightImg;
        }
        return null;
    }

    /**
     * 根据参数返回对应的View
     *
     * @param i
     * @return
     */
    public LinearLayout getToolbarView(int i) {
        if (i == LEFT_VIEW) {
            if(isToolbarShow) {
                llToolbarLeft.setVisibility(View.VISIBLE);
                return llToolbarLeft;
            }else {
                llBack.setVisibility(View.VISIBLE);
                return llBack;
            }
        }
        if (i == RIGHT_VIEW) {
            llToolbarRight.setVisibility(View.VISIBLE);
            return llToolbarRight;
        }
        return null;
    }

    /**
     * 设置返回文字
     * 默认显示上一个Activity传过来的文本
     */
    public void showLeftText(){
        Intent intent = getIntent();
        String backName = intent.getStringExtra("back_name");
        if(isToolbarShow) {
            tvToolbarLeftText.setVisibility(View.VISIBLE);// 显示左边文字
            tvToolbarLeftText.setMaxEms(4);
            tvToolbarLeftText.setEllipsize(TextUtils.TruncateAt.END);
            tvToolbarLeftText.setSingleLine(true);
            tvToolbarLeftText.setMaxLines(1);
            if(backName != null && backName.length() > 0){
                tvToolbarLeftText.setText(backName);
            }
        }else {
            tvBackText.setVisibility(View.VISIBLE);// 显示左边文字
            tvBackText.setMaxEms(4);
            tvBackText.setEllipsize(TextUtils.TruncateAt.END);
            tvBackText.setSingleLine(true);
            tvBackText.setMaxLines(1);
            if(backName != null && backName.length() > 0){
                tvBackText.setText(backName);
            }
        }

    }

    /**
     * 设置返回文字
     * @param backName
     */
    public void showLeftText(String backName){
        if(isToolbarShow) {
            tvToolbarLeftText.setVisibility(View.VISIBLE);// 显示左边文字
            tvToolbarLeftText.setMaxEms(4);
            tvToolbarLeftText.setEllipsize(TextUtils.TruncateAt.END);
            tvToolbarLeftText.setSingleLine(true);
            tvToolbarLeftText.setMaxLines(1);
            if(backName != null && backName.length() > 0){
                tvToolbarLeftText.setText(backName);
            }
        }else {
            tvBackText.setVisibility(View.VISIBLE);// 显示左边文字
            tvBackText.setMaxEms(4);
            tvBackText.setEllipsize(TextUtils.TruncateAt.END);
            tvBackText.setSingleLine(true);
            tvBackText.setMaxLines(1);
            if(backName != null && backName.length() > 0){
                tvBackText.setText(backName);
            }
        }
    }

    /**
     * 设置返回文字
     * @param backName
     */
    public void showLeftText(int backName){
        if(isToolbarShow) {
            tvToolbarLeftText.setVisibility(View.VISIBLE);// 显示左边文字
            tvToolbarLeftText.setMaxEms(4);
            tvToolbarLeftText.setEllipsize(TextUtils.TruncateAt.END);
            tvToolbarLeftText.setSingleLine(true);
            tvToolbarLeftText.setMaxLines(1);
            tvToolbarLeftText.setText(backName);
        }else {
            tvBackText.setVisibility(View.VISIBLE);// 显示左边文字
            tvBackText.setMaxEms(4);
            tvBackText.setEllipsize(TextUtils.TruncateAt.END);
            tvBackText.setSingleLine(true);
            tvBackText.setMaxLines(1);
            tvBackText.setText(backName);
        }
    }

    /**
     * 设置标题
     * @param title
     */
    public void setTitle(String title) {
        if (tvToolbarCenterText != null) {
            tvToolbarCenterText.setText(title);
            tvToolbarCenterText.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置标题
     * @param resId
     */
    public void setTitle(int resId) {
        if (tvToolbarCenterText != null) {
            tvToolbarCenterText.setText(resId);
            tvToolbarCenterText.setVisibility(View.VISIBLE);
        }
    }

    public void setToolbarColor(int colorId){
        toolbarDefault.setBackgroundColor(getResources().getColor(colorId));
    }

    public abstract View onCreatView();
}
