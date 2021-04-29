package com.company;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableResult extends AbstractTableModel {
    private List<ResultTest> data;
    String[] columnNames = {"Id","Id теста", "Название результата", "Описание","Id тестера"};
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    public ResultTest getValueAt(int rowIndex){
        return data.get(rowIndex);
    }
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ResultTest resultTest = data.get(rowIndex);
        switch (columnIndex){
            case 0: return resultTest.getIdResult();
            case 1: return resultTest.getIdTest();
            case 2: return resultTest.getNameResult();
            case 3: return resultTest.getDescriptionResult();
            case 4: return resultTest.getIdTesters();
        }
        return null;
    }
    public void deleteResult(int rowIndex){
        BDWorker.initBD();
        BDWorker.deleteResult(getValueAt(rowIndex));
        this.fireTableDataChanged();
        updateTable();
        BDWorker.closeBD();
    }
    public void deleteAllResult(){
        BDWorker.initBD();
        BDWorker.deleteAllResults();
        this.fireTableDataChanged();
        updateTable();
        BDWorker.closeBD();
    }
    public void updateResult(ResultTest resultTest,int rowIndex){
        BDWorker.initBD();
        BDWorker.updateResults(resultTest, rowIndex);
        this.fireTableDataChanged();
        updateTable();
        BDWorker.closeBD();
    }
    public void addResult(ResultTest resultTest){
        BDWorker.initBD();
        BDWorker.addResults(resultTest);
        this.fireTableDataChanged();
        updateTable();
        BDWorker.closeBD();
    }
    public TableResult() {
        BDWorker.initBD();
        this.data = BDWorker.getResults();
    }
    public void updateTable(){
        BDWorker.initBD();
        data = BDWorker.getResults();
        BDWorker.closeBD();
        this.fireTableDataChanged();
    }
}
