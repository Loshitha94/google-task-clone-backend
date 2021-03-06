package lk.ijse.dep8.api;

import lk.ijse.dep8.tasks.util.HttpServlet2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
@MultipartConfig(location = "/tmp",maxFileSize =10*1024*1024)

// use front-controller design pattern
@WebServlet(name = "DispatcherServlet", value = "v1/users/*")
public class DispatcherServlet extends HttpServlet2 {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getPathInfo() == null || req.getPathInfo().equals("/")){
            getServletContext().getNamedDispatcher("UserServlet").forward(req, resp);
        }else{
            if (req.getPathInfo().matches("/[A-Fa-f0-9\\-]{36}/?")){
                getServletContext().getNamedDispatcher("UserServlet").forward(req, resp);
            }else if (req.getPathInfo().matches("/[A-Fa-f0-9\\-]{36}/lists(/\\d+)?/?")){
                getServletContext().getNamedDispatcher("TaskServlet").forward(req, resp);
            }else {
                super.service(req,resp);
            }
        }
    }
}
