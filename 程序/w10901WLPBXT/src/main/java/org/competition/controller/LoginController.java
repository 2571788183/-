package org.competition.controller;

import org.springframework.beans.factory.annotation.Value;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Value;
import org.competition.dao.UserDao;
import org.competition.model.User;
import org.competition.dao.MemberDao;
import org.competition.dao.impl.MemberDaoImpl;
import org.competition.dao.JudgeDao;
import org.competition.dao.impl.JudgeDaoImpl;
import org.springframework.context.annotation.Scope;
import org.competition.model.Search;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@Scope("prototype")
@RequestMapping(value={"/backend/login","/login"})
public class LoginController extends BaseController{
	
	@Resource
	private UserDao userDao;
	@Resource
	private MemberDao memberDao = new MemberDaoImpl();
	@Resource
	private JudgeDao judgeDao = new JudgeDaoImpl();
	@Value("${service.iscc}")
	private boolean isSupportCheckCode;
//	private static Logger logger = Logger.getLogger(LoginController.class);
	private static Log logger = LogFactory.getLog("login");

	
	
	@RequestMapping("/login")
	public String login() {
		session = request.getSession(true);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String key = request.getParameter("key");
		String code = request.getParameter("code");
		String check_code = (String) session.getAttribute("check_code");
		User user = null;
		if (isSupportCheckCode) {
			if (!code.equalsIgnoreCase(check_code)) {
				session.setAttribute("login_error", "验证码输入有误");
				return "redirect:/backend/login.jsp";
			}
		}
		try {
			user = userDao.login(username, password);
		} catch (RuntimeException e) {
			session.setAttribute("login_error", e.getMessage());
		}
		if (user != null) {
			logger.info(user.getRole().getName() + user.getUsername() + "成功登录.");
			session.removeAttribute("login_error");
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

			return "redirect:/backend/main.jsp";

		} else {
			return "redirect:/backend/login.jsp";
		}
		
	}
	@RequestMapping("/loginOut")
	public String loginOut() {
		Cookie cookie = new Cookie("login_backend", "");
		cookie.setMaxAge(60*60*12);cookie.setPath("/");
		response.addCookie(cookie);
		List<User> loginUsers = (List<User>) application.getAttribute("loginUsers");
		User user = (User)session.getAttribute("login_user");
		User removeUser = null;
		for(User u:loginUsers){
			if(u.getUsername().equals(user.getUsername())){
				removeUser = u;
			}
		}
		if(removeUser!=null)loginUsers.remove(removeUser);
		loginUsers.remove(removeUser);
		session.removeAttribute("login_user");
			Cookie login_user_cookie = new Cookie("login_user","");
			login_user_cookie.setMaxAge(60*60*12);
			login_user_cookie.setPath("/");
			response.addCookie(login_user_cookie);
		return "redirect:/backend/login.jsp";
	}
	@ResponseBody
	@RequestMapping("/loginOutAjax")
	public String loginOutAjax() {
		Cookie cookie = new Cookie("login_backend", "");
		cookie.setMaxAge(60*60*12);cookie.setPath("/");
		response.addCookie(cookie);
		session.removeAttribute("login_user");
			Cookie login_user_cookie = new Cookie("login_user","");
			login_user_cookie.setMaxAge(60*60*12);
			login_user_cookie.setPath("/");
			response.addCookie(login_user_cookie);
		return null;
	}
	@ResponseBody
	@RequestMapping("/loginf")
	public String loginf() throws Exception{
		session = request.getSession(true);
		response.setContentType("text/plain");
		response.setHeader("Cache-Control", "no-cache");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = null;
		String error = "";
		PrintWriter out = response.getWriter();
		try {
			user = userDao.login(username, password);
		} catch (RuntimeException e) {
			error += e.getMessage();
		}
		if (user != null&&user.getRole().getId()!=1&&user.getRole().getId()!=2&&user.getRole().getId()!=3) {
			logger.info(user.getRole().getName() + user.getUsername() + "成功登录.");
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

			out.write("true");
		} else {
			out.write(error);
		}
		out.flush();
		out.close();
		return null;
	}
	@RequestMapping("/loginOutf")
	public String loginOutf() {
		Cookie cookie = new Cookie("login_site", "");
		cookie.setMaxAge(60*60*12);cookie.setPath("/");
		response.addCookie(cookie);
		List<User> loginUsers = (List<User>) application.getAttribute("loginUsers");
		User user = (User)session.getAttribute("login_user");
		User removeUser = null;
		for(User u:loginUsers){
			if(u.getUsername().equals(user.getUsername())){
				removeUser = u;
			}
		}
		if(removeUser!=null)loginUsers.remove(removeUser);
		loginUsers.remove(removeUser);
		session.removeAttribute("login_user");
			Cookie login_user_cookie = new Cookie("login_user","");
			login_user_cookie.setMaxAge(60*60*12);
			login_user_cookie.setPath("/");
			response.addCookie(login_user_cookie);
		return "redirect:/site/list";
	}
	@ResponseBody
	@RequestMapping("/loginOutfAjax")
	public String loginOutfAjax() {
		Cookie cookie = new Cookie("login_site", "");
		cookie.setMaxAge(60*60*12);cookie.setPath("/");
		response.addCookie(cookie);
		session.removeAttribute("login_user");
			Cookie login_user_cookie = new Cookie("login_user","");
			login_user_cookie.setMaxAge(60*60*12);
			login_user_cookie.setPath("/");
			response.addCookie(login_user_cookie);
		return null;
	}
	@ResponseBody
	@RequestMapping("/loginAccount")
	public String loginAccount() throws Exception{
		session = request.getSession(true);
		response.setContentType("text/plain");
		response.setHeader("Cache-Control", "no-cache");
		String id = request.getParameter("id");
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
		PrintWriter out = response.getWriter();
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
			
			
			out.write("1");
		} else {
			out.write("0");
		}
		out.flush();
		out.close();
		return null;
	}
	@RequestMapping("/list")
	public String execute(){
		return "forward:/backend/login/login";
	}
	


	
	
}
