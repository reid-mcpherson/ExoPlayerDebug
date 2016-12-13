package com.google.android.exoplayer.demo.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class NullObject {

    private static class NullInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getReturnType().equals(boolean.class)) {
                return false;
            } else if (method.getReturnType().equals(int.class)) {
                return -1;
            } else {
                return null;
            }
        }
    }

    public static <T> T create(Class<T> nullObjClazz) {
        Object proxy = Proxy.newProxyInstance(nullObjClazz.getClassLoader(), new Class<?>[]{nullObjClazz}, new NullInvocationHandler());
        return nullObjClazz.cast(proxy);
    }
}