package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Payment;

public class PaymentDao {
    private Connection conn;

    public PaymentDao(Connection conn) {
        this.conn = conn;
    }

    public boolean savePayment(Payment payment) {
        try {
            String sql = "INSERT INTO payments(payment_id, order_id, signature, amount, user_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, payment.getPaymentId());
            stmt.setString(2, payment.getOrderId());
            stmt.setString(3, payment.getSignature());
            stmt.setDouble(4, payment.getAmount());
            stmt.setInt(5, payment.getUserId());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}