package Interface.WebInterface.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet per l'hard refresh di una pagina
 * @author Francesco Rocchetti
 */
public class ReloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURL().substring(req.getRequestURL().lastIndexOf("/"));
        System.err.println(path);
        resp.sendRedirect("/Pagine"+path);
    }
}
