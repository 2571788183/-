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
import org.competition.model.Signup;
import org.competition.service.SignupService;
import org.competition.model.Search;import org.competition.model.User;import org.competition.utils.PageContext;
import org.competition.utils.ResultUtils;
import org.competition.model.Member;
import org.competition.model.Judge;
import org.competition.model.Search;
import org.competition.model.Shenhe;
import org.competition.service.ShenheService;
import org.competition.model.Search;
import org.competition.model.Member;
import org.competition.service.MemberService;


@Controller
@Scope("prototype")
@RequestMapping(value={"/backend/signup","/signup"})
public class SignupController extends BaseController {

	@Resource
	private SignupService signupService;
	@Resource
	private ShenheService shenheService ;
	@Resource
	private MemberService memberService ;
	private Signup signup;
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
		request.setAttribute("shenheList", shenheService.search(searchNew));
		request.setAttribute("memberList", memberService.search(searchNew));
		if(user!=null&&"2".equals(user.getRole().getId()+"")&&search.getType().equals("backend")){
			search.setAuthorityName("member");
			search.setAuthorityValue(memberLogin.getId()+"");
		}
		request.setAttribute("search", search);
		PageContext.getPage().setPageSize(ps);
		return "backend/signup/add_signup";
	}
	@RequestMapping("/add")
	public String add(){
		signupService.add(signup);
		return "backend/signup/add_signup_success";
	}

	@RequestMapping("/delete")
	public String delete(){
		signupService.delete(signupService.findById(signup.getId()));
		return "forward:/backend/signup/list";
	}
	@RequestMapping("/deleteBetch")
	public String deleteBetch(){
		String[] signupIds = request.getParameterValues("signupCheckbox");
		if(signupIds!=null){
			for (int i = 0; i < signupIds.length; i++) {
				signupService.delete(signupService.findById(Integer.parseInt(signupIds[i])));
			}
		}
		return "forward:/backend/signup/list";
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
		request.setAttribute("signup", signupService.findById(signup.getId()));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		request.setAttribute("memberList", memberService.search(searchNew));
		if(user!=null&&"2".equals(user.getRole().getId()+"")&&search.getType().equals("backend")){
			search.setAuthorityName("member");
			search.setAuthorityValue(memberLogin.getId()+"");
		}
		PageContext.getPage().setPageNo(pageNo);
		PageContext.getPage().setPageSize(pageSize);
		request.setAttribute("page", PageContext.getPage());
		request.setAttribute("search", search);
		return "backend/signup/update_signup_input";
	}
	@RequestMapping("/show")
	public String show(){
		Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int pageNo = PageContext.getPage().getPageNo();
		int pageSize = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		request.setAttribute("signup", signupService.findById(signup.getId()));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("search", search);
		PageContext.getPage().setPageNo(pageNo);
		PageContext.getPage().setPageSize(pageSize);
		return "backend/signup/show_signup";
	}

	@RequestMapping("/update")
	public String update(){
		signupService.update(signup);
		signupService.sessionFlush();
		signupService.sessionRefresh(signup);
		return "forward:/backend/signup/list";
	}
	@RequestMapping("/modifyShenhe")
	public String modifyShenhe(){
		int id = Integer.parseInt(request.getParameter("id"));
		Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int ps = PageContext.getPage().getPageSize();
		int pn = PageContext.getPage().getPageNo();
		PageContext.getPage().setPageSize(10000);
		request.setAttribute("signup", signupService.findById(id));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		request.setAttribute("memberList", memberService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		PageContext.getPage().setPageNo(pn);
		request.setAttribute("search", search);
		return "backend/signup/updateShenhe_signup_input";
	}

	@RequestMapping("/updateShenhe")
	public String updateShenhe(){
		Signup dbSignup =signupService.findById(signup.getId());
		dbSignup.setShenhe(signup.getShenhe());
		signupService.update(dbSignup);
		signupService.sessionFlush();
		signupService.sessionRefresh(dbSignup);
		return "forward:/backend/signup/list";
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
		request.setAttribute("shenheList", shenheService.search(searchNew));
		request.setAttribute("memberList", memberService.search(searchNew));
		if(user!=null&&"2".equals(user.getRole().getId()+"")&&search.getType().equals("backend")){
			search.setAuthorityName("member");
			search.setAuthorityValue(memberLogin.getId()+"");
		}
		PageContext.getPage().setPageNo(pageNo);
		PageContext.getPage().setPageSize(pageSize);
		request.setAttribute("signupList", signupService.search(search));
		return "backend/signup/signup_list";
	}

	
	@ModelAttribute
	public void init(Model model,Signup signup,Search search) {
		this.signup=signup;
		this.search=search;
		model.asMap().clear();
	}
}

