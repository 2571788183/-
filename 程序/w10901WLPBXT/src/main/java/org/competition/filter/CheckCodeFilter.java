package org.competition.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckCodeFilter implements Filter {
	ServletContext context;

	// private Logger logger = Logger.getLogger(AttachmentFilter.class);

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		request.getRequestDispatcher("/backend/checkCode.do").forward(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		context = filterConfig.getServletContext();
	}

}
