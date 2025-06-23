package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.OrderDao;
import connection.DbCon;
import model.Cart;
import model.Order;
import model.User;

 
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try(PrintWriter out=response.getWriter()){
    	  SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			 User auth=(User) request.getSession().getAttribute("auth");

       	 ArrayList<Cart> cartlist=(ArrayList<Cart>) request.getSession().getAttribute("cartlist");
         
       	 if(cartlist!=null && auth!=null) {
       		 for(Cart c:cartlist) {
       			 Order order=new Order();
       			 order.setId(c.getId());
       			 order.setUid(auth.getId());
       			 order.setQuantity(c.getQuantity());
       			 order.setDate(formatter.format(date));
       			 
       			 OrderDao odao=new OrderDao(DbCon.getConnection());
       			boolean result= odao.insertOrder(order);
       			if(!result)break;
       			
       		 }
       		 cartlist.clear();
       		 response.sendRedirect("orders.jsp");
       	 }else {
       		 if(auth==null) {
       			 response.sendRedirect("login.jsp");
       		 }else {
       			 response.sendRedirect("cart.jsp");
       		 }
       	 }
			
			
			
      }catch(Exception e) {
    	  e.printStackTrace();
      }
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		doGet(request, response);
	}

}
