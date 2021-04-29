package com.company;

public class ResultTest {
    private int idResult;
    private int idTest;
    private String nameResult;
    private String descriptionResult;
    private int idTesters;

    public ResultTest(int idResult,int idTest, String nameResult, String descriptionResult, int idTesters) {
        this.idResult = idResult;
        this.idTest = idTest;
        this.nameResult = nameResult;
        this.descriptionResult = descriptionResult;
        this.idTesters = idTesters;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    public int getIdResult() {
        return idResult;
    }
    public int getIdTest() {
        return idTest;
    }
    public String getNameResult() {
        return nameResult;
    }
    public String getDescriptionResult() {
        return descriptionResult;
    }
    public int getIdTesters() {
        return idTesters;
    }
}
