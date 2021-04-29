package com.company;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private static TableTest tableTest = new TableTest();
    private static TableTester tableTester = new TableTester();
    private static TableResult tableResult = new TableResult();
    private static JTable tabTest = new JTable(tableTest);
    private static JTable tabTester = new JTable(tableTester);
    private static JTable tabResult = new JTable(tableResult);

    MainFrame(String s){
        super(s);
        BDWorker.newTable();
        this.setSize(800, 600);
        this.setDefaultCloseOperation(3);
        TableRowSorter<TableModel> sorterTest = new TableRowSorter<TableModel>(tableTest);
        tabTest.setRowSorter(sorterTest);
        TableRowSorter<TableModel> sorterTester = new TableRowSorter<TableModel>(tableTester);
        tabTester.setRowSorter(sorterTester);
        TableRowSorter<TableModel> sorterResult = new TableRowSorter<TableModel>(tableResult);
        tabResult.setRowSorter(sorterResult);
        JPanel CenterTables = new JPanel(new GridLayout(1,3,1,3));
        JPanel NameTab = new JPanel(new GridLayout(5,3,1,3));
        JPanel buttonsSearch = new JPanel(new GridLayout(3,3,1,3));
        JScrollPane scrollPaneTest = new JScrollPane(tabTest);
        JScrollPane scrollPaneTester = new JScrollPane(tabTester);
        JScrollPane scrollPaneResult = new JScrollPane(tabResult);
        JLabel test = new JLabel("Тесты");
        JLabel tester = new JLabel("Тестировщики");
        JLabel result = new JLabel("Результаты тестов");
        JLabel testSearch = new JLabel("Поиск по тестам:");
        JLabel testerSearch = new JLabel("Поиск по тестировщикам:");
        JLabel resultSearch = new JLabel("Поиск по результатам:");
        JTextField filterTextTest = new JTextField(10);
        JTextField filterTextTester = new JTextField(10);
        JTextField filterTextResult = new JTextField(10);
        JButton buttonTestSearch = new JButton("Поиск");
        JButton buttonTesterSearch = new JButton("Поиск");
        JButton buttonResultSearch = new JButton("Поиск");
        JButton buttonAddTest = new JButton("Добавить тест");
        JButton buttonAddTester = new JButton("Добавить тестировщика");
        JButton buttonAddResult = new JButton("Добавить результат");
        JButton buttonDelTest = new JButton("Удалить тест");
        JButton buttonDelTester = new JButton("Удалить тестировщика");
        JButton buttonDelResult = new JButton("Удалить результат");
        JButton buttonDelAllTest = new JButton("Удалить все тесты");
        JButton buttonDelAllTester = new JButton("Удалить всех тестировщиков");
        JButton buttonDelAllResult = new JButton("Удалить все результаты");
        JButton buttonChangeTest = new JButton("Изменить данные теста");
        JButton buttonChangeTester = new JButton("Изменить данные о тестировщике");
        JButton buttonChangeResult = new JButton("Изменить данные результата");
        NameTab.add(buttonAddTest);
        NameTab.add(buttonAddTester);
        NameTab.add(buttonAddResult);
        NameTab.add(buttonDelTest);
        NameTab.add(buttonDelTester);
        NameTab.add(buttonDelResult);
        NameTab.add(buttonDelAllTest);
        NameTab.add(buttonDelAllTester);
        NameTab.add(buttonDelAllResult);
        NameTab.add(buttonChangeTest);
        NameTab.add(buttonChangeTester);
        NameTab.add(buttonChangeResult);
        NameTab.add(test);
        CenterTables.add(scrollPaneTest);
        NameTab.add(tester);
        CenterTables.add(scrollPaneTester);
        NameTab.add(result);
        CenterTables.add(scrollPaneResult);
        buttonsSearch.add(testSearch);
        buttonsSearch.add(testerSearch);
        buttonsSearch.add(resultSearch);
        buttonsSearch.add(filterTextTest);
        buttonsSearch.add(filterTextTester);
        buttonsSearch.add(filterTextResult);
        buttonsSearch.add(buttonTestSearch);
        buttonsSearch.add(buttonTesterSearch);
        buttonsSearch.add(buttonResultSearch);
        add(buttonsSearch, BorderLayout.SOUTH);
        add(NameTab, BorderLayout.NORTH);
        add(CenterTables, BorderLayout.CENTER);
        setVisible(true);
        buttonAddTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateDialogAddTest();
            }
        });
        buttonAddTester.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateDialogAddTester();
            }
        });
        buttonAddResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateDialogAddResult();
            }
        });
        buttonTestSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = filterTextTest.getText();
                if (text.length() == 0) {
                    sorterTest.setRowFilter(null);
                } else {
                    sorterTest.setRowFilter(RowFilter.regexFilter(text));
                }
            }
        });
        buttonTesterSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = filterTextTester.getText();
                if (text.length() == 0) {
                    sorterTester.setRowFilter(null);
                } else {
                    sorterTester.setRowFilter(RowFilter.regexFilter(text));
                }
            }
        });
        buttonResultSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = filterTextResult.getText();
                if (text.length() == 0) {
                    sorterResult.setRowFilter(null);
                } else {
                    sorterResult.setRowFilter(RowFilter.regexFilter(text));
                }
            }
        });
        buttonDelTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tabTest.getSelectedRow();
                if (row == -1){
                    JOptionPane.showMessageDialog(CenterTables,
                            "Вы ничего не выбрали",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                tableTest.deleteTest(row);
            }
        });
        buttonDelTester.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tabTester.getSelectedRow();
                if (row == -1){
                    JOptionPane.showMessageDialog(CenterTables,
                            "Вы ничего не выбрали",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                tableTester.deleteTester(row);
            }
        });
        buttonDelResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tabResult.getSelectedRow();
                if (row == -1){
                    JOptionPane.showMessageDialog(CenterTables,
                            "Вы ничего не выбрали",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                tableResult.deleteResult(row);
            }
        });
        buttonDelAllTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableTest.deleteAllTests();
            }
        });
        buttonDelAllTester.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableTester.deleteAllTester();
            }
        });
        buttonDelAllResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableResult.deleteAllResult();
            }
        });
        buttonChangeTester.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tabTester.getSelectedRow();
                if (row == -1){
                    JOptionPane.showMessageDialog(CenterTables,
                            "Вы ничего не выбрали",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                CreateDialogChangeTester();
            }
        });
        buttonChangeTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tabTest.getSelectedRow();
                if (row == -1){
                    JOptionPane.showMessageDialog(CenterTables,
                            "Вы ничего не выбрали",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                CreateDialogChangeTests();
            }
        });
        buttonChangeResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tabResult.getSelectedRow();
                if (row == -1){
                    JOptionPane.showMessageDialog(CenterTables,
                            "Вы ничего не выбрали",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                CreateDialogChangeResults();
            }
        });





    }
    public static void CreateDialogAddTest(){
        JDialog dialog=new JDialog();
        dialog.setModal(true);
        dialog.setTitle("Добавление теста");
        dialog.setSize(350, 250);
        GridLayout Grid = new GridLayout(2,2,1,3);
        JPanel panelInput = new JPanel();
        JPanel panel2But = new JPanel();
        panelInput.setLayout(Grid);
        JLabel labelNameTest = new JLabel("Название теста");
        JTextField fieldNameTest = new JTextField(10);
        JLabel labelDescriptionTest = new JLabel("Описание теста");
        JTextArea fieldDescriptionTest = new JTextArea();
        JButton buttonAddTest = new JButton("Добавить");
        panelInput.add(labelNameTest);
        panelInput.add(fieldNameTest);
        panelInput.add(labelDescriptionTest);
        panelInput.add(fieldDescriptionTest);
        panel2But.add(buttonAddTest);
        buttonAddTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fieldNameTest.getText().equals("") || fieldDescriptionTest.getText().equals("")){
                    JOptionPane.showMessageDialog(fieldNameTest,
                            "Не все поля заполнены",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                int id = 0;
                tableTest.addTest(new Test(id,(String)fieldNameTest.getText(),(String)fieldDescriptionTest.getText()));
                fieldDescriptionTest.setText(null);
                fieldNameTest.setText(null);
            }
        });
        dialog.add(panelInput, BorderLayout.CENTER);
        dialog.add(panel2But, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    public static void CreateDialogAddTester(){
        JDialog dialog=new JDialog();
        dialog.setModal(true);
        dialog.setTitle("Добавление тестируемого");
        dialog.setSize(350, 250);
        GridLayout Grid = new GridLayout(5,2,1,3);
        JPanel panelInput = new JPanel();
        JPanel panel2But = new JPanel();
        panelInput.setLayout(Grid);
        JLabel labelNameTester = new JLabel("Имя");
        JTextField fieldNameTester = new JTextField(10);
        JLabel labelSexTester = new JLabel("Пол");
        JTextField fieldSexTester = new JTextField(10);
        JLabel labelHeightTester = new JLabel("Рост");
        JTextField fieldHeightTester = new JTextField(10);
        JLabel labelWeightTester = new JLabel("Вес");
        JTextField fieldWeightTester = new JTextField(10);
        JLabel labelAgeTester = new JLabel("Возраст");
        JTextField fieldAgeTester = new JTextField(10);
        JButton buttonAddTest = new JButton("Добавить");
        panelInput.add(labelNameTester);
        panelInput.add(fieldNameTester);
        panelInput.add(labelSexTester);
        panelInput.add(fieldSexTester);
        panelInput.add(labelHeightTester);
        panelInput.add(fieldHeightTester);
        panelInput.add(labelWeightTester);
        panelInput.add(fieldWeightTester);
        panelInput.add(labelAgeTester);
        panelInput.add(fieldAgeTester);
        panel2But.add(buttonAddTest);
        buttonAddTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fieldAgeTester.getText().equals("") || fieldNameTester.getText().equals("") || fieldHeightTester.getText().equals("") || fieldWeightTester.getText().equals("") || fieldSexTester.getText().equals("")){
                    JOptionPane.showMessageDialog(fieldAgeTester,
                            "Не все поля заполнены",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                else {
                    try {
                        Integer.parseInt(fieldAgeTester.getText());
                        Integer.parseInt(fieldHeightTester.getText());
                        Integer.parseInt(fieldWeightTester.getText());
                        int id = 1;
                        tableTester.addTester(new Tester(id, fieldNameTester.getText(), fieldSexTester.getText(),Integer.parseInt(fieldHeightTester.getText()),Integer.parseInt(fieldWeightTester.getText()),Integer.parseInt(fieldAgeTester.getText())));
                        fieldAgeTester.setText(null);
                        fieldHeightTester.setText(null);
                        fieldWeightTester.setText(null);
                        fieldSexTester.setText(null);
                        fieldNameTester.setText(null);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(fieldAgeTester,
                                "Вес, рост и возраст должны быть целыми числами",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                }
            }
        });
        dialog.add(panelInput, BorderLayout.CENTER);
        dialog.add(panel2But, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    public static void CreateDialogAddResult(){
        JDialog dialog=new JDialog();
        dialog.setModal(true);
        dialog.setTitle("Добавление тестируемого");
        dialog.setSize(350, 250);
        GridLayout Grid = new GridLayout(4,2,1,3);
        JPanel panelInput = new JPanel();
        JPanel panel2But = new JPanel();
        panelInput.setLayout(Grid);

        JLabel labelIdTest = new JLabel("Индификатор теста");
        JComboBox comboBoxTest = new JComboBox(BDWorker.getTestersId());
        JLabel labelNameResult = new JLabel("Название результата");
        JTextField fieldNameResult = new JTextField(10);
        JLabel labelDescriptionResult = new JLabel("Описание результата");
        JTextField fieldDescriptionResult = new JTextField(10);
        JLabel labelIdTester = new JLabel("Индификатор тестировщика");
        JComboBox comboBoxTester = new JComboBox(BDWorker.getTestsId());
        JButton buttonAddTest = new JButton("Добавить");
        panelInput.add(labelIdTest);
        panelInput.add(comboBoxTest);
        panelInput.add(labelNameResult);
        panelInput.add(fieldNameResult);
        panelInput.add(labelDescriptionResult);
        panelInput.add(fieldDescriptionResult);
        panelInput.add(labelIdTester);
        panelInput.add(comboBoxTester);
        panel2But.add(buttonAddTest);
        buttonAddTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tableTester.getRowCount() == 0 || tableTest.getRowCount() == 0){
                    JOptionPane.showMessageDialog(comboBoxTest,
                            "Одна из таблиц пуста, проверьте данные",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                else if (fieldNameResult.getText().equals("") || fieldDescriptionResult.getText().equals("")){
                    JOptionPane.showMessageDialog(comboBoxTest,
                            "Не все поля заполнены",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                else {
                    int id = 1;
                    tableResult.addResult(new ResultTest(id,Integer.parseInt(String.valueOf(comboBoxTest.getSelectedItem())),(String)fieldNameResult.getText(),(String)fieldDescriptionResult.getText(),Integer.parseInt(String.valueOf(comboBoxTester.getSelectedItem()))));
                }
                fieldNameResult.setText(null);
                fieldDescriptionResult.setText(null);

            }
        });
        dialog.add(panelInput, BorderLayout.CENTER);
        dialog.add(panel2But, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    public static void CreateDialogChangeTester(){
        JDialog dialog=new JDialog();
        dialog.setModal(true);
        Tester tester = tableTester.getValueAt1(tabTester.getSelectedRow());
        dialog.setTitle("Изменение тестера");
        dialog.setSize(350, 250);
        GridLayout Grid = new GridLayout(5,2,1,3);
        JPanel panelInput = new JPanel();
        JPanel panel2But = new JPanel();
        panelInput.setLayout(Grid);
        JLabel labelNameTester = new JLabel("Имя");
        JTextField fieldNameTester = new JTextField(10);
        fieldNameTester.setText(tester.getName());
        JLabel labelSexTester = new JLabel("Пол");
        JTextField fieldSexTester = new JTextField(10);
        fieldSexTester.setText(tester.getSex());
        JLabel labelHeightTester = new JLabel("Рост");
        JTextField fieldHeightTester = new JTextField(10);
        fieldHeightTester.setText(String.valueOf(tester.getHeight()));
        JLabel labelWeightTester = new JLabel("Вес");
        JTextField fieldWeightTester = new JTextField(10);
        fieldWeightTester.setText(String.valueOf(tester.getWeight()));
        JLabel labelAgeTester = new JLabel("Возраст");
        JTextField fieldAgeTester = new JTextField(10);
        fieldAgeTester.setText(String.valueOf(tester.getAge()));
        JButton buttonChangeTester = new JButton("Изменить");
        panelInput.add(labelNameTester);
        panelInput.add(fieldNameTester);
        panelInput.add(labelSexTester);
        panelInput.add(fieldSexTester);
        panelInput.add(labelHeightTester);
        panelInput.add(fieldHeightTester);
        panelInput.add(labelWeightTester);
        panelInput.add(fieldWeightTester);
        panelInput.add(labelAgeTester);
        panelInput.add(fieldAgeTester);
        panel2But.add(buttonChangeTester);

        buttonChangeTester.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fieldAgeTester.getText().equals("") || fieldNameTester.getText().equals("") || fieldHeightTester.getText().equals("") || fieldWeightTester.getText().equals("") || fieldSexTester.getText().equals("")){
                    JOptionPane.showMessageDialog(fieldAgeTester,
                            "Одна из таблиц пуста, проверьте данные",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                else {
                    try {
                        Integer.parseInt(fieldAgeTester.getText());
                        Integer.parseInt(fieldHeightTester.getText());
                        Integer.parseInt(fieldWeightTester.getText());
                        int id_t = (int) tableTester.getValueAt(tabTester.getSelectedRow(),0);
                        tableTester.updateTester(new Tester(0, fieldNameTester.getText(), fieldSexTester.getText(),Integer.parseInt(fieldHeightTester.getText()),Integer.parseInt(fieldWeightTester.getText()),Integer.parseInt(fieldAgeTester.getText())), id_t);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(fieldAgeTester,
                                "Вес, рост и возраст должны быть целыми числами",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                }

            }
        });
        dialog.add(panelInput, BorderLayout.CENTER);
        dialog.add(panel2But, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    public static void CreateDialogChangeTests(){
        JDialog dialog=new JDialog();
        dialog.setModal(true);
        Test test = tableTest.getValueAt(tabTest.getSelectedRow());
        dialog.setTitle("Изменение теста");
        dialog.setSize(350, 250);
        GridLayout Grid = new GridLayout(5,2,1,3);
        JPanel panelInput = new JPanel();
        JPanel panel2But = new JPanel();
        panelInput.setLayout(Grid);
        JLabel labelNameTest = new JLabel("Название");
        JTextField fieldNameTest = new JTextField(10);
        fieldNameTest.setText(test.getNameTest());
        JLabel labelDescriptionTest = new JLabel("Описание теста");
        JTextArea fieldDescriptionTest = new JTextArea();
        fieldDescriptionTest.setText(test.getDescriptionTest());
        JButton buttonChangeTester = new JButton("Изменить");
        panelInput.add(labelNameTest);
        panelInput.add(fieldNameTest);
        panelInput.add(labelDescriptionTest);
        panelInput.add(fieldDescriptionTest);
        panel2But.add(buttonChangeTester);

        buttonChangeTester.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fieldNameTest.getText().equals("") || fieldDescriptionTest.getText().equals("")){
                    JOptionPane.showMessageDialog(fieldNameTest,
                            "Введены не все поля",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                else {
                    int id_t = (int) tableTest.getValueAt(tabTest.getSelectedRow(),0);
                    tableTest.updateTest(new Test(0, fieldNameTest.getText(), fieldDescriptionTest.getText()), id_t);
                }

            }
        });
        dialog.add(panelInput, BorderLayout.CENTER);
        dialog.add(panel2But, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    public static void CreateDialogChangeResults(){
        JDialog dialog=new JDialog();
        dialog.setModal(true);
        ResultTest resultTest = tableResult.getValueAt(tabResult.getSelectedRow());
        dialog.setTitle("Изменение результата");
        dialog.setSize(350, 250);
        GridLayout Grid = new GridLayout(5,2,1,3);
        JPanel panelInput = new JPanel();
        JPanel panel2But = new JPanel();
        panelInput.setLayout(Grid);
        JLabel labelNameResult = new JLabel("Название");
        JTextField fieldNameResult = new JTextField(10);
        fieldNameResult.setText(resultTest.getNameResult());
        JLabel labelDescriptionResult = new JLabel("Описание результата");
        JTextArea fieldDescriptionResult = new JTextArea();
        fieldDescriptionResult.setText(resultTest.getDescriptionResult());
        JButton buttonChangeResult = new JButton("Изменить");
        panelInput.add(labelNameResult);
        panelInput.add(fieldNameResult);
        panelInput.add(labelDescriptionResult);
        panelInput.add(fieldDescriptionResult);
        panel2But.add(buttonChangeResult);

        buttonChangeResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fieldNameResult.getText().equals("") || fieldDescriptionResult.getText().equals("")){
                    JOptionPane.showMessageDialog(fieldNameResult,
                            "Введены не все поля",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                else {
                    int id_t = (int) tableResult.getValueAt(tabResult.getSelectedRow(),0);
                    tableResult.updateResult(new ResultTest(0,resultTest.getIdTest(), fieldNameResult.getText(), fieldDescriptionResult.getText(), resultTest.getIdTesters()), id_t);
                }

            }
        });
        dialog.add(panelInput, BorderLayout.CENTER);
        dialog.add(panel2But, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

}
