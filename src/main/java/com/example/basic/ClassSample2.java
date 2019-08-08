package com.example.basic;

import java.lang.reflect.Constructor;

public class ClassSample2 {

    public static void main(String[] args) throws Exception {
        // classフィールドを利用 -> ジェネリクスが使える
        Class<Person> personClass = Person.class;

        // 引数なしコンストラクタの利用
        Constructor<Person> noArgConstructor = personClass.getConstructor();
        Person person1 = noArgConstructor.newInstance(); // キャスト不要！
        person1.showInfo();
        person1.readBook("プログラミング言語Java");

        // 引数ありコンストラクタの利用
        Constructor<Person> argsConstructor = personClass.getConstructor(int.class, String.class);
        Person person2 = argsConstructor.newInstance(999, "ジェームス"); // キャスト不要！
        person2.showInfo();
        person2.readBook("プログラミング言語Java");
    }
}
