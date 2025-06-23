package model;

public class Payment {
    private String paymentId;
    private String orderId;
    private String signature;
    private double amount;
    private int userId;

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getSignature() { return signature; }
    public void setSignature(String signature) { this.signature = signature; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}