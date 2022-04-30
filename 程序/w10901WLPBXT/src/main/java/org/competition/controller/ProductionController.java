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
import org.competition.model.Production;
import org.competition.service.ProductionService;
import org.competition.model.Search;import org.competition.model.User;import org.competition.utils.PageContext;
import org.competition.utils.ResultUtils;
import org.competition.model.Member;
import org.competition.model.Judge;
import org.competition.model.Search;
import org.competition.model.Member;
import org.competition.service.MemberService;


@Controller
@Scope("prototype")
@RequestMapping(value={"/backend/production","/production"})
public class ProductionController extends BaseController {

	@Resource
	private ProductionService productionService;
	@Resource
	private MemberService memberService ;
	private Production production;
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
		request.setAttribute("memberList", memberService.search(searchNew));
		if(user!=null&&"2".equals(user.getRole().getId()+"")&&search.getType().equals("backend")){
			search.setAuthorityName("member");
			search.setAuthorityValue(memberLogin.getId()+"");
		}
		request.setAttribute("search", search);
		PageContext.getPage().setPageSize(ps);
		return "backend/production/add_production";
	}
	@RequestMapping("/add")
	public String add(){
		productionService.add(production);
		return "backend/production/add_production_success";
	}

	@RequestMapping("/delete")
	public String delete(){
		productionService.delete(productionService.findById(production.getId()));
		return "forward:/backend/production/list";
	}
	@RequestMapping("/deleteBetch")
	public String deleteBetch(){
		String[] productionIds = request.getParameterValues("productionCheckbox");
		if(productionIds!=null){
			for (int i = 0; i < productionIds.length; i++) {
				productionService.delete(productionService.findById(Integer.parseInt(productionIds[i])));
			}
		}
		return "forward:/backend/production/list";
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
		request.setAttribute("production", productionService.findById(production.getId()));
		request.setAttribute("memberList", memberService.search(searchNew));
		if(user!=null&&"2".equals(user.getRole().getId()+"")&&search.getType().equals("backend")){
			search.setAuthorityName("member");
			search.setAuthorityValue(memberLogin.getId()+"");
		}
		PageContext.getPage().setPageNo(pageNo);
		PageContext.getPage().setPageSize(pageSize);
		request.setAttribute("page", PageContext.getPage());
		request.setAttribute("search", search);
		return "backend/production/update_production_input";
	}
	@RequestMapping("/show")
	public String show(){
		Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int pageNo = PageContext.getPage().getPageNo();
		int pageSize = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		request.setAttribute("production", productionService.findById(production.getId()));
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("search", search);
		PageContext.getPage().setPageNo(pageNo);
		PageContext.getPage().setPageSize(pageSize);
		return "backend/production/show_production";
	}

	@RequestMapping("/update")
	public String update(){
		productionService.update(production);
		productionService.sessionFlush();
		productionService.sessionRefresh(production);
		return "forward:/backend/production/list";
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
		request.setAttribute("memberList", memberService.search(searchNew));
		if(user!=null&&"2".equals(user.getRole().getId()+"")&&search.getType().equals("backend")){
			search.setAuthorityName("member");
			search.setAuthorityValue(memberLogin.getId()+"");
		}
		PageContext.getPage().setPageNo(pageNo);
		PageContext.getPage().setPageSize(pageSize);
		request.setAttribute("productionList", productionService.search(search));
		return "backend/production/production_list";
	}

	
	@ModelAttribute
	public void init(Model model,Production production,Search search) {
		this.production=production;
		this.search=search;
		model.asMap().clear();
	}
}

