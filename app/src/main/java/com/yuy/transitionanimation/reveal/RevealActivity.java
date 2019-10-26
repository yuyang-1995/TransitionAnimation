package com.yuy.transitionanimation.reveal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.transition.Scene;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.CheckBox;

import com.yuy.transitionanimation.R;

public class RevealActivity extends AppCompatActivity {
    private static final String TAG = "RevealActivity";

    Scene mScene;
    private View mView;
    private CheckBox mPlayAnimationCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal);
        mView = findViewById(R.id.view);
        mPlayAnimationCheckBox = (CheckBox) findViewById(R.id.checkBox);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onClick(View view) {
        final boolean playAnimation = mPlayAnimationCheckBox.isChecked();
        switch (view.getId()) {
            case R.id.buttonChangeVisibility:
                handleChangeVisibility(playAnimation);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void handleChangeVisibility(boolean playAnimation) {
        Log.d(TAG, "handleChangeVisibility() called with: playAnimation = [" + playAnimation + "]");
        Log.d(TAG, "handleChangeVisibility: " + mView.isShown());
        if (playAnimation) {
            if (mView.isShown()) {
                //
                revealExit();
            } else {
                //
                revealEnter();
            }
        } else {
            if (mView.isShown()) {
                mView.setVisibility(View.INVISIBLE);
            } else {
                mView.setVisibility(View.VISIBLE);
            }
        }
    }

    //揭露的效果呈现
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void revealEnter() {
        //调用ViewAnimationUtils createCircularReveal 创建属性动画

        int w = mView.getWidth();
        int h = mView.getHeight();

        int cx = w;
        int cy = h;

        int r = (int) Math.hypot(w, h);

        Animator animator = ViewAnimationUtils.createCircularReveal(mView, cx, cy, 0, r);

        mView.setVisibility(View.VISIBLE);

        animator.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void revealExit() {
        int w = mView.getWidth();
        int h = mView.getHeight();

        int cx = w;
        int cy = h;

        //
        int r = (int) Math.hypot(w, h);

        Animator animator = ViewAnimationUtils.createCircularReveal(mView, cx, cy, r, 0);

        animator.setDuration(5000);

        //为Animator 添加监听，当退出动画播放完成，将视图动画的可见性 变为不可见。
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mView.setVisibility(View.INVISIBLE);
            }
        });

        animator.start();
    }
}
