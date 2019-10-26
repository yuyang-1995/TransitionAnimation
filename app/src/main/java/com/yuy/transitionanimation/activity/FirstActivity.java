package com.yuy.transitionanimation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.util.Pair;
import android.view.View;

import com.yuy.transitionanimation.R;

public class FirstActivity extends AppCompatActivity {

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onClick(View view) {

        int resId = -1;
        switch (view.getId()) {

            case R.id.iv1:
                resId = R.drawable.pic1;
                break;

            case R.id.iv2:
                resId = R.drawable.pic2;
                break;
            case R.id.iv3:
                resId = R.drawable.pic3;
                break;

            case R.id.iv4:
                resId = R.drawable.pic4;
                break;

        }

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("resId", resId);

        //创建转场对象
        Transition transition = new Explode();

        //将某部件设置不参与转场动画, 此处排除状态栏
        transition.excludeTarget(android.R.id.statusBarBackground, true);

        //将转场动画设置到该活动的Window 将动画设置到相应的Activity切换动画， 这里设置进入进出
        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);

        //为共享元素设置指定转场效果
        getWindow().setSharedElementEnterTransition(transition);


        //创建共享元素， 调用Pair.create(view, "img") 方法， 参数一 共享视图对象，参数二 自定义名称
        //注入到ActivityOptions.makeSceneTransitionAnimation 方法中
        Pair<View, String> shareElement = Pair.create(view, "img");


        //创建ActivityOptions
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, shareElement);

        startActivity(intent, options.toBundle());

    }
}
