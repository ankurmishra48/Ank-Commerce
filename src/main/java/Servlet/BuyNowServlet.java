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

/**
 * Servlet implementation class BuyNowServlet
 */
@WebServlet("/BuyNowServlet")
public class BuyNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try(PrintWriter out=response.getWriter()){
			 
				SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
				Date date=new Date();
		 User auth=(User) request.getSession().getAttribute("auth");
		 
		 if(auth!=null) {
			 String productid=request.getParameter("id");
	  int productqunatity=Integer.parseInt(request.getParameter("quantity"));	  
			 if(productqunatity<=0) {
				 productqunatity=1;
			 }
		 
			 Order orderModel=new Order();
			 orderModel.setId(Integer.parseInt(productid));
			 orderModel.setUid(auth.getId());
			 orderModel.setQuantity(productqunatity);
             orderModel.setDate(formatter.format(date));
		 
             
             OrderDao orderdao=new OrderDao(DbCon.getConnection());
            boolean result= orderdao.insertOrder(orderModel);
             if(result) {
            	 ArrayList<Cart> cartlist=(ArrayList<Cart>) request.getSession().getAttribute("cartlist");
            	  if(cartlist!=null) {
            		  for(Cart c:cartlist) {
            			  if(c.getId()==Integer.parseInt(productid)) {
            				  cartlist.remove(cartlist.indexOf(c));
            				  break;
            			  }
            		  }
 
            	  }
            	 response.sendRedirect("orders.jsp");
             
                   }else {
                	   out.print("order failed");
                   }
		 
		 }else {
			 response.sendRedirect("login.jsp");
		 }
		 
		  
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }	 
		
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		doGet(request, response);
	}

}
