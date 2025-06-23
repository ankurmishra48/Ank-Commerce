package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.Product;

public class OrderDao {
	private Connection conn;
	private String query;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public OrderDao(Connection conn) {
		this.conn=conn;
	}
	
	public boolean insertOrder(Order model) {
		boolean result=false;
		try {
		query="insert into orders(p_id,u_id,o_quantity,o_date) values(?,?,?,?)";
		stmt=this.conn.prepareStatement(query);
		
		stmt.setInt(1, model.getId());
		stmt.setInt(2, model.getUid());
		stmt.setInt(3, model.getQuantity());
		stmt.setString(4, model.getDate());
		stmt.executeUpdate();
		result=true;	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	public List<Order> userorder(int id) {
	    List<Order> list = new ArrayList<>();
	    try {
	        query = "SELECT * FROM orders WHERE u_id = ? ORDER BY o_id DESC";
	        stmt = this.conn.prepareStatement(query);
	        stmt.setInt(1, id);  // ✅ Set the parameter!
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            Order order = new Order();
	            ProductDao productdao = new ProductDao(this.conn);
	            int pid = rs.getInt("p_id");

	            Product product = productdao.getSingleProduct(pid);

	            order.setOrederId(rs.getInt("o_id"));
	            order.setId(pid);
	            order.setName(product.getName());
	            order.setCategory(product.getCategory());
	            order.setPrice(product.getPrice() * rs.getInt("o_quantity"));
	            order.setQuantity(rs.getInt("o_quantity"));
	            order.setDate(rs.getString("o_date"));

	            list.add(order);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	public void cancelOrder(int id) {
		try {
			query="delete from orders where o_id=?";
			stmt=this.conn.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
}