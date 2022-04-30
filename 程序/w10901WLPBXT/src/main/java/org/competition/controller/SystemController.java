package org.competition.controller;

import java.io.PrintWriter;

import javax.annotation.Resource;

import org.competition.model.User;
import org.competition.service.UserService;
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
@RequestMapping(value={"/backend/sys","/sys"})
public class SystemController extends BaseController{
	
	@Resource
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/checkOld")
	public String checkOld() throws Exception{
		response.setContentType("text/plain");
  		response.setHeader("Cache-Control", "no-cache");
		User user = (User) request.getSession().getAttribute("login_user");
		String s = request.getParameter("password");
        PrintWriter out=response.getWriter();
        if(user.getPassword().equals(s))
        	out.write("1");
        else 
        	out.write("0");
        out.flush();
        out.close();
		return null;
	}
	@RequestMapping("/updatePassword")
	public String updatePassword(){
		User user = (User) request.getSession().getAttribute("login_user");
		String password = request.getParameter("new");
		user.setPassword(password);
		userService.update(user);
		return "redirect:/backend/sys/update_password_success.jsp";
	}
	@RequestMapping("/updatePassword1")
	public String updatePassword1(){
		User user = (User) request.getSession().getAttribute("login_user");
		String password = request.getParameter("new");
		user.setPassword(password);
		userService.update(user);
		return "redirect:/member_update_password_success.jsp";
	}
	
	


	
	
}
