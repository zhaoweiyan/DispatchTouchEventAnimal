package com.mygit.dispatchtoucheventanimal;

import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by admin on 2017/2/28.
 */

public class AnimalUtil {

    //属性动画
    public static void startAnimal(final View view, int startHeight, int endHeight) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(startHeight, endHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                int animatedValue = (Integer) animator.getAnimatedValue();
                view.getLayoutParams().height = animatedValue;
                view.requestLayout();//让view的布局params生效
            }
        });
//        valueAnimator.addListener(new SafeDesAnimListener());   动画监听，暂未设置
//        valueAnimator.setDuration(1000);   动画时间，使用默认
        valueAnimator.start();
    }
    //属性动画
    public static void startAnimalTime(final View view, int startHeight, int endHeight) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(startHeight, endHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                int animatedValue = (Integer) animator.getAnimatedValue();
                view.getLayoutParams().height = animatedValue;
                view.requestLayout();//让view的布局params生效
            }
        });
//        valueAnimator.addListener(new SafeDesAnimListener());   动画监听，暂未设置
        valueAnimator.setDuration(1000);   //动画时间，使用默认
        valueAnimator.start();
    }
}
