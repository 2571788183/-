package org.competition.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import org.competition.dao.MemberDao;import org.competition.dao.JudgeDao;
import org.competition.dao.UserDao;
import org.competition.dao.impl.MemberDaoImpl;import org.competition.dao.impl.JudgeDaoImpl;
import org.competition.model.User;

@Transactional
public class HttpSessionFilter implements Filter {
	@Resource
	private UserDao userDao;
	@Resource
	private MemberDao memberDao = new MemberDaoImpl();@Resource
	private JudgeDao judgeDao = new JudgeDaoImpl();
	@Value("${service.http.session.filter}")
	private boolean filterSession;
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session;
		ServletContext application;
		String url = request.getRequestURI();
		Cookie[] cookies =request.getCookies();
		String login_user = "";
		if(cookies!=null) {
			for(Cookie c:cookies) {
				if(c.getName().equals("login_user")) {
					login_user=c.getValue();
				}
			}
		}
		application=request.getServletContext();
		if (filterSession&&request.getSession().getAttribute("login_user") == null&&login_user!=null&&!login_user.equals("")) {
			session = request.getSession(true);
			response.setContentType("text/plain");
			response.setHeader("Cache-Control", "no-cache");
			String id = login_user;
			User user = null;
			if(id!=null&&!id.equals("")){
				List<User> userList = userDao.query("");
				int flag=0;
				for(User u:userList){
					if(id.equals(""+u.getId()))flag=1;
				}
				if(flag==1){
					user=userDao.getById(Integer.parseInt(id));
				}
			}
				if (user != null&&user.getId()!=0) {
				session.setAttribute("login_user", user);
				Cookie login_user_cookie = new Cookie("login_user", user.getId()+"");
				login_user_cookie.setMaxAge(60*60*12);
				login_user_cookie.setPath("/");
				response.addCookie(login_user_cookie);
				if(user.getRole().getId()==2)session.setAttribute("member", memberDao.getByUserId(user.getId()));
				if(user.getRole().getId()==3)session.setAttribute("judge", judgeDao.getByUserId(user.getId()));
				if(user.getRole().getId()==1){
					Cookie cookie = new Cookie("login_backend", user.getId()+"");
					cookie.setMaxAge(60*60*12);cookie.setPath("/");
					response.addCookie(cookie);
				}
				if(user.getRole().getId()==2){
					Cookie cookie = new Cookie("login_backend", user.getId()+"");
					cookie.setMaxAge(60*60*12);cookie.setPath("/");
					response.addCookie(cookie);
				}
				if(user.getRole().getId()==3){
					Cookie cookie = new Cookie("login_backend", user.getId()+"");
					cookie.setMaxAge(60*60*12);cookie.setPath("/");
					response.addCookie(cookie);
				}
				List<User> loginUsers = (List<User>) application.getAttribute("loginUsers");
				if (loginUsers == null || loginUsers.size() == 0) {
					loginUsers = new ArrayList<User>();
				}
				User removeUser = null;
				for(User u:loginUsers){
					if(u.getUsername().equals(user.getUsername())){
						removeUser = u;
					}
				}
				if(removeUser!=null)loginUsers.remove(removeUser);
				loginUsers.add(user);
				application.setAttribute("loginUsers", loginUsers);
				
				
				} else {
				}
			
		}
		chain.doFilter(request, response);
	}
}
