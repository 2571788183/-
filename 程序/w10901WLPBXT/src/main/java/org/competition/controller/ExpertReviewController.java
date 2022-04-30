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
import org.competition.model.ExpertReview;
import org.competition.service.ExpertReviewService;
import org.competition.model.Search;import org.competition.model.User;import org.competition.utils.PageContext;
import org.competition.utils.ResultUtils;
import org.competition.model.Member;
import org.competition.model.Judge;
import org.competition.model.Search;
import org.competition.model.Production;
import org.competition.service.ProductionService;
import org.competition.model.Search;
import org.competition.model.Judge;
import org.competition.service.JudgeService;
import org.competition.model.Search;
import org.competition.model.Member;
import org.competition.service.MemberService;


@Controller
@Scope("prototype")
@RequestMapping(value={"/backend/expertReview","/expertReview"})
public class ExpertReviewController extends BaseController {

	@Resource
	private ExpertReviewService expertReviewService;
	@Resource
	private ProductionService productionService ;
	@Resource
	private JudgeService judgeService ;
	@Resource
	private MemberService memberService ;
	private ExpertReview expertReview;
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
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("memberList", memberService.search(searchNew));
		if(user!=null&&"3".equals(user.getRole().getId()+"")&&search.getType().equals("backend")){
			search.setAuthorityName("judge");
			search.setAuthorityValue(judgeLogin.getId()+"");
		}
		if(user!=null&&"2".equals(user.getRole().getId()+"")&&search.getType().equals("backend")){
			search.setAuthorityName("member");
			search.setAuthorityValue(memberLogin.getId()+"");
		}
		request.setAttribute("search", search);
		PageContext.getPage().setPageSize(ps);
		return "backend/expertReview/add_expertReview";
	}
	@RequestMapping("/add")
	public String add(){
		expertReviewService.add(expertReview);
		return "backend/expertReview/add_expertReview_success";
	}

	@RequestMapping("/delete")
	public String delete(){
		expertReviewService.delete(expertReviewService.findById(expertReview.getId()));
		return "forward:/backend/expertReview/list";
	}
	@RequestMapping("/deleteBetch")
	public String deleteBetch(){
		String[] expertReviewIds = request.getParameterValues("expertReviewCheckbox");
		if(expertReviewIds!=null){
			for (int i = 0; i < expertReviewIds.length; i++) {
				expertReviewService.delete(expertReviewService.findById(Integer.parseInt(expertReviewIds[i])));
			}
		}
		return "forward:/backend/expertReview/list";
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
		request.setAttribute("expertReview", expertReviewService.findById(expertReview.getId()));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("memberList", memberService.search(searchNew));
		if(user!=null&&"3".equals(user.getRole().getId()+"")&&search.getType().equals("backend")){
			search.setAuthorityName("judge");
			search.setAuthorityValue(judgeLogin.getId()+"");
		}
		if(user!=null&&"2".equals(user.getRole().getId()+"")&&search.getType().equals("backend")){
			search.setAuthorityName("member");
			search.setAuthorityValue(memberLogin.getId()+"");
		}
		PageContext.getPage().setPageNo(pageNo);
		PageContext.getPage().setPageSize(pageSize);
		request.setAttribute("page", PageContext.getPage());
		request.setAttribute("search", search);
		return "backend/expertReview/update_expertReview_input";
	}
	@RequestMapping("/show")
	public String show(){
		Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int pageNo = PageContext.getPage().getPageNo();
		int pageSize = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		request.setAttribute("expertReview", expertReviewService.findById(expertReview.getId()));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("search", search);
		PageContext.getPage().setPageNo(pageNo);
		PageContext.getPage().setPageSize(pageSize);
		return "backend/expertReview/show_expertReview";
	}

	@RequestMapping("/update")
	public String update(){
		expertReviewService.update(expertReview);
		expertReviewService.sessionFlush();
		expertReviewService.sessionRefresh(expertReview);
		return "forward:/backend/expertReview/list";
	}
	@RequestMapping("/modifyYijiancontent")
	public String modifyYijiancontent(){
		int id = Integer.parseInt(request.getParameter("id"));
		Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int ps = PageContext.getPage().getPageSize();
		int pn = PageContext.getPage().getPageNo();
		PageContext.getPage().setPageSize(10000);
		request.setAttribute("expertReview", expertReviewService.findById(id));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("memberList", memberService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		PageContext.getPage().setPageNo(pn);
		request.setAttribute("search", search);
		return "backend/expertReview/updateYijiancontent_expertReview_input";
	}

	@RequestMapping("/updateYijiancontent")
	public String updateYijiancontent(){
		ExpertReview dbExpertReview =expertReviewService.findById(expertReview.getId());
		dbExpertReview.setYijiancontent(expertReview.getYijiancontent());
		expertReviewService.update(dbExpertReview);
		expertReviewService.sessionFlush();
		expertReviewService.sessionRefresh(dbExpertReview);
		return "forward:/backend/expertReview/list";
	}
	@RequestMapping("/modifyScore")
	public String modifyScore(){
		int id = Integer.parseInt(request.getParameter("id"));
		Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int ps = PageContext.getPage().getPageSize();
		int pn = PageContext.getPage().getPageNo();
		PageContext.getPage().setPageSize(10000);
		request.setAttribute("expertReview", expertReviewService.findById(id));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("memberList", memberService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		PageContext.getPage().setPageNo(pn);
		request.setAttribute("search", search);
		return "backend/expertReview/updateScore_expertReview_input";
	}

	@RequestMapping("/updateScore")
	public String updateScore(){
		ExpertReview dbExpertReview =expertReviewService.findById(expertReview.getId());
		dbExpertReview.setScore(expertReview.getScore());
		expertReviewService.update(dbExpertReview);
		expertReviewService.sessionFlush();
		expertReviewService.sessionRefresh(dbExpertReview);
		return "forward:/backend/expertReview/list";
	}
	@RequestMapping("/modifyJieluncontent")
	public String modifyJieluncontent(){
		int id = Integer.parseInt(request.getParameter("id"));
		Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int ps = PageContext.getPage().getPageSize();
		int pn = PageContext.getPage().getPageNo();
		PageContext.getPage().setPageSize(10000);
		request.setAttribute("expertReview", expertReviewService.findById(id));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("memberList", memberService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		PageContext.getPage().setPageNo(pn);
		request.setAttribute("search", search);
		return "backend/expertReview/updateJieluncontent_expertReview_input";
	}

	@RequestMapping("/updateJieluncontent")
	public String updateJieluncontent(){
		ExpertReview dbExpertReview =expertReviewService.findById(expertReview.getId());
		dbExpertReview.setJieluncontent(expertReview.getJieluncontent());
		expertReviewService.update(dbExpertReview);
		expertReviewService.sessionFlush();
		expertReviewService.sessionRefresh(dbExpertReview);
		return "forward:/backend/expertReview/list";
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
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("memberList", memberService.search(searchNew));
		if(user!=null&&"3".equals(user.getRole().getId()+"")&&search.getType().equals("backend")){
			search.setAuthorityName("judge");
			search.setAuthorityValue(judgeLogin.getId()+"");
		}
		if(user!=null&&"2".equals(user.getRole().getId()+"")&&search.getType().equals("backend")){
			search.setAuthorityName("member");
			search.setAuthorityValue(memberLogin.getId()+"");
		}
		PageContext.getPage().setPageNo(pageNo);
		PageContext.getPage().setPageSize(pageSize);
		request.setAttribute("expertReviewList", expertReviewService.search(search));
		return "backend/expertReview/expertReview_list";
	}

	@RequestMapping("/list1")
	public String list1(){
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
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("memberList", memberService.search(searchNew));
		if(user!=null&&"3".equals(user.getRole().getId()+"")&&search.getType().equals("backend")){
			search.setAuthorityName("judge");
			search.setAuthorityValue(judgeLogin.getId()+"");
		}
		if(user!=null&&"2".equals(user.getRole().getId()+"")&&search.getType().equals("backend")){
			search.setAuthorityName("member");
			search.setAuthorityValue(memberLogin.getId()+"");
		}
		PageContext.getPage().setPageNo(pageNo);
		PageContext.getPage().setPageSize(pageSize);
		request.setAttribute("expertReviewList", expertReviewService.search(search));
		return "backend/expertReview/expertReview_list1";
	}

	
	@ModelAttribute
	public void init(Model model,ExpertReview expertReview,Search search) {
		this.expertReview=expertReview;
		this.search=search;
		model.asMap().clear();
	}
}

