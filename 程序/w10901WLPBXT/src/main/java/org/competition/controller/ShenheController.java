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
import org.competition.model.Shenhe;
import org.competition.service.ShenheService;
import org.competition.model.Search;import org.competition.model.User;import org.competition.utils.PageContext;
import org.competition.utils.ResultUtils;
import org.competition.model.Member;
import org.competition.model.Judge;


@Controller
@Scope("prototype")
@RequestMapping(value={"/backend/shenhe","/shenhe"})
public class ShenheController extends BaseController {

	@Resource
	private ShenheService shenheService;
	private Shenhe shenhe;
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
		request.setAttribute("search", search);
		PageContext.getPage().setPageSize(ps);
		return "backend/shenhe/add_shenhe";
	}
	@RequestMapping("/add")
	public String add(){
		shenheService.add(shenhe);
		return "backend/shenhe/add_shenhe_success";
	}

	@RequestMapping("/delete")
	public String delete(){
		shenheService.delete(shenheService.findById(shenhe.getId()));
		return "forward:/backend/shenhe/list";
	}
	@RequestMapping("/deleteBetch")
	public String deleteBetch(){
		String[] shenheIds = request.getParameterValues("shenheCheckbox");
		if(shenheIds!=null){
			for (int i = 0; i < shenheIds.length; i++) {
				shenheService.delete(shenheService.findById(Integer.parseInt(shenheIds[i])));
			}
		}
		return "forward:/backend/shenhe/list";
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
		request.setAttribute("shenhe", shenheService.findById(shenhe.getId()));
		PageContext.getPage().setPageNo(pageNo);
		PageContext.getPage().setPageSize(pageSize);
		request.setAttribute("page", PageContext.getPage());
		request.setAttribute("search", search);
		return "backend/shenhe/update_shenhe_input";
	}
	@RequestMapping("/show")
	public String show(){
		Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int pageNo = PageContext.getPage().getPageNo();
		int pageSize = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		request.setAttribute("shenhe", shenheService.findById(shenhe.getId()));
		request.setAttribute("search", search);
		PageContext.getPage().setPageNo(pageNo);
		PageContext.getPage().setPageSize(pageSize);
		return "backend/shenhe/show_shenhe";
	}

	@RequestMapping("/update")
	public String update(){
		shenheService.update(shenhe);
		shenheService.sessionFlush();
		shenheService.sessionRefresh(shenhe);
		return "forward:/backend/shenhe/list";
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
		PageContext.getPage().setPageNo(pageNo);
		PageContext.getPage().setPageSize(pageSize);
		request.setAttribute("shenheList", shenheService.search(search));
		return "backend/shenhe/shenhe_list";
	}

	
	@ModelAttribute
	public void init(Model model,Shenhe shenhe,Search search) {
		this.shenhe=shenhe;
		this.search=search;
		model.asMap().clear();
	}
}

