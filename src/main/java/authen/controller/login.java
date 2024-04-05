package authen.controller;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String upassword = request.getParameter("password");

		String url = "jdbc:mysql://localhost:3306/javaweb";
		String dbUsername = "root";
		String dbPassword = "đ̉đôâđđâ";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		HttpSession session = request.getSession();

		RequestDispatcher dispatch = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(url, dbUsername, dbPassword);

			String query = ""
					+ "SELECT * FROM javaweb.users where email = ?  and upassword = ? ";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, upassword);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				session.setAttribute("username", resultSet.getString("username"));
				session.setAttribute("upassword", resultSet.getString("upassword"));
				session.setAttribute("phone", resultSet.getString("phone"));
				session.setAttribute("email", resultSet.getString("email"));

				dispatch = request.getRequestDispatcher("form.jsp");
			} else {
				request.setAttribute("status", "failed");
				dispatch = request.getRequestDispatcher("login.jsp");
			}

			dispatch.forward(request, response);

		} catch (Exception e) {
			System.out.println("LỖI không kết nối được database login" + e);
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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.sendRedirect("login.jsp");
	}
}
