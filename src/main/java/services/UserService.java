package services;

import model.DataBaseModel;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class UserService {

    private static DataBaseModel DBObject;

    public static boolean userCheckToRegistration(String login, String pass) throws SQLException {

        try {
            DBObject = DataBaseModel.getMyDBObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (DBObject.userExistRegistration(login)) {
            System.out.println("Такой пользователь уже есть");
            return true;
        } else {
            DBObject.userNew(login, pass);
            System.out.println("создан");
            return false;
        }
    }

    public static boolean userCheckToLogin(String login, String pass) throws SQLException {

        try {
            DBObject = DataBaseModel.getMyDBObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (DBObject.userExistLogin(login, pass)) {
            System.out.println("Пользователь есть, вход возможен");
            return true;
        } else {
            return false;
        }
    }

    public static List<String> userGetListOfUsers(){
        List<String> users = null;
        try {
            DBObject = DataBaseModel.getMyDBObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            users = DBObject.getUserListDBList();
        } catch (SQLException e) {
            System.out.println("невозможно отобразить список пользователей");
            e.printStackTrace();
            return null;
        }
        return users;
    }
}
