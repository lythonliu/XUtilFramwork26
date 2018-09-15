package com.example.administrator.xutilframwork26.annotion;

import android.widget.AdapterView;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/2/27 0027.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@EventBase(listenerSetter ="setOnItemClickListener"
        ,listenerType = AdapterView.OnItemClickListener.class,callBackMethod = "onItemClick")
public @interface OnItemClick {
    int[] value();
}
