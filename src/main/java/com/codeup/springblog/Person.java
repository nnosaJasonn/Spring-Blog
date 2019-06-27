//package com.codeup.springblog;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name="people")
//public class Person {
//
//    @Id
//    @GeneratedValue
//    @Column(nullable = false, columnDefinition = "INT(11) UNSIGNED")
//    private long id;
//    @Column(nullable = false)
//    private int age;
//    @Column(nullable = false)
//    private String name;
//    @Column(nullable = false, length = 2, name="reside_state")
//    private String resideState;
//
//    public Person(int age, String name, String resideState) {
//        this.age = age;
//        this.name = name;
//        this.resideState = resideState;
//    }
//}
