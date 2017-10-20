package com.lqh.lichao.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;

/**
 * 仿淘宝添加购物车动画
 */
public class TaobaoActivity extends AppCompatActivity {

    private View first_view;
    private View second_view;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taobao);

        first_view = findViewById(R.id.first);
        second_view = findViewById(R.id.second);

        bt = (Button)findViewById(R.id.bt);
    }

    public void startFirstAnim(View v) {
        //显示first_view：1.透明度动画；2.缩放动画；3.翻转动画
        ObjectAnimator firstAlphaAnim = ObjectAnimator.ofFloat(first_view, "alpha", 1.0f, 0.7f);
        firstAlphaAnim.setDuration(300);
        //旋转动画
        ObjectAnimator firstRotationXAnim = ObjectAnimator.ofFloat(first_view, "rotationX", 0f,20f);
        firstRotationXAnim.setDuration(300);
        //再旋转回来
        ObjectAnimator firstResumeRotationXAnim = ObjectAnimator.ofFloat(first_view, "rotationX", 20f, 0f);
        firstResumeRotationXAnim.setDuration(300);
        firstResumeRotationXAnim.setStartDelay(300);//延迟第一次旋转动画的时间，在这之后再执行
        //缩放动画
        ObjectAnimator firstScaleXAnim = ObjectAnimator.ofFloat(first_view, "ScaleX", 1.0f, 0.8f);
        firstScaleXAnim.setDuration(300);
        ObjectAnimator firstScaleYAnim = ObjectAnimator.ofFloat(first_view, "ScaleY", 1.0f, 0.8f);
        firstScaleYAnim.setDuration(300);
        //由于缩放造成离顶部有一个距离，需要平移
        ObjectAnimator firstTranslationYAnim = ObjectAnimator.ofFloat(first_view, "translationY", 0f, -0.1f*first_view.getHeight());
        firstTranslationYAnim.setDuration(300);
        //第二个view和第一个view动画同时开始执行
        ObjectAnimator secondTranslationYAnim = ObjectAnimator.ofFloat(second_view, "translationY", second_view.getHeight(), 0f);
        secondTranslationYAnim.setDuration(300);
        secondTranslationYAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                second_view.setVisibility(View.VISIBLE);
                bt.setClickable(false);
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.playTogether(firstScaleXAnim,firstScaleYAnim,firstAlphaAnim,firstRotationXAnim,firstResumeRotationXAnim,firstTranslationYAnim,secondTranslationYAnim);
        set.start();
    }
}
