package model;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//для хранения сессий
public class SessionsModel {

    private static SessionsModel instance;

    public Map<String, List<Session>> getSessions() {
        return sessions;
    }

    private final Map<String, List<Session>> sessions;

    public static synchronized SessionsModel getInstance() {
        if (instance == null) {
            instance = new SessionsModel();
        }
        return instance;
    }

    SessionsModel() {
        sessions = new HashMap<>();
    }

}
