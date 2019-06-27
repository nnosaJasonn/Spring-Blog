//package com.codeup.springblog;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name="dogs")
//public class Dog {
//
//    @Id
//    @GeneratedValue
//    @Column(nullable = false, columnDefinition = "INT(11) UNSIGNED")
//    private long id;
//    @Column(nullable = false)
//    private int age;
//    @Column(nullable = false)
//    private String name;
//    @Column(length=2, nullable = false, name="reside_state")
//    private String resideState;
//
//    public Dog(long id, int age, String name, String resideState) {
//        this.id = id;
//        this.age = age;
//        this.name = name;
//        this.resideState = resideState;
//    }
//
//    public Dog() {
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getResideState() {
//        return resideState;
//    }
//
//    public void setResideState(String resideState) {
//        this.resideState = resideState;
//    }
//}
//
//
