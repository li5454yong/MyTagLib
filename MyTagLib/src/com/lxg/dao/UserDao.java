package com.lxg.dao;

import java.util.List;
import java.util.Map;



import org.springframework.stereotype.Repository;

import com.lxg.entity.User;

/**
 * @author lxg
 *
 * 2015年10月26日下午8:54:38
 */
@Repository
public interface UserDao {
	public int getCount();
	public List<User> list(Map<String,Object> parm);
}
