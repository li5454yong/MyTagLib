package com.lxg.tag;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author lxg
 *
 * 2015年10月21日下午9:49:50
 */
@SuppressWarnings("serial")
public class MyTags extends TagSupport {
	
	private int pageNo; //当前页
	private int pageSize; //一页显示条数
	private int pageCount; //总页数
	private int count; //总记录数
	
	
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		//Enumeration en = request.getParameterNames();
		
		
		//计算总页数
		if(count%pageSize > 0){
			pageCount = count/pageSize + 1;
		}else if(count%pageSize == 0){
			pageCount = count/pageSize;
		}
		//判断传入的pageNo是否在正确范围内
		if(pageNo<1){
			pageNo=1;
		}else if(pageNo>pageCount){
			pageNo = pageCount;
		}
		
		return SKIP_BODY;
	}

	
	public int doEndTag() throws JspException {
		StringBuffer sb = new StringBuffer("<div>");
		/**
		 * 页面中需要显示的按钮分别是：当前页和总页数、首页、上一页、以当前页为中心前后两页（共5页）的页码、下一页、尾页
		 */
		
		if(pageCount<6){//首先判断总页数是否大于5页，如果不到5页，中间的页码就依次显示
			sb.append("<a class='myButton'>第"+pageNo+"页/共"+pageCount+"页</a>&nbsp;&nbsp;");
			sb.append("<a href='javascript:page("+1+","+pageSize+")' class='myButton'>首页</a>&nbsp;&nbsp;");
			sb.append("<a href='javascript:page("+(pageNo-1)+","+pageSize+")' class='myButton'>上一页</a>&nbsp;&nbsp;");
			for(int i=1;i<=pageCount;i++){
				sb.append("<a href='javascript:page("+i+","+pageSize+")' class='myButton'>"+i+"页</a>&nbsp;&nbsp;");
			}
			sb.append("<a href='javascript:page("+(pageNo+1)+","+pageSize+")' class='myButton'>下一页</a>&nbsp;&nbsp;");
			sb.append("<a href='javascript:page("+pageCount+","+pageSize+")' class='myButton'>尾页</a>&nbsp;&nbsp;");
		}else{//总页数大于5页
			sb.append("<a class='myButton'>"+pageNo+"/"+pageCount+"</a>&nbsp;&nbsp;");
			sb.append("<a href='javascript:page("+1+","+pageSize+")' class='myButton'>首页</a>&nbsp;&nbsp;");
			sb.append("<a href='javascript:page("+(pageNo-1)+","+pageSize+")' class='myButton'>上一页</a>&nbsp;&nbsp;");
			if(pageNo-2<=0){//首先判断当前页是不是第1、2页或倒数1、2页，如果是这四页中的其中一页，那么当前页就不能显示在五个页码的中间位置
				for(int i=1; i<=5; i++){
					sb.append("<a href='javascript:page("+i+","+pageSize+")' class='myButton'>"+i+"</a>&nbsp;&nbsp;");
				}
			}else if(pageCount-pageNo<=2){
				for(int i=4;i>=0;i--){
					sb.append("<a href='javascript:page("+(pageCount-i)+","+pageSize+")' class='myButton'>"+(pageCount-i)+"</a>&nbsp;&nbsp;");
				}
			}else{//当前页不是第1、2页或倒数1、2页这四页中的其中一页，那么久以当前页为中心，依次显示五个页码
				sb.append("<a href='javascript:page("+(pageNo-2)+","+pageSize+")' class='myButton'>"+(pageNo-2)+"</a>&nbsp;&nbsp;");
				sb.append("<a href='javascript:page("+(pageNo-1)+","+pageSize+")' class='myButton'>"+(pageNo-1)+"</a>&nbsp;&nbsp;");
				sb.append("<a href='javascript:page("+pageNo+","+pageSize+")' class='myButton'>"+pageNo+"</a>&nbsp;&nbsp;");
				sb.append("<a href='javascript:page("+(pageNo+1)+","+pageSize+")' class='myButton'>"+(pageNo+1)+"</a>&nbsp;&nbsp;");
				sb.append("<a href='javascript:page("+(pageNo+2)+","+pageSize+")' class='myButton'>"+(pageNo+2)+"</a>&nbsp;&nbsp;");
			}
			sb.append("<a href='javascript:page("+(pageNo+1)+","+pageSize+")' class='myButton'>下一页</a>&nbsp;&nbsp;");
			sb.append("<a href='javascript:page("+pageCount+","+pageSize+")' class='myButton'>尾页</a>&nbsp;&nbsp;");
		}
		
		
		
		sb.append("</div>");
		JspWriter writer = pageContext.getOut();
		try {
			writer.println(new String(sb.toString().getBytes()));
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}
		return EVAL_PAGE;
	}


	public int getPageNo() {
		return pageNo;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getPageCount() {
		return pageCount;
	}


	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}

	

	
}
