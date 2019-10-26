package com.yuy.transitionanimation.scene;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import com.yuy.transitionanimation.R;

public class SceneActivity extends AppCompatActivity {


    private ViewGroup sceneRoot;

    private Scene mOverViewScene, mInfoScene;


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);


        //场景ViewRoot 对象
        sceneRoot = findViewById(R.id.scene_root);

        //定义转换前的场景对象
       mOverViewScene = Scene.getSceneForLayout(sceneRoot, R.layout.scene_overview, getBaseContext());


       //定义转换后的场景对象
        mInfoScene = Scene.getSceneForLayout(sceneRoot, R.layout.sence_info, getBaseContext());

        //首先跳转mOverViewScene 场景， 使用默认转场效果
        TransitionManager.go(mOverViewScene);

    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnInfo:
                //创建转场对象
                Transition transition = TransitionInflater.from(getBaseContext())
                        .inflateTransition(R.transition.teansition);
                //按指定效果进行转场
                TransitionManager.go(mInfoScene, transition);

                break;
            case R.id.btnClose:
                TransitionManager.go(mOverViewScene);

                break;
        }

    }
}
