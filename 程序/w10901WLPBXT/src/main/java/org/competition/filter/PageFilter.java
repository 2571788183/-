package org.competition.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.competition.utils.PageContext;

public class PageFilter implements Filter {
	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		PageContext.getPage().setPageNo(
				Integer.parseInt(request.getParameter("pageNo") == null
						|| "".equals(request.getParameter("pageNo")) ? "1"
						: request.getParameter("pageNo")));
		PageContext.getPage().setPageSize(
				Integer.parseInt(request.getParameter("pageSize") == null
						|| "".equals(request.getParameter("pageSize")) ? "10"
						: request.getParameter("pageSize")));
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {
	}

}
