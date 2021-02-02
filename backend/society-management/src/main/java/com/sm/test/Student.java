package com.sm.test;

import lombok.Data;

@Data
public class Student {
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private int id;
    private String name;

    @Override
    public String toString() {
        return "com.sm.test.Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
