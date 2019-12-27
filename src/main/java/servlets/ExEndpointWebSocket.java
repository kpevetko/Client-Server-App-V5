package servlets;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@ServerEndpoint("/ex/{key}")
public class ExEndpointWebSocket {

    private static final Sessions sessions = Sessions.getInstance();


    @OnMessage
    public void onMessage(Session session, String message, @PathParam("key") String key) {
        List<Session> sessions = this.sessions.getSessions().get(key);
        for (Session session1 : sessions) {
            session1.getAsyncRemote().sendText(message);
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("key") String key) {

        if (!sessions.getSessions().containsKey(key)) {
            sessions.getSessions().put(key, Arrays.asList(session));
        }
        //sessions.get(key).add(session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("key") String key) {

        //sessions.getSessions().get(key).remove(session); //компилятор ругается на эту часть
        sessions.getSessions().remove(key);
    }

    @OnError
    public void onError(Session session, @PathParam("key") String key, Throwable throwable) {

        //sessions.getSessions().get(key).remove(session); //не стоит ремувать сессию, лучше убрать по ключу часть и тогда и сессия уйдет
        sessions.getSessions().remove(key);
        try {
            session.close();
        } catch (IOException ex) {

        }
    }

    public static List<String> getKeys() {
        return new ArrayList<>(sessions.getSessions().keySet());
    }

    //отправляем сообщение конкретному юзеру
    public static void send(String message, String key) {
        List<Session> session = sessions.getSessions().get(key);
        for (Session session1 : session) {
            session1.getAsyncRemote().sendText(message);
        }
    }

    //отправляем сообщение себе
    public static void sendMe(String message, String mykey) {
        List<Session> session = sessions.getSessions().get(mykey);
        for (Session session1 : session) {
            session1.getAsyncRemote().sendText(message);
        }
    }

    //отправляем сообщение всем юзерам
    //пришлось в начале вытащить список ключений
    //и пробегаясь по нему транслировать сообщение в сессии
    public static void sendAll(String message) {
        Set<String> session = sessions.getSessions().keySet();
        for (String session1 : session) {
            List<Session> session3 = sessions.getSessions().get(session1);
            for (Session session2 : session3) {
                session2.getAsyncRemote().sendText(message);
            }
        }
    }
}
