package com.company;

public class Tester {
    private int id;
    private String name;
    private String sex;
    private int height;
    private int weight;
    private int age;

    public Tester(int id, String name, String sex, int height, int weight, int age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.age = age;
    }

    @Override
    public String toString(){
        return getName() + ' ' + getSex() + ' ' + getHeight() + ' ' + getWeight()+ ' ' + getAge();
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSex() {
        return sex;
    }
    public int getHeight() {
        return height;
    }
    public int getWeight() {
        return weight;
    }
    public int getAge() {
        return age;
    }
}
