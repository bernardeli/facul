package mack.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mack.db.ConnectionFactory;

/**
 *
 * @author Ricardo Bernardelli 
 */
public class RegisterUserServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterUserServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterUserServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        } finally { 
            out.close();
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if(ConnectionFactory.userExists(username)){
                request.setAttribute("message", "username already exists");
                RequestDispatcher dispatcher = request.getRequestDispatcher("restrito.jsp");
                dispatcher.forward(request, response);
            }else{

                ConnectionFactory.registerUser(username, password);
                RequestDispatcher dispatcher = request.getRequestDispatcher("restrito.jsp");
                dispatcher.forward(request, response);
            }
    }

    @Override
    public String getServletInfo() {
        return "description";
    }

}
