package controllers;


import services.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/SingUp")
public class SingUpController extends HttpServlet {
    RequestDispatcher requestDispatcher = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String myLogin = request.getParameter("log");
        String myPass = request.getParameter("pass");
        try {
            if (UserService.userCheckToRegistration(myLogin, myPass)) {
                response.getWriter().println("NotCreated");
            } else {
                response.getWriter().println("CreateNew");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestDispatcher = request.getRequestDispatcher("views/SingUp.jsp");
        requestDispatcher.forward(request, response);
    }
}
