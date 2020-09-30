import javax.servlet.http.*;
import java.io.IOException;

public class HelloServlet extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.getWriter().print("Hello from servlet");
    }
}
