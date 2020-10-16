package servlets;

import service.AccountService;
import service.SomeFile;
import service.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileInputStream;
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
        String sessionId = req.getSession().getId();
        UserProfile user = AccountService.getUserBySessionId(sessionId);
        if(user == null){
            req.getRequestDispatcher("/logIn.jsp");
            return;
        }

        String sPath = req.getParameter("path");
        if(sPath.contains("C:\\Users\\" + user.getLogin())){
            Path path = Paths.get(sPath);
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
                FileInputStream fileIn = new FileInputStream(someFile);
                ServletOutputStream out = resp.getOutputStream();
                byte[] outputByte = new byte[4096];
                while(fileIn.read(outputByte, 0, 4096) != -1){
                    out.write(outputByte, 0, 4096);
                }
                fileIn.close();
                out.flush();
                out.close();
            }
        }else {
            req.setAttribute("path", "C:\\Users\\" + user.getLogin());
            req.getRequestDispatcher("error.jsp").forward(req,resp);
        }
    }
}
