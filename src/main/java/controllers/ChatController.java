package controllers;

import services.MessageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Chat")
public class ChatController extends HttpServlet {
    RequestDispatcher requestDispatcher = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String myKey = request.getParameter("key");
        String message = request.getParameter("msg");
        MessageService.sendMessage(message, myKey);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("name")== null){
        }else {
            requestDispatcher = request.getRequestDispatcher("views/Chat.jsp");
            requestDispatcher.forward(request, response);
            response.getWriter().println(ExEndpointWebController.getKeys().toString());
        }
    }
}
