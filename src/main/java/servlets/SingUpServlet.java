package servlets;


import model.DataBase;
import services.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SingUp")
public class SingUpServlet extends HttpServlet {
    RequestDispatcher requestDispatcher = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = null, pass = null;
        try {
            login = request.getParameter("userLogin");
            pass = request.getParameter("userPassword");
            if (userService.userCheckToRegistration(login, pass)) {
                request.setAttribute("Answer", "NotCreated");
            } else {
                request.setAttribute("Answer", "CreatedNew");
            }
        } catch (Exception e) {
            return;
        }


        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("Answer", "NewConnect");
        requestDispatcher = request.getRequestDispatcher("views/SingUp.jsp");
        requestDispatcher.forward(request, response);

    }
}
