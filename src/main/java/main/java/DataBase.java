package main.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;


public class DataBase {
    FileInputStream fileInputStream;
    //инициализируем специальный объект Properties
    Properties prop = new Properties();
    //поле коннекта
    Connection connection = null;
    //поля содержащие параметры входа (адрес, имя и пароль) для базы
    String DB_URL, DB_USER, DB_PASS;
    //
    Statement pstmt = null;
    //список результатов запроса
    ResultSet rs = null;

    //приватная статическая ссылка класса на его объект
    private static DataBase myDBObject;

    //открытый статический метод используемый для получения объекта нашего класса
    public static DataBase getMyDBObject() throws SQLException, IOException {
        if (myDBObject == null) {
            myDBObject = new DataBase();
        }
        return myDBObject;
    }

    public DataBase() throws SQLException, IOException {
        fileInputStream = new FileInputStream("C:\\Users\\Yury\\Documents\\IdeaProjects\\Client-Server-App-V5(web)\\src\\main\\resources\\ServerConfig.properties");
        prop.load(fileInputStream);
        DB_URL = prop.getProperty("URL");
        DB_USER = prop.getProperty("LOGIN");
        DB_PASS = prop.getProperty("PASSWORD");
        ConnectToDB();
    }

    //добавление нового юзера в таблицу (включая автоинкремент номера)
    //кстати автоинкремент можно было сделать поинтереснее, но такой способ тоже работает
    public void addNewUser(String name, String password) throws SQLException {
        String SQL = "INSERT INTO MYUSERS (userid,USERNAME,USERPASSWORD) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        Statement statement = myDBObject.getStatement();
        ResultSet rs = statement.executeQuery("select * from myusers");
        int rst = 0;
        while (rs.next()) {
            if (rs.isLast()) {
                rst = rs.getRow();
            }
        }
        preparedStatement.setInt(1, rst + 1);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, password);
        preparedStatement.executeUpdate();
    }

    public boolean userExist(String name) throws SQLException {
        String SQL = "select * from MYUSERS where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, name);
        preparedStatement.execute();
        ResultSet rsUser = preparedStatement.getResultSet();
        rsUser.next();
        if (rsUser.getRow() != 0) {
            return true;
        } else {
            return false;
        }
    }


    //коннектимся к БД
    public void ConnectToDB() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("NO");
            e.printStackTrace();
            return;
        }
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            pstmt = connection.createStatement();
            System.out.println("Connect to DB is done.");
        } catch (SQLException e) {
            System.out.println("NOT CONNECT");
            e.printStackTrace();
            return;
        }
    }

    //быстро получаем стейтмент для формирования запросов SQL
    public Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    //смотрим список юзеров (всех)
    public void getUserListDB() throws SQLException {
        rs = pstmt.executeQuery("Select * from myUsers");
        while (rs.next()) {
            System.out.printf(rs.getString(1) + " " + rs.getString(2));

        }
    }

    //Возвращаем список юзеров (всех) в листе
    public List<String> getUserListDBList() throws SQLException {
        rs = pstmt.executeQuery("Select * from myUsers");
        List<String> users= new ArrayList<>();
        //rs.next();
        while (rs.next()) {
            users.add(rs.getString(2));
        }

        return users;
    }

}
