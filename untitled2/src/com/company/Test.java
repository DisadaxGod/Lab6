package com.company;

public class Test {
    private int idTest;
    private String nameTest;
    private String descriptionTest;

    public Test(int idTest, String nameTest, String descriptionTest) {
        this.idTest = idTest;
        this.nameTest = nameTest;
        this.descriptionTest = descriptionTest;
    }
    @Override
    public String toString(){
        return getNameTest()+" "+getDescriptionTest();
    }
    public int getIdTest() {
        return idTest;
    }
    public String getNameTest() {
        return nameTest;
    }
    public String getDescriptionTest() {
        return descriptionTest;
    }
}
