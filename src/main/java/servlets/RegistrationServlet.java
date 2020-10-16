package servlets;

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

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if(email == null || login == null || password == null){
            req.getRequestDispatcher("/registration").forward(req, resp);
            return;
        }
        AccountService.addNewUser(new UserProfile(email, login, password));
        req.getRequestDispatcher("logIn.jsp").forward(req,resp);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req,resp);
    }
}
