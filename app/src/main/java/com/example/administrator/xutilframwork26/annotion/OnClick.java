package com.example.administrator.xutilframwork26.annotion;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/2/27 0027.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@EventBase(listenerSetter = "setOnClickListener",
        listenerType = View.OnClickListener.class,
        callBackMethod = "onClick")
public @interface OnClick
{
    /**
     * 哪些kongji控件id 进行设置点击事件
     * @return
     */
    int[] value();
}
