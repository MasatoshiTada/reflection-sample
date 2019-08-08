package com.example.basic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class FieldSample1 {

    public static void main(String[] args) throws Exception {
        Class personClass = Class.forName("com.example.basic.Person");
        Constructor constructor = personClass.getConstructor(int.class, String.class);
        Object person = constructor.newInstance(999, "ジェームス");

        // idフィールドを表すFieldインスタンスを取得
        Field idField = personClass.getDeclaredField("id");
        // idフィールドをアクセス可能にする（privateでもOK!）
        idField.setAccessible(true);
        // personのidフィールドに値を代入（「999」を上書き）
        idField.set(person, 111);

        // idフィールドを表すFieldインスタンスを取得
        Field nameField = personClass.getDeclaredField("name");
        // idフィールドをアクセス可能にする（privateでもOK!）
        nameField.setAccessible(true);
        // personのidフィールドに値を代入（「ジェームス」を上書き）
        nameField.set(person, "James");

        // showInfoメソッド実行（呼び出しを簡略化するためにキャストを利用）
        ((Person) person).showInfo();
    }
}
