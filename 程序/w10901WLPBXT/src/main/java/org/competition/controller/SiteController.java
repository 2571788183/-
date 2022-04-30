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
import org.competition.utils.PageContext;
import org.competition.utils.ResultUtils;
import org.competition.model.Search;
import org.competition.model.Member;
import org.competition.service.MemberService;
import org.competition.model.Judge;
import org.competition.service.JudgeService;
import org.competition.model.Signup;
import org.competition.service.SignupService;
import org.competition.model.Production;
import org.competition.service.ProductionService;
import org.competition.model.ExpertReview;
import org.competition.service.ExpertReviewService;
import org.competition.model.Shenhe;
import org.competition.service.ShenheService;


@Controller
@Scope("prototype")
@RequestMapping(value={"/site"})
public class SiteController extends BaseController {

	@Resource
	private MemberService memberService ;
	@Resource
	private JudgeService judgeService ;
	@Resource
	private SignupService signupService ;
	@Resource
	private ProductionService productionService ;
	@Resource
	private ExpertReviewService expertReviewService ;
	@Resource
	private ShenheService shenheService ;
	private String url="";
	private Search search;
	@RequestMapping("/addMemberInput")
	public String addMemberInput(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		Member member = ResultUtils.copyParams(Member.class, request);
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("member", memberService.findById(search.getId()));
		return "add_member";
	}
	@RequestMapping("/addMember")
	public String addMember(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		Member member = ResultUtils.copyParams(Member.class, request);
		memberService.add(member);
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("member", memberService.findById(search.getId()));
		return "redirect:"+url;
	}
	
	@RequestMapping("/deleteMember")
	public String deleteMember(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		Member member = ResultUtils.copyParams(Member.class, request);
		memberService.delete(member);
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("member", memberService.findById(search.getId()));
		return "redirect:"+url;
	}
	
	@RequestMapping("/member")
	public String member(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("member", memberService.findById(search.getId()));
		return "member";
	}
	
	@RequestMapping("/members")
	public String members(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		request.setAttribute("search", search);
		PageContext.getPage().setPageSize(ps);
		return "members";
	}
	
	@RequestMapping("/addJudgeInput")
	public String addJudgeInput(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		Judge judge = ResultUtils.copyParams(Judge.class, request);
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("judge", judgeService.findById(search.getId()));
		return "add_judge";
	}
	@RequestMapping("/addJudge")
	public String addJudge(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		Judge judge = ResultUtils.copyParams(Judge.class, request);
		judgeService.add(judge);
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("judge", judgeService.findById(search.getId()));
		return "redirect:"+url;
	}
	
	@RequestMapping("/deleteJudge")
	public String deleteJudge(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		Judge judge = ResultUtils.copyParams(Judge.class, request);
		judgeService.delete(judge);
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("judge", judgeService.findById(search.getId()));
		return "redirect:"+url;
	}
	
	@RequestMapping("/judge")
	public String judge(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("judge", judgeService.findById(search.getId()));
		return "judge";
	}
	
	@RequestMapping("/judges")
	public String judges(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		request.setAttribute("search", search);
		PageContext.getPage().setPageSize(ps);
		return "judges";
	}
	
	@RequestMapping("/addSignupInput")
	public String addSignupInput(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		Signup signup = ResultUtils.copyParams(Signup.class, request);
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("signup", signupService.findById(search.getId()));
		return "add_signup";
	}
	@RequestMapping("/addSignup")
	public String addSignup(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		Signup signup = ResultUtils.copyParams(Signup.class, request);
		signupService.add(signup);
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("signup", signupService.findById(search.getId()));
		return "redirect:"+url;
	}
	
	@RequestMapping("/deleteSignup")
	public String deleteSignup(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		Signup signup = ResultUtils.copyParams(Signup.class, request);
		signupService.delete(signup);
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("signup", signupService.findById(search.getId()));
		return "redirect:"+url;
	}
	
	@RequestMapping("/signup")
	public String signup(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("signup", signupService.findById(search.getId()));
		return "signup";
	}
	
	@RequestMapping("/signups")
	public String signups(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		request.setAttribute("search", search);
		PageContext.getPage().setPageSize(ps);
		return "signups";
	}
	
	@RequestMapping("/addProductionInput")
	public String addProductionInput(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		Production production = ResultUtils.copyParams(Production.class, request);
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("production", productionService.findById(search.getId()));
		return "add_production";
	}
	@RequestMapping("/addProduction")
	public String addProduction(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		Production production = ResultUtils.copyParams(Production.class, request);
		productionService.add(production);
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("production", productionService.findById(search.getId()));
		return "redirect:"+url;
	}
	
	@RequestMapping("/deleteProduction")
	public String deleteProduction(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		Production production = ResultUtils.copyParams(Production.class, request);
		productionService.delete(production);
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("production", productionService.findById(search.getId()));
		return "redirect:"+url;
	}
	
	@RequestMapping("/production")
	public String production(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("production", productionService.findById(search.getId()));
		return "production";
	}
	
	@RequestMapping("/productions")
	public String productions(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		request.setAttribute("search", search);
		PageContext.getPage().setPageSize(ps);
		return "productions";
	}
	
	@RequestMapping("/addExpertReviewInput")
	public String addExpertReviewInput(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		ExpertReview expertReview = ResultUtils.copyParams(ExpertReview.class, request);
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("expertReview", expertReviewService.findById(search.getId()));
		return "add_expertReview";
	}
	@RequestMapping("/addExpertReview")
	public String addExpertReview(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		ExpertReview expertReview = ResultUtils.copyParams(ExpertReview.class, request);
		expertReviewService.add(expertReview);
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("expertReview", expertReviewService.findById(search.getId()));
		return "redirect:"+url;
	}
	
	@RequestMapping("/deleteExpertReview")
	public String deleteExpertReview(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		ExpertReview expertReview = ResultUtils.copyParams(ExpertReview.class, request);
		expertReviewService.delete(expertReview);
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("expertReview", expertReviewService.findById(search.getId()));
		return "redirect:"+url;
	}
	
	@RequestMapping("/expertReview")
	public String expertReview(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("expertReview", expertReviewService.findById(search.getId()));
		return "expertReview";
	}
	
	@RequestMapping("/expertReviews")
	public String expertReviews(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		request.setAttribute("search", search);
		PageContext.getPage().setPageSize(ps);
		return "expertReviews";
	}
	
	@RequestMapping("/addShenheInput")
	public String addShenheInput(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		Shenhe shenhe = ResultUtils.copyParams(Shenhe.class, request);
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("shenhe", shenheService.findById(search.getId()));
		return "add_shenhe";
	}
	@RequestMapping("/addShenhe")
	public String addShenhe(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		Shenhe shenhe = ResultUtils.copyParams(Shenhe.class, request);
		shenheService.add(shenhe);
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("shenhe", shenheService.findById(search.getId()));
		return "redirect:"+url;
	}
	
	@RequestMapping("/deleteShenhe")
	public String deleteShenhe(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		Shenhe shenhe = ResultUtils.copyParams(Shenhe.class, request);
		shenheService.delete(shenhe);
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("shenhe", shenheService.findById(search.getId()));
		return "redirect:"+url;
	}
	
	@RequestMapping("/shenhe")
	public String shenhe(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		PageContext.getPage().setPageSize(ps);
		request.setAttribute("search", search);
		request.setAttribute("shenhe", shenheService.findById(search.getId()));
		return "shenhe";
	}
	
	@RequestMapping("/shenhes")
	public String shenhes(){
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		request.setAttribute("search", search);
		PageContext.getPage().setPageSize(ps);
		return "shenhes";
	}
	
	@RequestMapping("/list")
	public String list(){
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		request.setAttribute("page", PageContext.getPage());
		String strid = request.getParameter("searchid");
		String name = request.getParameter("str");
		//Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		int id = 0;
		if (!"".equals(strid) && strid != null)
			id = Integer.parseInt(strid);

		Search search2 = new Search();
		
		
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		request.setAttribute("search", search);
		request.setAttribute("page", PageContext.getPage());

		PageContext.getPage().setPageSize(ps);
		return "index";
	}

	@RequestMapping("/search")
	public String search(){
		int ps = PageContext.getPage().getPageSize();
		PageContext.getPage().setPageSize(10000);
		request.setAttribute("page", PageContext.getPage());
		Search search = ResultUtils.copyParams(Search.class, request);
		Search searchNew = new Search();
		Search search2 = new Search();
		request.setAttribute("memberList", memberService.search(searchNew));
		request.setAttribute("judgeList", judgeService.search(searchNew));
		request.setAttribute("signupList", signupService.search(searchNew));
		request.setAttribute("productionList", productionService.search(searchNew));
		request.setAttribute("expertReviewList", expertReviewService.search(searchNew));
		request.setAttribute("shenheList", shenheService.search(searchNew));
		
		request.setAttribute("search", search);
		request.setAttribute("page", PageContext.getPage());

		PageContext.getPage().setPageSize(ps);
		return "search";
	}

	@ModelAttribute
	public void init(Model model,Search search,String url) {
		this.search=search;
		this.url=url;
		model.asMap().clear();
	}
}

