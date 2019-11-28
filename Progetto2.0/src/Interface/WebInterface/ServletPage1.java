package Interface.WebInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletPage1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.getRequestDispatcher("/Pagine/page1.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        int ck=0;   //  0==page1, 1==success

        Page1Bean bean = (Page1Bean) req.getSession().getAttribute("test");

        if(req.getParameter("buttonC") != null){
            String temp = req.getParameter("buttonC");
             bean.remove(temp);
        }

        if(req.getParameter("buttonD") != null){
            String temp = req.getParameter("buttonD");
            if(temp.equalsIgnoreCase("r")){
                bean.reset();
                bean.setcAttivo("");
            }
            if(temp.equalsIgnoreCase("b")){
                bean.confirm();
                ck=1;
            }
        }

        if(ck==0) {
            req.getRequestDispatcher("/Pagine/page1.jsp").forward(req, resp);
        }
        else {
            req.getRequestDispatcher("/Pagine/Success.jsp").forward(req, resp);
        }
    }
}
