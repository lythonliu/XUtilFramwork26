package com.example.administrator.xutilframwork26.proxy;

import android.content.Context;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public class ListenerInvocationHandler implements InvocationHandler{
    //activity   真实对象
    private Context context;
    private Map<String,Method> methodMap;

    public ListenerInvocationHandler(Context context, Map<String, Method> methodMap) {
        this.context = context;
        this.methodMap = methodMap;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name=method.getName();
        //决定是否需要进行代理
        Method method1=methodMap.get(name);

        if(method1!=null)
        {
            return  method1.invoke(context,args);
        }else
        {
            return method.invoke(proxy,args);
        }
    }
}
