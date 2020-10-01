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
        //resp.getWriter().print("Hello from servlet");
        String sPath = req.getParameter("path");
        if(sPath == null || sPath.length() == 0)
            sPath = "C:/";
        Path path = Paths.get(sPath);
        File file = path.toFile();

        if(file.isDirectory()){
            req.setAttribute("now", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
            req.setAttribute("path", sPath);
            req.setAttribute("files", file.listFiles());
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }
        //Path workingDirectory = Paths.get(".").toAbsolutePath();
        //String sPath = workingDirectory.toString();

    }
/*
    public List<File> printFiles(File file){
        List<File> listFiles = new ArrayList<>();
        File[] files = file.listFiles();
        for(File f: files){
            file.lastModified()
        }
    }*/


}
