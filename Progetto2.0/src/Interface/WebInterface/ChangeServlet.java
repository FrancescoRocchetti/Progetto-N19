package Interface.WebInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String inf = req.getRequestURL().substring(req.getRequestURL().lastIndexOf("/") +1);

        String[] as = inf.split(",");

        req.getSession().setAttribute("id",as[0]);
        req.getSession().setAttribute("price",as[1]);
        req.getSession().setAttribute("qt",as[2]);
        req.getSession().setAttribute("rt",as[3]);


        resp.sendRedirect("/Pagine/change.jsp?id="+as[0]);

    }
}
