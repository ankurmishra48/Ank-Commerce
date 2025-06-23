package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;

 
@WebServlet("/Removecart")
public class Removecart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  response.setContentType("text/html;charset=UTF-8");
	  try(PrintWriter out=response.getWriter()){
		  
  int id=Integer.parseInt(request.getParameter("id"));
  if(id>=1) {
	  ArrayList<Cart> cartlist=(ArrayList<Cart>) request.getSession().getAttribute("cartlist");
  if(cartlist!=null) {
	  for(Cart c:cartlist) {
		  if(c.getId()==id) {
			  cartlist.remove(cartlist.indexOf(c));
			  break;
		  }
	  }
	  response.sendRedirect("cart.jsp");

  }
  else {
	  response.sendRedirect("cart.jsp");
  }
  
  
  }
  
  
  
  }
  
  }

}
