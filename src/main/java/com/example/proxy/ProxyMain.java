package com.example.proxy;

import java.lang.reflect.Proxy;

public class ProxyMain {

    public static void main(String[] args) {
        ClassLoader classLoader = ProxyMain.class.getClassLoader();
        Hello h = (Hello) Proxy.newProxyInstance(
                classLoader, // クラスローダー
                new Class[]{Hello.class}, // プロキシが実装するインタフェース
                new HelloInvocationHandler() // InvocationHandler実装クラスのインスタンス
        );

        // プロキシ内部でInvocationHandlerが処理を実行
        String message = h.getMessage("James");
        System.out.println(message);

        System.out.println(classLoader.getClass().getName());
        System.out.println(classLoader.getParent().getClass().getName());
    }
}
