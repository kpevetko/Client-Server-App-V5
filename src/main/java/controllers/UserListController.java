package controllers;

import model.DataBaseModel;
import model.SessionsModel;
import services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UserList")
public class UserListController extends HttpServlet {
    RequestDispatcher requestDispatcher = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //возвращает список юзеров в онлайне
        SessionsModel ses = SessionsModel.getInstance();
        System.out.println(ses.getSessions().size());
        List ll = new ArrayList<>(ses.getSessions().keySet());
        for (int i = 0; i<ll.size(); i++ ) {
            response.getWriter().println(ll.get(i));
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> users = UserService.userGetListOfUsers();
        request.setAttribute("Users", users);

        requestDispatcher = request.getRequestDispatcher("views/UserList.jsp");
        requestDispatcher.forward(request,response);
    }
}
