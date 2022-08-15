package lk.ijse.dep8.tasks.api;

import lk.ijse.dep8.tasks.dto.UserDTO;
import lk.ijse.dep8.tasks.service.ServiceFactory;
import lk.ijse.dep8.tasks.service.custom.UserService;
import lk.ijse.dep8.tasks.util.HttpServlet2;
import lk.ijse.dep8.tasks.util.ResponseStatusException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet2 {

    private final Logger logger= Logger.getLogger(UserServlet.class.getName());

    private UserDTO getUser(HttpServletRequest req){
        if (!(req.getPathInfo() != null &&
                (req.getPathInfo().replaceAll("/", "").length() == 36))) {
            throw new ResponseStatusException(404, "Invalid user id");
        }

        String userId = req.getPathInfo().replaceAll("/", "");

        try  {
            UserService userService = ServiceFactory.getInstance().
                    getService(ServiceFactory.ServiceTypes.USER);
            if (!userService.existsUser(userId)) {
                throw new ResponseStatusException(404, "Invalid user id");
            } else {
                return userService.getUser(userId);
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Throwable e) {
            throw new ResponseStatusException(500, "Failed to fetch the user info", e);
        }
    }
    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPatch(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
