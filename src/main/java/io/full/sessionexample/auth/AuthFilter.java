package io.full.sessionexample.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "AuthFilter", value = "/*")
public class AuthFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletReq, ServletResponse servletResp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) servletReq;
		HttpServletResponse resp = (HttpServletResponse) servletResp;

		String path = req.getRequestURI();

		if (req.getSession().getAttribute("user") == null && !path.startsWith("/login") && !path.equalsIgnoreCase("/")) {
			resp.sendRedirect("/login");
		} else if (req.getSession().getAttribute("user") != null && path.startsWith("/login") || path.equalsIgnoreCase("/")) {
			resp.sendRedirect("/dashboard");
		} else {
			chain.doFilter(servletReq, servletResp);
		}

	}

	@Override
	public void destroy() {
	}
}
