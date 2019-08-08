package com.example.basic;

import java.lang.reflect.Constructor;

public class ClassSample1 {

    public static void main(String[] args) throws Exception {
        // Class.forName()を利用 -> ジェネリクスが使えない
        Class personClass = Class.forName("com.example.basic.Person");

        // 引数なしコンストラクタの利用
        Constructor noArgConstructor = personClass.getConstructor();
        Person person1 = (Person) noArgConstructor.newInstance(); // キャストが必要
        person1.showInfo();
        person1.readBook("プログラミング言語Java");

        // 引数ありコンストラクタの利用
        Constructor argsConstructor = personClass.getConstructor(int.class, String.class);
        Person person2 = (Person) argsConstructor.newInstance(999, "ジェームス"); // キャストが必要
        person2.showInfo();
        person2.readBook("プログラミング言語Java");
    }
}
