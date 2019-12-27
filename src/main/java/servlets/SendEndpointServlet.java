package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/send")
public class SendEndpointServlet extends HttpServlet {
    //получаем параметры ключа (никнейм) и сообщения
    //проверяем есть ли спец разделитель (:::)
    //если он есть, сплитим сообщение, делим его на 2 части и отсылаем указанному человеку
    //если нет, то отсылаем всем
    //так же перед отправкой каждое сообщение стилизуется

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String myKey = request.getParameter("key");
        String message = request.getParameter("msg");

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
                        ExEndpointWebSocket.send("<" + myKey + "> пишет вам личное сообщение: " + retval, userTo);
                        ExEndpointWebSocket.sendMe("Вы пишете личное сообщение <" + userTo + ">: " + retval, myKey);
                    }

                }

            }
        } else {
            message = "<" + myKey + "> пишет: " + message;
            ExEndpointWebSocket.sendAll(message);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println(ExEndpointWebSocket.getKeys().toString());
    }
}
