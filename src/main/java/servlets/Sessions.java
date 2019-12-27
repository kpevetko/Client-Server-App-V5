package servlets;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sessions {

    private static Sessions instance;

    public Map<String, List<Session>> getSessions() {
        return sessions;
    }

    private final Map<String, List<Session>> sessions;

    public static synchronized Sessions getInstance() {
        if (instance == null) {
            instance = new Sessions();
        }
        return instance;
    }

    Sessions() {
        sessions = new HashMap<>();
    }

}
