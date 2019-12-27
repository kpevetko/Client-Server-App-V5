package servlets;

import main.java.DataBase;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@WebServlet("/UserList")
public class UserList extends HttpServlet {
    DataBase DB = null;

    RequestDispatcher requestDispatcher = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //возвращает список юзеров в онлайне
        Sessions ses = Sessions.getInstance();
        System.out.println(ses.getSessions().size());
        List ll = new ArrayList<>(ses.getSessions().keySet());
        for (int i = 0; i<ll.size(); i++ ) {
            response.getWriter().println(ll.get(i));
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            DB = DataBase.getMyDBObject();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<String> users = null;
        try {
            users = DB.getUserListDBList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       request.setAttribute("Users",users);

        requestDispatcher = request.getRequestDispatcher("views/UserList.jsp");
        requestDispatcher.forward(request,response);
    }
}
