package org.competition.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import org.competition.model.User;
import org.competition.service.UserService;
import org.competition.utils.PageContext;
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
@RequestMapping(value={"/backend/user","/user"})
public class UserController extends BaseController{
	
	@Resource
	private UserService userService;
	private User user;
	private Search search;
	
	@ResponseBody
	@RequestMapping("/approval")
	public String approval()throws Exception{
		response.setContentType("text/plain");
		response.setHeader("Cache-Control", "no-cache");
		String username = request.getParameter("username");
		User user = userService.findByUsername(username);
		PrintWriter out = response.getWriter();
		if (user != null)
			out.write("1");
		else
			out.write("0");
		out.flush();
		out.close();
		return null;
	}
	@ResponseBody
	@RequestMapping("/approvalusername")
	public String approvalusername()throws Exception{
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		String truestr = "{\"success\":\"true\"}";
		String falsestr = "{\"success\":\"false\"}";
		String username = request.getParameter("username");
		User user = userService.findByUsername(username);
		PrintWriter out = response.getWriter();
		if (user != null)
			out.write(truestr);
		else
			out.write(falsestr);
		out.flush();
		out.close();
		return null;
	}
	@ResponseBody
	@RequestMapping("/validate")
	public String validate()throws Exception{
		response.setContentType("text/plain");
		response.setHeader("Cache-Control", "no-cache");
		String username = request.getParameter("username");
		User user = userService.findByUsername(username);
		PrintWriter out = response.getWriter();
		if (user != null)
			out.write("1");
		else
			out.write("0");
		out.flush();
		out.close();
		return null;
	}
	@ResponseBody
	@RequestMapping("/getId")
	public String getId()throws Exception{
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		User user = (User) request.getSession().getAttribute("login_user");
		PrintWriter out = response.getWriter();
		if (user != null)
			out.write("" + user.getId());
		else
			out.write("请重新登录");
		out.flush();
		out.close();
		return null;
	}
	@RequestMapping("/view")
	public String view(){
		int id = ((User) request.getSession().getAttribute("login_user")).getId();
		User user = userService.findById(id);
		request.setAttribute("user", user);
		return "backend/user/user_update_input";
	}
	@RequestMapping("/users")
	public String users(){
		ServletContext application = request.getSession().getServletContext();
		request.setAttribute("users", (List<User>)application.getAttribute("loginUsers"));
		request.setAttribute("page", PageContext.getPage());
		return "backend/user/user_list";
	}
	@ResponseBody
	@RequestMapping("/getRole")
	public String getRole()throws Exception{
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		User user = (User) request.getSession().getAttribute("login_user");
		PrintWriter out = response.getWriter();
		if (user != null)
			out.write("" + user.getRole().getId());
		else
			out.write("0");
		out.flush();
		out.close();
		return null;
	}
	@ResponseBody
	@RequestMapping("/getUserId")
	public String getUserId()throws Exception{
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		User user = (User) request.getSession().getAttribute("login_user");
		PrintWriter out = response.getWriter();
		if (user != null)
			out.write("" + user.getId());
		else
			out.write("0");
		out.flush();
		out.close();
		return null;
	}
	@ModelAttribute
	public void init(Model model,User user,Search search) {
		this.user=user;
		this.search=search;
		model.asMap().clear();
	}
}
