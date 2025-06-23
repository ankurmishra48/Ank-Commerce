<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.User" %>
<%@ page import="model.Order" %>
<%@ page import="java.util.*" %>
<%@ page import="Dao.OrderDao" %>
<%@ page import="connection.DbCon" %>

<%
    User auth = (User) request.getSession().getAttribute("auth");
    List<Order> orders = null;
    if (auth != null) {
        request.setAttribute("auth", auth);
        orders = new OrderDao(DbCon.getConnection()).userorder(auth.getId());
    } else {
        response.sendRedirect("login.jsp");
        return;
    }

    // Set 'cartlist' for navbar badge (EL-friendly)
    java.util.ArrayList<model.Cart> cartlist = (java.util.ArrayList<model.Cart>) session.getAttribute("cart-list");
    if (cartlist == null) {
        cartlist = new java.util.ArrayList<>();
    }
    session.setAttribute("cartlist", cartlist);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Orders</title>
    <%@ include file="includes/header.jsp" %>
</head>
<body>
    <%@ include file="includes/navbar.jsp" %>

    <div class="container">
        <div class="card-header my-3">All Orders</div>

        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th scope="col">Date</th>
                    <th scope="col">Name</th>
                    <th scope="col">Category</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Price</th>
                    <th scope="col">Cancel</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (orders != null) {
                        for (Order o : orders) {
                %>
                <tr>
                    <td><%= o.getDate() %></td>
                    <td><%= o.getName() %></td>
                    <td><%= o.getCategory() %></td>
                    <td><%= o.getQuantity() %></td>
                    <td><%= o.getPrice() %></td>
                    <td>
                        <a class="btn btn-sm btn-danger" href="CanecelOrder?id=<%= o.getOrederId() %>">Cancel</a>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>

    <%@ include file="includes/footer.jsp" %>
</body>
</html>
