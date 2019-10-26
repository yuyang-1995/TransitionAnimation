package com.yuy.transitionanimation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.widget.ImageView;

import com.yuy.transitionanimation.R;

public class SecondActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        int resId = getIntent().getExtras().getInt("resId");
        ImageView iv = findViewById(R.id.iv);
        iv.setImageResource(resId);

        Transition transition = new Explode();

        //将某部件设置不参与转场动画, 此处排除状态栏
        transition.excludeTarget(android.R.id.statusBarBackground, true);

        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);


    }
}
