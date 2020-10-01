import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/files")
public class FilesServlet extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String sPath = req.getParameter("path");
        if(sPath == null || sPath.length() == 0)
            sPath = "C:/";
        Path path = Paths.get(sPath);
        File file = path.toFile();
        SomeFile someFile = new SomeFile(path);

        if(someFile.isDirectory()){
            req.setAttribute("now", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
            req.setAttribute("path", path);
            req.setAttribute("files", someFile.getFiles(path));
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }

        if(someFile.isFile()){
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition","attachment;fileName=\""+someFile.getName()+"\"");
        }
    }
}
