package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Cart;
import model.Product;

public class ProductDao {
    private Connection conn;
    private String query;
    private PreparedStatement stmt;
    private ResultSet rs;

    // Constructor
    public ProductDao(Connection conn) {
        this.conn = conn;
    }

    // Method to get all products
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<Product>();
        try {
            query = "SELECT * FROM products";
            stmt = this.conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Product row = new Product();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setCategory(rs.getString("category"));
                row.setPrice(rs.getDouble("price"));
                row.setImage(rs.getString("image"));
                
                products.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Better for debugging
        }
        return products;
    }
    
    public Product getSingleProduct(int id) {
    	Product row=null;
    	try {
    		query="select * from products where id=?";
    		stmt=this.conn.prepareStatement(query);
    		stmt.setInt(1, id);
    		rs=stmt.executeQuery();
    		
    		while(rs.next()) {
    			row=new Product();
    			row.setId(rs.getInt("id"));
    			row.setName(rs.getString("name"));
    			row.setCategory( rs.getString("category"));
    			row.setPrice(rs.getDouble("price"));
    			row.setImage(rs.getString("image"));
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return row;
    }
    
    
    public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
        List<Cart> products = new ArrayList<Cart>();

        try {
            if (cartList != null && cartList.size() > 0) {
                for (Cart item : cartList) {
                    query = "SELECT * FROM products WHERE id = ?";
                    stmt = this.conn.prepareStatement(query);
                    stmt.setInt(1, item.getId()); // ✅ Set the value for the placeholder
                    rs = stmt.executeQuery();

                    while (rs.next()) {
                        Cart row = new Cart();
                        row.setId(rs.getInt("id"));
                        row.setName(rs.getString("name"));
                        row.setCategory(rs.getString("category"));
                        row.setPrice(rs.getDouble("price") * item.getQuantity());
                        row.setQuantity(item.getQuantity());
                        products.add(row);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }
    
    
    
    
    
    
    
    
}
