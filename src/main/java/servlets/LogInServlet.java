package servlets;

import service.AccountService;
import service.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logIn")
public class LogInServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UserProfile user = AccountService.getUserByLogin(login);
        if(user == null){
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
            return;
        }

        if(!password.equals(user.getPassword())){
            req.getRequestDispatcher("logIn.jsp").forward(req,resp);
            return;
        }

        AccountService.addNewSession(req.getSession().getId(), user);
        String path = "http://localhost:8000/files?path=C:\\Users\\" + login;
        resp.sendRedirect(path);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("logIn.jsp").forward(req,resp);
    }

}
