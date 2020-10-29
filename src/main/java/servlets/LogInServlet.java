package servlets;

import dbService.dataSets.UsersDataSet;
import service.AccountService;
import service.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/logIn")
public class LogInServlet extends HttpServlet {

    AccountService accountService = new AccountService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("logIn.jsp").forward(req,resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        UsersDataSet authUser = new UsersDataSet(login, password, email);

        try {
            if(accountService.authorizateUser(authUser, req.getSession().getId())){
                String path = "http://localhost:8000/files?path=C:\\Users\\" + login;
                resp.sendRedirect(path);
            }else {
                req.getRequestDispatcher("/registration.jsp").forward(req, resp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
