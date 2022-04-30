package org.competition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.context.annotation.Scope;
import javax.annotation.Resource;
import java.io.IOException;
import org.apache.commons.beanutils.BeanUtils;
import org.competition.model.Judge;
import org.competition.service.JudgeService;
import org.competition.model.Search;import org.competition.model.User;import org.competition.utils.PageContext;
import org.competition.utils.ResultUtils;
import org.competition.model.Member;
import org.competition.model.Judge;
import org.competition.model.Search;
import org.competition.model.User;
import org.competition.service.UserService;


@Controller
@Scope("prototype")
@RequestMapping(value={"/backend/judge","/judge"})
public class JudgeController extends BaseController {

	@Resource
	private JudgeService judgeService;
	@Resource
	private UserService userService ;
	private Judge judge;
	private Search search;

	
	@RequestMapping("/addInput")
	public String addInput(){
		Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		User user = (User)request.getSession().getAttribute("login_user");
		Member memberLogin = (Member) request.getSession().getAttribute("member");
		if(user!=null&&"2".equals(user.getRole().getId()+"")&&search.getType().equals("backend")){
			search.setMembersearch(memberLogin.getId()+"");
		}
		Judge judgeLogin = (Judge) request.getSession().getAttribute("judge");
		if(user!=null&&"3".equals(user.getRole().getId()+"")&&search.getType().equals("backend")){
			search.setJudgesearch(judgeLogin.getId()+"");
		}
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		request.setAttribute("usersList", userService.search(searchNew));
		if(user!=null&&"3".equals(user.getRole().getId()+"")){
				search.setAuthorityName("users");
				search.setAuthorityValue(user.getId()+"");
		}
		request.setAttribute("search", search);
		PageContext.getPage().setPageSize(ps);
		return "backend/judge/add_judge";
	}
	@RequestMapping("/add")
	public String add(){
		judgeService.add(judge);
		return "backend/judge/add_judge_success";
	}

	@RequestMapping("/delete")
	public String delete(){
		judgeService.delete(judgeService.findById(judge.getId()));
		return "forward:/backend/judge/list";
	}
	@RequestMapping("/deleteBetch")
	public String deleteBetch(){
		String[] judgeIds = request.getParameterValues("judgeCheckbox");
		if(judgeIds!=null){
			for (int i = 0; i < judgeIds.length; i++) {
				judgeService.delete(judgeService.findById(Integer.parseInt(judgeIds[i])));
			}
		}
		return "forward:/backend/judge/list";
	}

	@RequestMapping("/modify")
	public String modify(){
		Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		User user = (User)request.getSession().getAttribute("login_user");
		Member memberLogin = (Member) request.getSession().getAttribute("member");
		if(user!=null&&"2".equals(user.getRole().getId()+"")&&search.getType().equals("backend")){
			search.setMembersearch(memberLogin.getId()+"");
		}
		Judge judgeLogin = (Judge) request.getSession().getAttribute("judge");
		if(user!=null&&"3".equals(user.getRole().getId()+"")&&search.getType().equals("backend")){
			search.setJudgesearch(judgeLogin.getId()+"");
		}
		int pageNo = PageContext.getPage().getPageNo();
		int pageSize = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		request.setAttribute("judge", judgeService.findById(judge.getId()));
		request.setAttribute("usersList", userService.search(searchNew));
		if(user!=null&&"3".equals(user.getRole().getId()+"")){
				search.setAuthorityName("users");
				search.setAuthorityValue(user.getId()+"");
		}
		PageContext.getPage().setPageNo(pageNo);
		PageContext.getPage().setPageSize(pageSize);
		request.setAttribute("page", PageContext.getPage());
		request.setAttribute("search", search);
		return "backend/judge/update_judge_input";
	}
	@RequestMapping("/show")
	public String show(){
		Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int pageNo = PageContext.getPage().getPageNo();
		int pageSize = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		request.setAttribute("judge", judgeService.findById(judge.getId()));
		request.setAttribute("usersList", userService.search(searchNew));
		request.setAttribute("search", search);
		PageContext.getPage().setPageNo(pageNo);
		PageContext.getPage().setPageSize(pageSize);
		return "backend/judge/show_judge";
	}

	@RequestMapping("/update")
	public String update(){
		judgeService.update(judge);
		judgeService.sessionFlush();
		judgeService.sessionRefresh(judge);
		return "forward:/backend/judge/list";
	}
	
	@RequestMapping("/get")
	public String get(){
		return null;
	}

	@RequestMapping("/list")
	public String list(){
		Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int pageNo = PageContext.getPage().getPageNo();
		int pageSize = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		User user = (User)request.getSession().getAttribute("login_user");
		Member memberLogin = (Member) request.getSession().getAttribute("member");
		if(user!=null&&"2".equals(user.getRole().getId()+"")&&search.getType().equals("backend")){
			search.setMembersearch(memberLogin.getId()+"");
		}
		Judge judgeLogin = (Judge) request.getSession().getAttribute("judge");
		if(user!=null&&"3".equals(user.getRole().getId()+"")&&search.getType().equals("backend")){
			search.setJudgesearch(judgeLogin.getId()+"");
		}
		request.setAttribute("page", PageContext.getPage());
		request.setAttribute("search", search);
		request.setAttribute("usersList", userService.search(searchNew));
		if(user!=null&&"3".equals(user.getRole().getId()+"")){
				search.setAuthorityName("users");
				search.setAuthorityValue(user.getId()+"");
		}
		PageContext.getPage().setPageNo(pageNo);
		PageContext.getPage().setPageSize(pageSize);
		request.setAttribute("judgeList", judgeService.search(search));
		return "backend/judge/judge_list";
	}
	@RequestMapping("/add1")
	public String add1() throws Exception{
		User user = new User();
		BeanUtils.copyProperties(user, judge);
		user.setRole(judge.getRoles());
		String password = request.getParameter("password");
		user.setPassword(password);
		userService.add(user);
		judge.setUsers(user);
		judgeService.add(judge);
		return "backend/judge/add_judge_success";
	}
	@RequestMapping("/modify1")
	public String modify1(){
		User user = (User)request.getSession().getAttribute("login_user");
		request.setAttribute("judge", judgeService.findByUserId(user.getId()));
		request.setAttribute("userList", userService.search(""));
		return "backend/judge/update_judge_input";
	}

	
	@ModelAttribute
	public void init(Model model,Judge judge,Search search) {
		this.judge=judge;
		this.search=search;
		model.asMap().clear();
	}
}

