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
import org.competition.model.Member;
import org.competition.service.MemberService;
import org.competition.model.Search;import org.competition.model.User;import org.competition.utils.PageContext;
import org.competition.utils.ResultUtils;
import org.competition.model.Member;
import org.competition.model.Judge;
import org.competition.model.Search;
import org.competition.model.User;
import org.competition.service.UserService;


@Controller
@Scope("prototype")
@RequestMapping(value={"/backend/member","/member"})
public class MemberController extends BaseController {

	@Resource
	private MemberService memberService;
	@Resource
	private UserService userService ;
	private Member member;
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
		if(user!=null&&"2".equals(user.getRole().getId()+"")){
				search.setAuthorityName("users");
				search.setAuthorityValue(user.getId()+"");
		}
		request.setAttribute("search", search);
		PageContext.getPage().setPageSize(ps);
		return "backend/member/add_member";
	}
	@RequestMapping("/add")
	public String add(){
		memberService.add(member);
		return "backend/member/add_member_success";
	}

	@RequestMapping("/delete")
	public String delete(){
		memberService.delete(memberService.findById(member.getId()));
		return "forward:/backend/member/list";
	}
	@RequestMapping("/deleteBetch")
	public String deleteBetch(){
		String[] memberIds = request.getParameterValues("memberCheckbox");
		if(memberIds!=null){
			for (int i = 0; i < memberIds.length; i++) {
				memberService.delete(memberService.findById(Integer.parseInt(memberIds[i])));
			}
		}
		return "forward:/backend/member/list";
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
		request.setAttribute("member", memberService.findById(member.getId()));
		request.setAttribute("usersList", userService.search(searchNew));
		if(user!=null&&"2".equals(user.getRole().getId()+"")){
				search.setAuthorityName("users");
				search.setAuthorityValue(user.getId()+"");
		}
		PageContext.getPage().setPageNo(pageNo);
		PageContext.getPage().setPageSize(pageSize);
		request.setAttribute("page", PageContext.getPage());
		request.setAttribute("search", search);
		return "backend/member/update_member_input";
	}
	@RequestMapping("/show")
	public String show(){
		Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int pageNo = PageContext.getPage().getPageNo();
		int pageSize = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		request.setAttribute("member", memberService.findById(member.getId()));
		request.setAttribute("usersList", userService.search(searchNew));
		request.setAttribute("search", search);
		PageContext.getPage().setPageNo(pageNo);
		PageContext.getPage().setPageSize(pageSize);
		return "backend/member/show_member";
	}

	@RequestMapping("/update")
	public String update(){
		memberService.update(member);
		memberService.sessionFlush();
		memberService.sessionRefresh(member);
		return "forward:/backend/member/list";
	}
	@RequestMapping("/modifyIdcardimg")
	public String modifyIdcardimg(){
		int id = Integer.parseInt(request.getParameter("id"));
		Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int ps = PageContext.getPage().getPageSize();
		int pn = PageContext.getPage().getPageNo();
		PageContext.getPage().setPageSize(10000);
		request.setAttribute("member", memberService.findById(id));
		request.setAttribute("usersList", userService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		PageContext.getPage().setPageNo(pn);
		request.setAttribute("search", search);
		return "backend/member/updateIdcardimg_member_input";
	}

	@RequestMapping("/updateIdcardimg")
	public String updateIdcardimg(){
		Member dbMember =memberService.findById(member.getId());
		dbMember.setIdcardimg(member.getIdcardimg());
		memberService.update(dbMember);
		memberService.sessionFlush();
		memberService.sessionRefresh(dbMember);
		return "forward:/backend/member/list";
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
		if(user!=null&&"2".equals(user.getRole().getId()+"")){
				search.setAuthorityName("users");
				search.setAuthorityValue(user.getId()+"");
		}
		PageContext.getPage().setPageNo(pageNo);
		PageContext.getPage().setPageSize(pageSize);
		request.setAttribute("memberList", memberService.search(search));
		return "backend/member/member_list";
	}
	@RequestMapping("/add1")
	public String add1() throws Exception{
		User user = new User();
		BeanUtils.copyProperties(user, member);
		user.setRole(member.getRoles());
		String password = request.getParameter("password");
		user.setPassword(password);
		userService.add(user);
		member.setUsers(user);
		memberService.add(member);
		return "backend/member/add_member_success";
	}
	@RequestMapping("/modify1")
	public String modify1(){
		User user = (User)request.getSession().getAttribute("login_user");
		request.setAttribute("member", memberService.findByUserId(user.getId()));
		request.setAttribute("userList", userService.search(""));
		return "backend/member/update_member_input";
	}

	
	@ModelAttribute
	public void init(Model model,Member member,Search search) {
		this.member=member;
		this.search=search;
		model.asMap().clear();
	}
}

