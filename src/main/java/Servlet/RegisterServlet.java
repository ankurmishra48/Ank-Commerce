package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.UserDao;
import connection.DbCon;
import model.User;

 
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
 
 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	     try {
	String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        UserDao udao = new UserDao(DbCon.getConnection());
        boolean success = udao.registerUser(user);

        if (success) {
            res.sendRedirect("login.jsp");
        } else {
            res.sendRedirect("register.jsp?error=exists");
        } }catch(Exception e) {
        	e.printStackTrace();
        }
	}
     
}
