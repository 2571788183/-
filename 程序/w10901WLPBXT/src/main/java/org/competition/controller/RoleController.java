package org.competition.controller;

import javax.annotation.Resource;

import org.competition.model.Role;
import org.competition.service.RoleService;
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
@RequestMapping(value={"/backend/role","/role"})
public class RoleController extends BaseController{
	
	@Resource
	private RoleService roleService;
	private Role role;
	private Search search;
	
	@RequestMapping("/addInput")
	public String addInput() {
		return "backend/role/add_role";
	}
	
	@RequestMapping("/add")
	public String add(){
		roleService.add(role);
		return "backend/role/add_role_success";
	}
	@RequestMapping("/delete")
	public String delete(){
		roleService.delete(role);
		return "forward:/backend/role/list";
	}
	@RequestMapping("/deleteBetch")
	public String deleteBetch(){
		String[] roleIds = request.getParameterValues("roleCheckbox");
		if(roleIds!=null){
			for (int i = 0; i < roleIds.length; i++) {
				roleService.delete(roleService.findById(Integer
						.parseInt(roleIds[i])));
			}
		}
		return "forward:/backend/role/list";
	}
	@RequestMapping("/modify")
	public String modify(){
		request.setAttribute("role", roleService.findById(role.getId()));
		return "backend/role/update_role_input";
	}
	@RequestMapping("/update")
	public String update(){
		Role roles =role;
		roleService.update(role);
		return "forward:/backend/role/list";
	}
	
	@RequestMapping("/get")
	public String get(){
		return null;
	}
	@RequestMapping("/list")
	public String list(){
		request.setAttribute("page", PageContext.getPage());
		request.setAttribute("roleList", roleService.search(""));
		return "backend/role/role_list";
	}


	
	
	@ModelAttribute
	public void init(Model model,Role role,Search search) {
		this.role=role;
		this.search=search;
		model.asMap().clear();
	}
}
