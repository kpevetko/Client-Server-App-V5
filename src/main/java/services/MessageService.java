package services;

import model.DataBaseModel;
import model.SessionsModel;

import javax.websocket.Session;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class MessageService {
    private static final SessionsModel SESSIONS_MODEL = SessionsModel.getInstance();

    //проверяем есть ли спец разделитель (:::)
    //если он есть, сплитим сообщение, делим его на 2 части и отсылаем указанному человеку
    //если нет, то отсылаем всем
    //так же перед отправкой каждое сообщение стилизуется
    public static void sendMessage(String message, String myKey) throws SQLException {
        if (message.indexOf(":::") > 0) {
            int i = 1;
            String userTo = null;
            for (String retval : message.split(":::", 2)) {

                if (i == 1) {
                    userTo = retval;
                    i++;
                } else if (i == 2) {
                    //добавляем проверку на не пустое сообщение
                    if (retval.equals("")) {
                        break;
                    } else {
                        //добавляем проверку чтобы не отправлялись сообщения адресованые самому себе
                        if (myKey.equals(userTo)) {
                            sendMe("Нельзя писать сообщение самому себе", myKey);
                        } else {
                            send("<" + myKey + "> пишет вам личное сообщение: " + retval, userTo);
                            sendMe("Вы пишете личное сообщение <" + userTo + ">: " + retval, myKey);
                            DataBaseModel.writeToLog(myKey + " пишет личное сообщение " + userTo + " " + retval);
                        }
                    }

                }

            }
        } else {
            message = "<" + myKey + "> пишет: " + message;
            sendAll(message);
            DataBaseModel.writeToLog(message);
        }
    }

    //отправляем сообщение конкретному юзеру
    public static void send(String message, String key) {
        List<Session> session = SESSIONS_MODEL.getSessions().get(key);
        for (Session session1 : session) {
            session1.getAsyncRemote().sendText(message);
        }
    }

    //отправляем сообщение себе
    public static void sendMe(String message, String mykey) {
        List<Session> session = SESSIONS_MODEL.getSessions().get(mykey);
        for (Session session1 : session) {
            session1.getAsyncRemote().sendText(message);
        }
    }

    //отправляем сообщение всем юзерам
    //пришлось в начале вытащить список ключений
    //и пробегаясь по нему транслировать сообщение в сессии
    public static void sendAll(String message) {
        Set<String> session = SESSIONS_MODEL.getSessions().keySet();
        for (String session1 : session) {
            List<Session> session3 = SESSIONS_MODEL.getSessions().get(session1);
            for (Session session2 : session3) {
                session2.getAsyncRemote().sendText(message);
            }
        }
    }

}
