package com.example.basic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MethodSample1 {

    public static void main(String[] args) throws Exception {
        Class personClass = Class.forName("com.example.basic.Person");
        Constructor constructor = personClass.getConstructor(int.class, String.class);
        Object person = constructor.newInstance(999, "ジェームス");

        // showInfoメソッドを表すMethodインスタンスを取得
        Method showInfoMethod = personClass.getMethod("showInfo");
        // showInfoメソッドを実行する（引数はメソッドを実行するPersonインスタンス）
        showInfoMethod.invoke(person);

        // readBookメソッドを表すMethodインスタンスを取得
        Method readBookMethod = personClass.getMethod("readBook", String.class);
        // readBookメソッドを実行する（引数はメソッドを実行するPersonインスタンス）
        readBookMethod.invoke(person, "プログラミング言語Java");
    }
}
