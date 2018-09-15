package com.example.administrator.xutilframwork26;

import android.content.Context;
import android.view.View;

import com.example.administrator.xutilframwork26.annotion.ContentView;
import com.example.administrator.xutilframwork26.annotion.EventBase;
import com.example.administrator.xutilframwork26.annotion.ViewInject;
import com.example.administrator.xutilframwork26.proxy.ListenerInvocationHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/24 0024.
 * 第三方容器
 *
 */

public class InjectUtils {
    public  static  void inject(Context context)
    {
        injectLayout(context);
        injectView(context);
        injectEvents(context);
    }

    /**
     * 注入事件
     * @param context
     */
    private static void injectEvents(Context context) {
        Class<?> clazz=context.getClass();
        //获取Activity里面 所有方法
        Method[] methods=clazz.getDeclaredMethods();
        //遍历Activity所有方法
        for (Method method:methods)
        {
            //获取方法上所有的注解
            Annotation[]  annotations=method.getAnnotations();
            for(Annotation annotation:annotations)
            {
                //获取注解 anntionType   OnClick  OnLongClck
                Class<?> annotationType=annotation.annotationType();
                //获取注解的注解   onClick 注解上面的EventBase
                EventBase eventBase=annotationType.getAnnotation(EventBase.class);
                if(eventBase==null)
                {
                    continue;
                }
                /*
                开始获取事件三要素  通过反射注入进去
                1 listenerSetter  返回     setOnClickListener字符串
                 */
                String listenerSetter=eventBase.listenerSetter();
                //得到 listenerType--》 View.OnClickListener.class,
                Class<?> listenerType=eventBase.listenerType();
                //callMethod--->onClick
                String callBackMethodStr=eventBase.callBackMethod();
                //方法名 与方法Method的对应关心
                Map<String,Method> methodMap=new HashMap<>();

                methodMap.put(callBackMethodStr,method);

                try {
                    Method valueMethod=annotationType.getDeclaredMethod("value");
                    int[] viewIds= (int[]) valueMethod.invoke(annotation);
                    for (int viewId:viewIds)
                    {
                        //通过反射拿到TextView
                        Method method_findViewById=clazz.getMethod("findViewById",int.class);
                        View view= (View) method_findViewById.invoke(context,viewId);
                        if(view==null)
                        {
                            continue;
                        }
                        /*
                        listenerSetter  setOnClickLitener
                        listenerType   View.OnClickListener.class
                         */
                        Method setOnClickListener=view.getClass().getMethod(listenerSetter,listenerType);

                        ListenerInvocationHandler invocationHandler=new ListenerInvocationHandler(context,methodMap);
//                        proxyy已经实现了listenerType接口
                        Object proxy= Proxy.newProxyInstance
                                (listenerType.getClassLoader(),
                                        new Class[]{listenerType},invocationHandler);
                        /**
                         * 类比 于  textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                        });
                         */
                        setOnClickListener.invoke(view,proxy);
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    public static void injectView(Context context)
    {
        Class<?> aClass=context.getClass();
        //获取到MainActivity里面所有的成员变量 包含 textView
        Field[] fields=aClass.getDeclaredFields();
        for (Field field:fields)
        {
            //得到成员变量的注解
            ViewInject viewInject=field.getAnnotation(ViewInject.class);
            if(viewInject!=null)
            {
                //拿到id  R.id.text
                int valueId=viewInject.value();
                try {
                    //View view=activity.findViewById()
                    Method method=aClass.getMethod("findViewById",int.class);
                    //反射调用方法
                    View view= (View) method.invoke(context,valueId);
                    field.setAccessible(true);
                    field.set(context,view);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public static void injectLayout(Context context)
    {
        int layoutId=0;
        Class<?> clazz=context.getClass();
        //拿到MainActivity类上面的注解
        ContentView contentView=clazz.getAnnotation(ContentView.class);
        if (contentView != null ) {
            layoutId=contentView.value();
            try {
                Method method=clazz.getMethod("setContentView",int.class);
                method.invoke(context,layoutId);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
