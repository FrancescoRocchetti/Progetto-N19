package Interface.WebInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String inf = req.getRequestURL().substring(req.getRequestURL().lastIndexOf("/") +1);


        resp.sendRedirect("../Pagine/Res.jsp");
    }
}
