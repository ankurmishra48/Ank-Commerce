package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;

public class UserDao {
private Connection conn;
private String query;
private PreparedStatement stmt;
private ResultSet rs;

public UserDao(Connection conn) {
	this.conn=conn;
}
public User userlogin(String email,String password) {
	User user=null;
	
	try {
		query="select * from users where email=? and password=?";
		stmt=this.conn.prepareStatement(query);
		stmt.setString(1, email);
		stmt.setString(2, password);
		rs=stmt.executeQuery();
		if(rs.next()) {
			user=new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			
		}
	}catch(Exception e) {
		System.out.println(e);
	}
	return user;
}

public boolean registerUser(User user) {
    boolean result = false;
    try {
        String query = "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPassword());
        int rows = ps.executeUpdate();
        result = rows > 0;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}



}
