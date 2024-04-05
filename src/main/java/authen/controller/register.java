package authen.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public register() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username").trim().toUpperCase();
		String email = request.getParameter("email").trim();
		String upassword = request.getParameter("upassword").trim();
		String phone = request.getParameter("phone").trim();

		try {
			if ((username != null && username.length() > 0) || (email != null && email.length() > 0)
					|| (upassword != null && upassword.length() > 0)) {
				USER svobj = new USER();
				if (username != null && username.length() > 0)
					svobj.setUsername(username);
				if (email != null && email.length() > 0)
					svobj.setEmail(email);
				if (upassword != null && upassword.length() > 0)
					svobj.setUpassword(upassword);
				if (phone != null && phone.length() > 0)
					svobj.setPhone(phone);

				svobj.themDuLieu();
				

			}
			response.sendRedirect("login.jsp");
		} catch (Exception e) {
			System.out.println("ERR" + e.toString());
			response.sendRedirect("register.jsp");
		}
	}

}
