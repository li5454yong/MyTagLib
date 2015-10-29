package com.lxg.service;

import org.springframework.stereotype.Service;

import com.lxg.entity.User;
import com.lxg.util.PageList;
import com.lxg.util.PageProperty;

/**
 * @author lxg
 *
 * 2015年10月28日下午9:11:10
 */

public interface UserService {
	
	public PageList<User> getList(PageProperty pp);
}
