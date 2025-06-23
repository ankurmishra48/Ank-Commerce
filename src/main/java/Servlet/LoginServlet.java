package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.UserDao;
import connection.DbCon;
import model.User;

 
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.sendRedirect("login.jsp");
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 response.setContentType("text/html;charset=UTF-8");
	 try(PrintWriter out=response.getWriter()) {
       		 String email=request.getParameter("login-email");
       		 String password=request.getParameter("login-password");
       		 
       		  UserDao udao =new UserDao(DbCon.getConnection());
       		  User user =udao.userlogin(email, password);
       		  if(user!=null) {
       			  out.print("login");
       			  request.getSession().setAttribute("auth", user);
       			  response.sendRedirect("index.jsp");
       		  }
       		  else {
       			  out.print("user login failed");
       		  }
	 } catch (ClassNotFoundException | SQLException e) {
 		e.printStackTrace();
	}
	}

}
