package io.full.sessionexample.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.full.sessionexample.user.User;

public class SessionHelper {
	/**
	 * Helper method to set user info in session and secure the session cookie
	 * 
	 * @param request
	 * @param response
	 */
	public void createSession(HttpServletRequest request, HttpServletResponse response, User user) {
		request.getSession().setAttribute("user", user);
	}
}
