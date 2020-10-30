package servlets;

import org.hibernate.HibernateException;
import service.AccountService;
import service.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.sql.SQLException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    AccountService accountService = new AccountService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req,resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if(email == null || login == null || password == null){
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
            return;
        }

        if(accountService.addNewUser(login, password, email)){
            req.getRequestDispatcher("logIn.jsp").forward(req,resp);
        }else {
            resp.getWriter().println("User with such login has already exist");
        }
    }
}
