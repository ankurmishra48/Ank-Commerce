<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="model.User" %>
<%@ page import="Dao.ProductDao" %>
<%@ page import="java.util.*" %>
<%@ page import="model.Cart" %>
<%@ page import="connection.DbCon" %>

<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("auth", auth);
    }

    ArrayList<Cart> cartlist = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartproduct = null;
    int total = 0;

    if (cartlist != null && !cartlist.isEmpty()) {
        ProductDao pdao = new ProductDao(DbCon.getConnection());
        cartproduct = pdao.getCartProducts(cartlist);
        for (Cart item : cartproduct) {
            total += item.getPrice() * item.getQuantity();
        }
        
        request.setAttribute("cartlist", cartlist);
        request.setAttribute("total",total);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Cart</title>
    <%@include file="includes/header.jsp" %>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <%@include file="includes/navbar.jsp" %>

    <div class="container">
        <div class="d-flex py-3 align-items-center justify-content-between">
            <h3>Total Price: $<%= total %></h3>
            <a class="btn btn-primary" href="CheckoutServlet">CheckOut</a>
        </div>

        <table class="table table-light">
            <thead>
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Category</th>
                    <th scope="col">Price</th>
                    <th scope="col">Buy Now</th>
                    <th scope="col">Cancel</th>
                </tr>
            </thead>
            <tbody>
            <%
                if (cartproduct != null && !cartproduct.isEmpty()) {
                    for (Cart c : cartproduct) {
            %>
                <tr>
                    <td><%= c.getName() %></td>
                    <td><%= c.getCategory() %></td>
                    <td>$<%= c.getPrice() %></td>
                    <td>
    <form action="BuyNowServlet" method="post" class="form-inline d-flex align-items-center">
        <input type="hidden" name="id" value="<%= c.getId() %>" />

        <!-- Quantity Controls -->
        <div class="form-group d-flex align-items-center me-2">
            <a class="btn btn-sm btn-decre" href="QuantIncDec?action=dec&id=<%=c.getId()%>">
                <i class="fas fa-minus-square"></i>
            </a>
            <input type="text" name="quantity" class="form-control text-center mx-2" 
                   style="width: 50px;" value="<%= c.getQuantity() %>" readonly>
            <a class="btn btn-sm btn-incre" href="QuantIncDec?action=inc&id=<%=c.getId()%>">
                <i class="fas fa-plus-square"></i>
            </a>
        </div>

        <!-- Buy Now Button -->
        <button type="submit" class="btn btn-primary btn-sm">Buy Now</button>
    </form>
</td>

                    <td>
                        <a class="btn btn-sm btn-danger" href="Removecart?id=<%=c.getId() %>">Remove</a>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="5" class="text-center">Your cart is empty</td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>

    <%@include file="includes/footer.jsp" %>
</body>
</html>
