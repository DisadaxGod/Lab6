package com.company;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableTester extends AbstractTableModel {

    private List<Tester> data;
    String[] columnNames = {"Id", "Имя", "Пол", "Рост", "Вес", "Возраст"};

    public Tester getValueAt1(int rowIndex){
        return data.get(rowIndex);
    }
    public void deleteTester(int rowIndex){
        BDWorker.initBD();
        BDWorker.newTable();
        BDWorker.deleteTester(getValueAt1(rowIndex));
        this.fireTableDataChanged();
        updateTable();
        BDWorker.closeBD();
    }
    public void deleteAllTester(){
        BDWorker.initBD();
        BDWorker.deleteAllTesters();
        this.fireTableDataChanged();
        updateTable();
        BDWorker.closeBD();
    }
    public void updateTester(Tester tester,int id){
        BDWorker.initBD();
        BDWorker.updateTester(tester,id);
        this.fireTableDataChanged();
        updateTable();
        BDWorker.closeBD();
    }
    public void addTester(Tester tester){
        BDWorker.initBD();
        BDWorker.addTesters(tester);
        updateTable();
        this.fireTableDataChanged();
        BDWorker.closeBD();
    }
    public TableTester() {
        BDWorker.initBD();
        this.data = BDWorker.getTesters();
    }
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tester tester = data.get(rowIndex);
        switch (columnIndex){
            case 0: return tester.getId();
            case 1: return tester.getName();
            case 2: return tester.getSex();
            case 3: return tester.getHeight();
            case 4: return tester.getWeight();
            case 5: return tester.getAge();
        }
        return null;
    }
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    public void updateTable(){
        BDWorker.initBD();
        data = BDWorker.getTesters();
        BDWorker.closeBD();
        this.fireTableDataChanged();
    }
}
