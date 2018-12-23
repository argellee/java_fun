package com.jinhigh.java.fun.base.serializable;

import java.io.Serializable;

public class Person implements Serializable
{
    int age;
    transient String address;
    double height;
    Education education;

    public Person(int age, String address, double height,Education education)
    {
        this.age = age;
        this.address = address;
        this.height = height;
        this.education = education;
    }


    static class Education implements Serializable{
        String school;
        int grade;

        public Education(String school,int grade){
            this.school = school;
            this.grade = grade;
        }

    }

}