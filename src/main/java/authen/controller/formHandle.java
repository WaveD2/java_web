package authen.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/formUser")
public class formHandle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");

		String url = "jdbc:mysql://localhost:3306/javaweb";
		String dbUsername = "root";
		String dbPassword = "your_password";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, dbUsername, dbPassword);

			String query = "SELECT * FROM users WHERE email=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				req.setAttribute("username", resultSet.getString("username"));
				req.setAttribute("upassword", resultSet.getString("upassword"));
				req.setAttribute("email", resultSet.getString("email"));
				req.setAttribute("phone", resultSet.getString("phone"));

				req.getRequestDispatcher("form.jsp").forward(req, resp);
			} else {

				resp.getWriter().println("User not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().println("Error: " + e.getMessage());
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String upassword = request.getParameter("upassword");
		String phone = request.getParameter("phone");

		String url = "jdbc:mysql://localhost:3306/javaweb";
		String dbUsername = "root";
		String dbPassword = "đ̉đôâđđâ";

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, dbUsername, dbPassword);

			String query = "UPDATE users SET username=?, upassword=?, phone=? WHERE email=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, upassword);
			preparedStatement.setString(3, phone);
			preparedStatement.setString(4, email);

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				response.sendRedirect("form.jsp");
			} else {

				response.getWriter().println("Failed to update user profile");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Error: " + e.getMessage());
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

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String email = request.getParameter("email");

		String url = "jdbc:mysql://localhost:3306/javaweb";
		String dbUsername = "root";
		String dbPassword = "đ̉đôâđđâ";

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, dbUsername, dbPassword);

			String query = "DELETE FROM users WHERE email=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				session.invalidate();

				response.sendRedirect("login.jsp");

			} else {

				response.getWriter().println("Failed to delete user");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Error: " + e.getMessage());
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
