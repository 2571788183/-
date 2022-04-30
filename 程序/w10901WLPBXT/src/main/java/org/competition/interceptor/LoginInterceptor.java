package org.competition.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{
	@Value("${service.lg.interceptor.check.urlPattern}")
    private String urlPattern;
    @Value("${service.lg.interceptor.check.escape}")
    private String escape;
    private String[] urlPatterns = null;
	private String[] escapeUrls = null;
	@Autowired
	HttpSession session;
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
    	//preHandle在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
//    	HttpSession session = request.getSession();
    	String url = request.getRequestURI();
    	setUrlPattern();
    	setEscape();
		if (isIntercept(url)) {
			if (session.getAttribute("login_user") == null) {
				response.sendRedirect(request.getContextPath()+"/backend/login.jsp");
				return false;
			}
		}
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    	//postHandle方法在业务处理器处理请求执行完成后，生成视图之前执行。属于后处理（调用了Service并返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView
    }
    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    	//afterCompletion方法在控制器的处理请求方法执行完成后执行，即视图渲染结束之后执行
    }
    public boolean isIntercept(String url) {
		boolean urlPatternFlag = false;
		boolean escapeFlag = false;

		for (String s : urlPatterns) {
			if (url.indexOf(s) > -1) {
				urlPatternFlag = true;
				break;
			}
		}
		for (String s : escapeUrls) {
			if (url.indexOf(s) > -1) {
				escapeFlag = false;
				break;
			} else {
				escapeFlag = true;
			}
		}
		return urlPatternFlag && escapeFlag;
	}
    
	public void setUrlPattern() {
		urlPattern.replace('，', ',');
		this.urlPatterns =  urlPattern.split(",");
	}

	public void setEscape() {
		escape.replace('，', ',');
		this.escapeUrls = escape.split(",");
	}
}
