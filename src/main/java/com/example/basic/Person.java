package com.example.basic;

public class Person {

    private int id;

    private String name;

    public Person() {
        this(101, "名無し");
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void showInfo() {
        System.out.println(id + "の" + name + "さんです。");
    }

    public void readBook(String bookTitle) {
        System.out.println(name + "さんが" + bookTitle + "を読みます。");
    }
}
