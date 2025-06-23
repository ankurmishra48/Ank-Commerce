package model;

public class Order extends Product {
private int orederId;
private int uid;
private int quantity;
private String date;

 
public Order(int id, String name, String category, double price, String image) {
 }
public Order(int id, String name, String category, double price, String image, int orederId, int uid, int quantity,
		String date) {
 	this.orederId = orederId;
	this.uid = uid;
	this.quantity = quantity;
	this.date = date;
}
public Order() {
	// TODO Auto-generated constructor stub
}
public int getOrederId() {
	return orederId;
}
public void setOrederId(int orederId) {
	this.orederId = orederId;
}
public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
@Override
public String toString() {
	return "Order [orederId=" + orederId + ", uid=" + uid + ", quantity=" + quantity + ", date=" + date + "]";
}

}
