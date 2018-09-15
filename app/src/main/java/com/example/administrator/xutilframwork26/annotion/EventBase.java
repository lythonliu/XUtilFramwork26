package com.example.administrator.xutilframwork26.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/2/27 0027.
 * 设计目的是 对所有的事件点击 进行扩展
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface EventBase {
    /**
     * 设置监听的方法
     * @return
     */
    String listenerSetter();

    /**
     * 事件类型
     * @return
     */
    Class<?> listenerType();
    /**
     * 回调方法
     * 事件被触发后，执行回调方法名称
     */
    String callBackMethod();
}
