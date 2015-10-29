package com.lxg.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lxg.dao.UserDao;
import com.lxg.entity.User;
import com.lxg.service.UserService;
import com.lxg.util.PageList;
import com.lxg.util.PageProperty;
import com.lxg.util.PageUtil;

/**
 * @author lxg
 *
 * 2015年10月28日下午9:12:31
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao dao;
	@Override
	public PageList<User> getList(PageProperty pp) {
		
		int count = dao.getCount();
		int start = PageUtil.getStart(pp.getNpage(),count, pp.getNpagesize());
		int end = pp.getNpagesize();
		pp.putParamMap("start", start);
		pp.putParamMap("end", end);
		PageList<User> list = new PageList<User>(pp, count, dao.list(pp.getParamMap()));
		return list;
	}

}
