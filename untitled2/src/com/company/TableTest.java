package com.company;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableTest extends AbstractTableModel {


    private List<Test> data;
    String[] columnNames = {"Id", "Название теста", "Описание теста"};

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    public Test getValueAt(int rowIndex){
        return data.get(rowIndex);
    }
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Test test = data.get(rowIndex);
        switch (columnIndex){
            case 0: return test.getIdTest();
            case 1: return test.getNameTest();
            case 2: return test.getDescriptionTest();
        }
        return null;
    }
    public TableTest() {
        BDWorker.initBD();
        this.data = BDWorker.getTests();
    }
    public void deleteTest(int rowIndex){
        BDWorker.initBD();
        BDWorker.deleteTest(getValueAt(rowIndex));
        this.fireTableDataChanged();
        updateTable();
        BDWorker.closeBD();
    }
    public void deleteAllTests(){
        BDWorker.initBD();
        BDWorker.deleteAllTests();
        this.fireTableDataChanged();

        updateTable();

        BDWorker.closeBD();
    }
    public void updateTest(Test test, int rowIndex){
        BDWorker.initBD();
        BDWorker.updateTests(test, rowIndex);
        this.fireTableDataChanged();
        updateTable();
        BDWorker.closeBD();
    }
    public void addTest(Test test){
        BDWorker.initBD();
        BDWorker.addTests(test);
        updateTable();
        this.fireTableDataChanged();
        BDWorker.closeBD();
    }
    public void updateTable(){
        BDWorker.initBD();
        data = BDWorker.getTests();
        BDWorker.closeBD();
        this.fireTableDataChanged();
    }
}
