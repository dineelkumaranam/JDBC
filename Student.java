package com.studentmanagement.model;
public class Student {
 private int id, year; private String name,email,course,phone;
 public Student(){}
 public Student(int id,String name,String email,String course,int year,String phone){this.id=id;this.name=name;this.email=email;this.course=course;this.year=year;this.phone=phone;}
 public Student(String name,String email,String course,int year,String phone){this(0,name,email,course,year,phone);}
 public int getId(){return id;} public void setId(int v){id=v;} public String getName(){return name;} public void setName(String v){name=v;} public String getEmail(){return email;} public void setEmail(String v){email=v;} public String getCourse(){return course;} public void setCourse(String v){course=v;} public int getYear(){return year;} public void setYear(int v){year=v;} public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
}
