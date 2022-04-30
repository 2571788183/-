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
import org.springframework.beans.factory.annotation.Value;

public class IndexFilter implements Filter {
	ServletContext context;
@Value("${service.iscc}")
	boolean isSupportCheckCode;

	// private Logger logger = Logger.getLogger(AttachmentFilter.class);

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String requestURI = request.getRequestURI();
		if(requestURI.contains("login.jsp")) {
			request.setAttribute("isSupportCheckCode",isSupportCheckCode);
		}
		if (requestURI.equals(request.getContextPath()+"/")) {
			response.sendRedirect(request.getContextPath()+"/backend/login.jsp");
//			request.getRequestDispatcher("/site/list").forward(request, response);
		} else {
			if (requestURI.equals("/houtai")) {
				response.sendRedirect(request.getContextPath()+"/backend/login.jsp");
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		context = filterConfig.getServletContext();
	}

}
