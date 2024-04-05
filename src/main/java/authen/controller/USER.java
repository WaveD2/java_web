package authen.controller;

import java.sql.*;

public class USER {

	private String username;
	private String upassword;
	private String email;
	private String phone;



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void themDuLieu(){
		String url = "jdbc:mysql://localhost:3306/javaweb";
		String dbUsername = "root";
		String dbPassword = "đ̉đôâđđâ";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		System.out.println(username + "---" + upassword);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 
			connection = DriverManager.getConnection(url, dbUsername, dbPassword);
			 
			String query = 
					"INSERT INTO users (username, upassword, email, phone)"
					+ " VALUES (?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, this.username);
			preparedStatement.setString(2, this.upassword);
			preparedStatement.setString(3, this.email);
			preparedStatement.setString(4, this.phone);

			int rowsAffected = preparedStatement.executeUpdate();
			
			if (rowsAffected > 0) {
				  
			} else {
				System.out.println("LỖI KHÔNG GHI ĐƯỢC");
			}

		} catch (Exception e) {
			System.out.println("LỖI không kết nối được database " + e);
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
