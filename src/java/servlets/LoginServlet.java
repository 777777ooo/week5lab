
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.AccountService;
import models.User;

/**
 *
 * @author Dont1
 */

public class LoginServlet extends HttpServlet {

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String logout = request.getParameter("logout");
        String username = (String) session.getAttribute("username");
        
        if (logout != null) {
            session.invalidate();
            request.setAttribute("result", "You have successfully logged out");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
        }
        
        if (username != null) {
            
            response.sendRedirect("home");
            
        } else {
            
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
        
        }
        

        
    }
    
    
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        
        AccountService ac = new AccountService();
        User user = ac.login(username, password);
        
        if (username.equals("") || password.equals("")) {
     
        request.setAttribute("result", "Please enter credentials");
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
       
        }
     
        if (user == null) {
         request.setAttribute("result", "Incorrect credentials");
         getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                 .forward(request, response);
        
        } else {
            session.setAttribute("username", username);
            response.sendRedirect("home");
        
        }
        
        
    } 
    
    
    
 
}
