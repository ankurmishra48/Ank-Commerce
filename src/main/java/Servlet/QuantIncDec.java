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

 
@WebServlet("/QuantIncDec")
public class QuantIncDec extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out=response.getWriter();
	response.setContentType("text/html;charset=UTF-8");
	 String action=request.getParameter("action");
	 int id=Integer.parseInt(request.getParameter("id"));
	 
	 ArrayList<Cart> cartlist=(ArrayList<Cart>)request.getSession().getAttribute("cartlist");
	 if(action!=null && id>=1) {
		 if(action.equals("inc")) {
			 for(Cart c:cartlist) {
				 if(c.getId()== id) {
					 int quantity=c.getQuantity();
					 quantity++;
					 c.setQuantity(quantity);
					 response.sendRedirect("cart.jsp");
				 }
			 }
		 }
		 if(action.equals("dec")) {
			 for(Cart c:cartlist) {
				 if(c.getId()== id && c.getQuantity()>1) {
					 int quantity=c.getQuantity();
					 quantity--;
					 c.setQuantity(quantity);
					 response.sendRedirect("cart.jsp");
				 }
			 }
		 }
	 }
	
	
	
	}

}
