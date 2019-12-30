package controllers;

import services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/SingIn")
public class SingInController extends HttpServlet {
    RequestDispatcher requestDispatcher = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String myLogin = request.getParameter("log");
        String myPass = request.getParameter("pass");
        try {
            if (UserService.userCheckToLogin(myLogin, myPass)) {
                request.getSession().setAttribute("name",myLogin);
                response.sendRedirect("/Chat");
            } else {
                response.getWriter().println("NotAccepted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestDispatcher = request.getRequestDispatcher("views/SingIn.jsp");
        requestDispatcher.forward(request, response);
    }
}
