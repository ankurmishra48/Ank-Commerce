<%@ page import="connection.DbCon" %>
 <%@ page import="model.Product" %>
 <%@ page import="Dao.ProductDao" %>
 
 <%@ page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="model.User" %>
    
 <%
 User auth=(User) request.getSession().getAttribute("auth");
 if(auth!=null){
	 request.setAttribute("auth",auth);
 }
 %>   
 <%  
    ProductDao pd=new ProductDao(DbCon.getConnection());
    List<Product> products=pd.getAllProducts();
    %> 
       <%
    // Fix EL access: set 'cartlist' (no hyphen) for navbar
    java.util.ArrayList<model.Cart> cartlist = (java.util.ArrayList<model.Cart>) session.getAttribute("cart-list");
    if (cartlist == null) {
        cartlist = new java.util.ArrayList<>();
    }
    session.setAttribute("cartlist", cartlist); // EL-friendly name
%>
       
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome To Shopping Cart</title>
<%@include file="includes/header.jsp" %>
 </head>
<body>
<%@include file="includes/navbar.jsp" %>
 
 <div class="container">
 <div class="card-header my-3">All Products</div>
 <div class="row">
 <%
  if (!products.isEmpty()) {
     for (Product p : products) { %>
     <div class="col-md-3"> 
         <div class="card w-100" style="width: 18rem;">
<img src="product-image/<%=p.getImage()%>" class="card-img-top" alt="..." style="width:100%; height:200px; object-fit:cover;">
             <div class="card-body">
                 <h5 class="card-title"><%= p.getName() %></h5>
                 <h6 class="price">Price: <%= p.getPrice() %></h6>
                 <h6 class="category">Category: <%= p.getCategory() %></h6>
                 <div class="mt-3 d-flex justify-content-between">
                     <a href="AddToCartServlet?id=<%=p.getId() %>" class="btn btn-dark">Add to Cart</a>
                     <a href="BuyNowServlet?quantity=1&id=<%= p.getId() %>" class="btn btn-primary">Buy Now</a>
                 </div>
             </div>
         </div>
     </div>
<%   }
   }
%>

 
 
 
 
 
 </div>
 </div>

<%@include file="includes/footer.jsp" %>
 </body>
</html>