package com.lxg.controller;

import javax.sql.DataSource;

import org.apache.catalina.core.ApplicationContext;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lxg
 *
 * 2015年10月26日下午8:12:32
 */
@Controller
public class TestController {
	
	@RequestMapping("test")
	public String demo(){
		return "index";
	}
	
	@Test
	public void demo2(){
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		DataSource s = (DataSource) ac.getBean("dataSource");
		System.out.println(s);
	}
}
