package com.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HelloInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass().getName() + "の"
                + method.getName() + "実行中・・・");
        String name = (String) args[0];
        return "Hello, " + name + "!";
    }
}
