package com.lqh.lichao.animator;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.cl_iv);
    }

    public void startAnimation(View view) {
        //1.-------------属性动画基础--------------------
        //iv.setTranslationX(100);//从左向右平移100px
        //iv.setScaleX(2);//X方向拉伸2倍
        //iv.setScaleY(2);//Y方向拉伸2倍
        //iv.setRotation(90);//顺时针旋转90度
        //iv.setBackgroundColor(Color.GREEN);//背景颜色设置为绿色

        //只要view里面有setXXX()方法就可以通过反射达到变化的目的
        //ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "translationX", 0f, 200f);
        //oa.setDuration(500);//动画执行时间ms
        //oa.start();

        //2.-------------多个动画同时执行----------------------
        //方法 1) 设置动画监听，同步操作其他的属性
        /*ObjectAnimator animator = ObjectAnimator.ofFloat(iv, "translationX", 0f,100f);
        animator.setDuration(300);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 监听动画回调
                animation.getAnimatedFraction();//动画执行的百分比 0~1
                float value = (float) animation.getAnimatedValue();//得到0f~100f当中的这个时间点对应的值
                iv.setScaleX(0.5f + value / 200);
                iv.setScaleY(0.5f+value/200);
            }
        });
        animator.start();*/
        //animator.setRepeatCount(2);//重新次数
        //animator.setRepeatCount(ValueAnimator.INFINITE);//一直重复
        //animator.setRepeatMode(ValueAnimator.RESTART);

        //方法 2）---------------ValueAnimator---如果只需要监听值变化就用ValueAnimator---------------
        /*ValueAnimator animator = ValueAnimator.ofFloat(0f, 200f);
        animator.setDuration(200);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();//得到0f~100f当中的这个时间点对应的值
                iv.setScaleX(0.5f+value/200);
                iv.setScaleY(0.5f+value/200);
            }
        });
        animator.start();*/

        //方法 3)
		/*PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("alpha", 1f, 0.5f);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.5f);
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.5f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(iv, holder1, holder2, holder3);
        animator.setDuration(200);
        animator.start();*/

        //4.------------------案例：实现自由落体抛物线效果---购物车动画、股指数-----------------
        /**
         * x: 匀速
         * y: 加速度 y=vt=1/2*g*t*t
         * 估值器---控制坐标PointF(x,y)
         */
        /*ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(2000);
        valueAnimator.setObjectValues(new PointF(0, 0));
        final PointF pointF = new PointF();
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                // 估值计算方法---可以在执行的过程当中干预改变属性的值---做效果：用自己的算法来控制
                //不断地去计算修改坐标
                //x匀速运动 x=v*t 为了看起来效果好我让t变成fraction*5
                pointF.x = 100f * (fraction * 5);
                //加速度 y=vt=1/2*g*t*t
                pointF.y = 10f * 0.5f * 9.8f * (fraction * 5) * (fraction * 5);
                return pointF;
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF f = (PointF) animation.getAnimatedValue();
                iv.setX(f.x);
                iv.setY(f.y);
            }
        });
        valueAnimator.start();
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);*/

        //6.---------插值器（加速器）Interpolator-----------
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "translationY", 0f,1000f);
        oa.setDuration(800);
        //oa.setInterpolator(new AccelerateInterpolator(1));
        //oa.setInterpolator(new AccelerateDecelerateInterpolator());
        //oa.setInterpolator(new BounceInterpolator());
        //oa.setInterpolator(new AnticipateInterpolator());//先向上再向下
        oa.setInterpolator(new CycleInterpolator(5));
        oa.start();
    }
}
