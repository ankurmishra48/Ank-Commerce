package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.PaymentDao;
import connection.DbCon;
import model.Payment;
import model.User;

public class PaymentSuccessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      try {  User auth = (User) req.getSession().getAttribute("auth");
        if (auth == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        String paymentId = req.getParameter("razorpay_payment_id");
        String orderId = req.getParameter("razorpay_order_id");
        String signature = req.getParameter("razorpay_signature");
        double amount = Double.parseDouble(req.getParameter("amount"));

        Payment payment = new Payment();
        payment.setPaymentId(paymentId);
        payment.setOrderId(orderId);
        payment.setSignature(signature);
        payment.setAmount(amount);
        payment.setUserId(auth.getId());

        PaymentDao dao = new PaymentDao(DbCon.getConnection());
        if (dao.savePayment(payment)) {
            res.sendRedirect("payment-success.jsp");
        } else {
            res.sendRedirect("error.jsp");
        }}
    catch(Exception e) {
    	e.printStackTrace();
    }
}}