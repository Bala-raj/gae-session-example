package io.full.sessionexample.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.full.sessionexample.user.UserService;
import io.full.sessionexample.utils.SessionHelper;

@WebServlet(name = "LoginController", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserService();

	private void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("invalid", true);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String login = request.getParameter("login");
		String pwd = request.getParameter("password");

		if (login != null && pwd != null) {

			// Check the credential and create session 
			userService.checkCredential(login, pwd).ifPresent(user -> new SessionHelper().createSession(request, response, user));
			
			// In case of invalid credential just redirect to login page
			if(request.getSession().getAttribute("user") == null) {
				redirectToLogin(request,response);
			}
			response.sendRedirect("/dashboard");
		} else {

			// In case of invalid data just redirect to login page
			redirectToLogin(request, response);
		}
	}
}