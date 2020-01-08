package Interface.WebInterface.Servlet;

import Logica.Facade;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rId = req.getRequestURL().substring(req.getRequestURL().lastIndexOf("/") +1);

        Facade f = new Facade();
        f.dropComp(Integer.parseInt(rId));
        resp.sendRedirect("/Pagine/page2.jsp");
    }
}
