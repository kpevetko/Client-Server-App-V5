package servlets;

import main.java.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/SingUp")
public class SingUpServlet extends HttpServlet {
    DataBase DB = null;

    RequestDispatcher requestDispatcher = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = null, pass = null;
        try {
            login = request.getParameter("userLogin");
            pass = request.getParameter("userPassword");
        } catch (Exception e) {
            return;
        }
        request.setAttribute("Answer", "Created");
        try {
            System.out.println("TUT");
            DB = DataBase.getMyDBObject();

            System.out.println(login + " " + pass);
            if (DB.userExist(login)) {
                System.out.println("Такой пользователь уже есть");
                request.setAttribute("Answer", "NotCreated");

            } else {
                DB.addNewUser(login, pass);
                System.out.println("создан");
                request.setAttribute("Answer", "CreatedNew");
            }
            System.out.println("TAM");
        } catch (SQLException e) {
            System.out.println("Все плохо");
            request.setAttribute("Answer", "Wrong");
            e.printStackTrace(System.out);
        }
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("Answer", "NewConnect");
        requestDispatcher = request.getRequestDispatcher("views/SingUp.jsp");
        requestDispatcher.forward(request, response);

    }
}
