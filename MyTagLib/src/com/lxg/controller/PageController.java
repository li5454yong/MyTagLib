package com.lxg.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lxg.entity.User;
import com.lxg.service.UserService;
import com.lxg.util.PageList;
import com.lxg.util.PageProperty;

/**
 * @author lxg
 *
 * 2015年10月28日下午8:26:21
 */
@Controller
public class PageController {
	
	@Resource
	private UserService service;
	
	@RequestMapping("list")
	public String getList(ModelMap map,HttpServletRequest request,HttpServletResponse response){
		PageProperty pp = new PageProperty();
		
		String pageNo = request.getParameter("pageNo"); 
		String pageSize = request.getParameter("pageSize");
		int pageNo1 = 0;
		int pageSize1 = 6;
		if(!"".equals(pageNo)&&pageNo!=null){
			pageNo1 = Integer.parseInt(pageNo);
		}
		if(!"".equals(pageSize)&&pageSize!=null){
			pageSize1 = Integer.parseInt(pageSize);
		}
		pp.setNpage(pageNo1);
		pp.setNpagesize(pageSize1);
		PageList<User> list = service.getList(pp);
		map.put("data", list);
		return "index";
	}
}
