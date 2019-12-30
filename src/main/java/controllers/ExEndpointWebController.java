package controllers;

import model.SessionsModel;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@ServerEndpoint("/ex/{key}")
public class ExEndpointWebController {

    private static final SessionsModel SESSIONS_MODEL = SessionsModel.getInstance();


    @OnMessage
    public void onMessage(Session session, String message, @PathParam("key") String key) {
        List<Session> sessions = this.SESSIONS_MODEL.getSessions().get(key);
        for (Session session1 : sessions) {
            session1.getAsyncRemote().sendText(message);
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("key") String key) {

        if (!SESSIONS_MODEL.getSessions().containsKey(key)) {
            SESSIONS_MODEL.getSessions().put(key, Arrays.asList(session));
        }
        //sessions.get(key).add(session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("key") String key) {

        //sessions.getSessions().get(key).remove(session); //компилятор ругается на эту часть
        SESSIONS_MODEL.getSessions().remove(key);
    }

    @OnError
    public void onError(Session session, @PathParam("key") String key, Throwable throwable) {

        //sessions.getSessions().get(key).remove(session); //не стоит ремувать сессию, лучше убрать по ключу часть и тогда и сессия уйдет
        SESSIONS_MODEL.getSessions().remove(key);
        try {
            session.close();
        } catch (IOException ex) {

        }
    }

    public static List<String> getKeys() {
        return new ArrayList<>(SESSIONS_MODEL.getSessions().keySet());
    }


}
