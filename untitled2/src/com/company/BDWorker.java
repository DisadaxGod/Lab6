package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BDWorker {
    public static final String Path_To_BD_File = "test.bd";
    public static final String URL = "jdbc:sqlite:" + Path_To_BD_File;
    public static Connection connection; //Для соединения с БД необходимо использовать класс Connection пакета java.sql.
    public static Statement statement; //используется для выполнения SQL-запросов
    public static void initBD(){
        try{
            connection = DriverManager.getConnection(URL);
            if (connection != null){
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("Драйвер" + meta.getDriverName());
            }
        }
        catch (SQLException ex){
            System.out.println("Ошибка подключения " + ex);
        }
    }
    public static void newTable(){
        try {
            statement = connection.createStatement();//создание экземпляра класса Statement
            statement.execute("CREATE TABLE if not exists 'testers' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'sex' text, 'weight' Integer, 'height' Integer, 'age' Integer);");// позволяет выполнять различные статичные SQL запросы, используется, когда операторы SQL возвращают более одного набора данных, более одного счетчика обновлений или и то, и другое
            System.out.println("Таблица Тестировщики существует");
            statement.execute("CREATE TABLE if not exists 'tests1' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'nameTest' text, 'descriptionTest' text);");
            System.out.println("Таблица тесты существует");
            statement.execute("CREATE TABLE if not exists 'results' ('id' INTEGER PRIMARY KEY AUTOINCREMENT,'idTest' INTEGER NOT NULL, 'nameResult' text, 'descriptionResult' text, 'idTester' INTEGER NOT NULL, FOREIGN KEY (idTester) REFERENCES testers (id) FOREIGN KEY (idTest) REFERENCES tests1 (id));");
            System.out.println("Таблица результаты существует");
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void addTesters(Tester tester){
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO testers('name','sex','weight','height','age')" + "VALUES(?, ?, ?, ?, ?);");
            statement.setObject(1,tester.getName());
            statement.setObject(2,tester.getSex());
            statement.setObject(3,tester.getWeight());
            statement.setObject(4,tester.getHeight());
            statement.setObject(5,tester.getAge());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<Tester> getTesters() {
        List<Tester> listTesters = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM testers");
            while ((resultSet.next())){
                listTesters.add(new Tester(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5),resultSet.getInt(6)));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listTesters;
    }
    public static void addTests(Test test){

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO tests1('nameTest','descriptionTest')" + "VALUES(?, ?);");
            statement.setObject(1,test.getNameTest());
            statement.setObject(2,test.getDescriptionTest());

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<Test> getTests() {
        List<Test> listTests = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tests1");
            while ((resultSet.next())){
                listTests.add(new Test(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTests;
    }
    public static void addResults(ResultTest resultTest){
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO results('idTest','nameResult','descriptionResult','idTester')" + "VALUES(?, ?, ?, ?);");
            statement.setObject(1,resultTest.getIdTest());
            statement.setObject(2,resultTest.getNameResult());
            statement.setObject(3,resultTest.getDescriptionResult());
            statement.setObject(4,resultTest.getIdTesters());

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<ResultTest> getResults() {
        List<ResultTest> listResultTest = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM results");
            while ((resultSet.next())){
                listResultTest.add(new ResultTest(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3), resultSet.getString(4),resultSet.getInt(5)));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listResultTest;
    }


    public static void deleteAllTesters(){
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM testers");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteTester(Tester tester){
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM testers WHERE id = " + tester.getId());
            System.out.println("ydaleno");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteAllTests(){
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM tests1");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteTest(Test test){
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM tests1 WHERE id = " + test.getIdTest());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteAllResults(){
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM results");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteResult(ResultTest resultTest){
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM results WHERE id = " + resultTest.getIdResult());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateTester(Tester tester, int id){
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE testers SET name = ?, sex = ?, weight = ?, height = ?, age = ? WHERE id = ?;" );
            statement.setObject(1,tester.getName());
            statement.setObject(2,tester.getSex());
            statement.setObject(3,tester.getWeight());
            statement.setObject(4,tester.getHeight());
            statement.setObject(5,tester.getAge());
            statement.setObject(6,id);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateTests(Test test, int id){
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE tests1 SET nameTest=?,descriptionTest=? WHERE id = ?");
            statement.setObject(1,test.getNameTest());
            statement.setObject(2,test.getDescriptionTest());
            statement.setObject(3,id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateResults(ResultTest resultTest, int id){
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE results SET nameResult=?,descriptionResult=? WHERE id = ?");
            statement.setObject(1,resultTest.getNameResult());
            statement.setObject(2,resultTest.getDescriptionResult());
            statement.setObject(3,id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeBD()
    {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Соединения закрыты");
    }
    public static String[] getTestsId(){
        try {
            initBD();
            Statement statement = connection.createStatement();
            List<String> id = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT id FROM tests1");
            while (resultSet.next()){
                id.add(resultSet.getString("id"));
            }
            String[] id1 = new String[id.size()];
            id.toArray(id1);
            statement.close();
            return id1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[0];
    }
    public static String[] getTestersId(){
        try {
            initBD();
            Statement statement = connection.createStatement();
            List<String> id = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT id FROM testers");
            while (resultSet.next()){
                id.add(resultSet.getString("id"));
            }
            String[] id1 = new String[id.size()];
            id.toArray(id1);
            return id1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[0];
    }
}
