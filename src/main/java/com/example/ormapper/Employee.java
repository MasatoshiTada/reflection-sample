package com.example.ormapper;

@MyEntity(tableName = "employee")
public class Employee {

    @MyColumn(name = "id")
    private String id;

    @MyColumn(name = "first_name")
    private String firstName;

    @MyColumn(name = "last_name")
    private String lastName;

    public Employee() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
