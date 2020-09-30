import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


public class HelloServlet extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        //resp.getWriter().print("Hello from servlet");
        req.setAttribute("name", "BITCH");
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
}
