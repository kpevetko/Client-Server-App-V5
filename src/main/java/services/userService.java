package services;

import model.DataBase;

import java.io.IOException;
import java.sql.*;

public class userService {

    private static DataBase DBObject;

    public static boolean userCheckToRegistration(String login, String pass) throws SQLException {

        try {
            DBObject = DataBase.getMyDBObject();
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
            DBObject = DataBase.getMyDBObject();
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
}
