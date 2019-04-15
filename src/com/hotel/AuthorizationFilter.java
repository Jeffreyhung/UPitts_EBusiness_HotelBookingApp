package com.hotel;

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
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {}

	public void init(FilterConfig filterConfig) throws ServletException {}

	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			String reqURI = reqt.getRequestURI();
			if ((reqURI.indexOf("/signIn.xhtml") >= 0 ||
					reqURI.indexOf("/signUp.xhtml") >= 0) &&
					(ses != null && ses.getAttribute("username") != null)) {
				resp.sendRedirect(reqt.getContextPath() + "/faces/index.xhtml");
			} else if (	(ses != null && ses.getAttribute("username") != null) ||
					reqURI.contains("javax.faces.resource")) {
				chain.doFilter(request, response);
			}else if((reqURI.indexOf("/member.xhtml") >= 0 ||
					reqURI.indexOf("/logout.xhtml") >= 0) &&
					(ses == null || 
					ses.getAttribute("username") == null)) {
				resp.sendRedirect(reqt.getContextPath() + "/faces/signIn.xhtml");
			}else {
				chain.doFilter(request, response);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void destroy() {

	}
}