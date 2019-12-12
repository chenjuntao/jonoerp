/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on July 29, 2014 by liyzh
 * Last edited on Aug 6, 2014 by liyzh
 * 
 * 说明： action父类，用来实现返回json数据等公用操作
 */
package action.common;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import pojo.store.BranchStorage;

import com.opensymphony.xwork2.ActionSupport;
import com.tanry.business.module.setting.user.pojo.SysUser;
import com.tanry.framework.util.constant.BranchCode;
import com.tanry.framework.util.constant.BranchType;

public class BaseAction extends ActionSupport implements SessionAware, ServletRequestAware {

	public static final int ALL = 0;

	/**
	 * 未审核
	 */
	public static final int UN_AUDIT = 1;

	/**
	 * 已审核
	 */
	public static final int AUDIT_ED = 2;

	/**
	 * 未出库
	 */
	public static final int UN_OUT = 3;

	private static final long serialVersionUID = 1L;

	// Session Map对象
	protected Map<String, Object> session;
	protected HttpServletRequest request;

	private String myLoginUserId;
	private String loginUserId;

	protected String hasSum;

	public String getHasSum() {
		return hasSum;
	}

	public void setHasSum(String hasSum) {
		this.hasSum = hasSum;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSessionMap() {
		return this.session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getServletRequest() {
		return this.request;
	}

	/**
	 * Convenience method to get the response
	 * 
	 * @return current response
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	protected void outJS(String value) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(value);
		out.flush();
		out.close();
	}

	/**
	 * 配合dojo io iframe的使用 For all values EXCEPT html and xml, The server
	 * response should be an HTML file with a textarea element. The response
	 * data should be inside the textarea element. Using an HTML document is the
	 * only reliable, cross-browser way this transport can know when the
	 * response has loaded. For the text/html (Or XML) mimetype, just return a
	 * normal HTML/XML document. In other words, your services for JSON and Text
	 * formats should return the data wrapped as the following:
	 * 
	 * <html> <body> <textarea> payload </textarea> </body> </html>
	 * 
	 * @param value
	 * @throws IOException
	 */
	protected void outHTML(String value) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html><body><textarea>" + value + "</textarea></body></html>");
		out.flush();
		out.close();
	}

	protected void outXls(String filename, Workbook wb) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		response.setContentType("application/octet-stream; charset=UTF-8");
		response.setHeader("Content-disposition",
				"attachment; filename=" + new String((filename.replaceAll(" ", "")).getBytes("GBK"), "iso8859-1"));

		// test
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Cookie cDate = new Cookie("lastVisited", format.format(new java.util.Date()));
		response.addCookie(cDate);
		// test

		OutputStream outFile = response.getOutputStream();
		wb.write(outFile);
		outFile.flush();
		outFile.close();
	}

	protected void outCsv(String filename, StringBuffer sb) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		response.setContentType("application/octet-stream; charset=UTF-8");// charset貌似不起任何作用
		response.setHeader("Content-disposition",
				"attachment; filename=" + new String((filename.replaceAll(" ", "")).getBytes("GBK"), "iso8859-1"));
		OutputStream outFile = response.getOutputStream();
		outFile.write(sb.toString().getBytes("GBK"));// 如果没有参数，则是系统默认的编码，服务器上可能默认编码是utf-8
		outFile.flush();
		outFile.close();
	}

	protected void outPdf(String filename, StringBuffer sb) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		response.setContentType("application/octet-stream; charset=UTF-8");// charset貌似不起任何作用
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String((filename).getBytes("GBK"), "iso8859-1"));
		OutputStream outFile = response.getOutputStream();
		outFile.write(sb.toString().getBytes("GBK"));// 如果没有参数，则是系统默认的编码，服务器上可能默认编码是utf-8
		outFile.flush();
		outFile.close();
	}

	protected void outBlank(String content) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		response.setContentType("text/plain; charset=UTF-8");// charset貌似不起任何作用
		OutputStream outFile = response.getOutputStream();
		outFile.write(content.getBytes("UTF-8"));// 如果没有参数，则是系统默认的编码，服务器上可能默认编码是utf-8
		outFile.flush();
		outFile.close();
	}

	/**
	 * 分页参数，开始记录序号
	 */
	private int start; //
	/**
	 * 分页参数，获取记录数 dojo默认使用
	 */
	private int count;

	/**
	 * 同count，jquery DataTables默认使用
	 */
	private int length;

	/**
	 * 前端 传递 字符串化的排列规则，后台需解析
	 */
	private String sort;

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void setTotal(int total) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Content-Total", total + "");
	}

	public int getStart() {
		return start + 1;// 从1开始
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCount() {
		return count;
	}

	public int getEnd() {
		if (length != 0) {// 如果请求来自jquery DataTables
			count = length;
		}
		return start + count + 1;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * 手机端标志
	 */
	private boolean fromMobile;

	public boolean getFromMobile() {
		return fromMobile;
	}

	public boolean isMobile() {
		return fromMobile;
	}

	public void setFromMobile(boolean fromMobile) {
		this.fromMobile = fromMobile;
	}

	/**
	 * 获取当前登录用户的编码
	 * 
	 * @return
	 */
	public String getLoginUserId() {
		SysUser sysUser = (SysUser) getSessionMap().get("loginUser");
		if (sysUser != null) {
			return sysUser.getUserId();
		}
		return null;
	}

	public String getMyLoginUserId() {
		return myLoginUserId;
	}

	public void setMyLoginUserId(String myLoginUserId) {
		this.myLoginUserId = myLoginUserId;
	}

	/**
	 * 获取当前登录用户的姓名
	 * 
	 * @return
	 */
	public String getLoginUsername() {
		SysUser sysUser = (SysUser) getSessionMap().get("loginUser");
		if (sysUser != null) {
			return sysUser.getUserName();
		}
		return null;
		// return "[" + getLoginUserId() + "]" + "管理员";
	}

	/**
	 * 获取当前登录用户的姓名
	 * 
	 * @return
	 */
	public String getBranchId() {
		SysUser sysUser = (SysUser) getSessionMap().get("loginUser");
		if (sysUser != null) {
			return sysUser.getUserName();
		}
		return null;
		// return "[" + getLoginUserId() + "]" + "管理员";
	}

	/**
	 * 获取当前登录用户所属的部门编码
	 * 
	 * @return
	 */
	public String getLoginBranchId() {
		SysUser sysUser = (SysUser) getSessionMap().get("loginUser");
		if (sysUser != null) {
			return sysUser.getBranchId();
		}
		return null;
	}

	/**
	 * 获取当前登录用户所属的部门名称
	 * 
	 * @return
	 */
	public String getLoginBranchName() {
		String branchName = (String) getSessionMap().get("branchName");
		if (branchName != null) {
			return branchName;
		}
		return null;
	}

	/**
	 * 获取当前登录用户的门店类型
	 * 
	 * @return
	 */
	public String getLoginBranchType() {
		String branchType = (String) getSessionMap().get("branchType");
		if (branchType != null) {
			return branchType;
		}
		return null;
		// return "[" + getLoginUserId() + "]" + "管理员";
	}

	/**
	 * 获取当前登录用户所属的部门主仓
	 * 
	 * @return
	 */
	public String getMainStorage() {
		String mainStorage = (String) getSessionMap().get("mainStorage");
		return mainStorage;
	}

	public BranchStorage getBranchStorage() {
		BranchStorage branchStorage = (BranchStorage) getSessionMap().get("branchStorage");
		return branchStorage;
	}

	public String getCfCode() {
		return BranchCode.DEFAULT_CENTRALFACTORY;
	}

	public String getLcCode() {
		return BranchCode.DEFAULT_LOGISTICSCENTER;
	}

	/**
	 * 根据门店类型得到门店名
	 * 
	 * @return
	 * */
	public String getCodeName(String branchType) {
		return BranchType.getBrandWord(branchType);
	}

	/**
	 * 每一个TAB页面的唯一标志
	 */
	private String tabId;
	/**
	 * 父TAB页面的唯一标志
	 */
	private String parentTabId;

	public String getTabId() {
		return tabId;
	}

	public void setTabId(String tabId) {
		this.tabId = tabId;
	}

	public String getParentTabId() {
		return parentTabId;
	}

	public void setParentTabId(String parentTabId) {
		this.parentTabId = parentTabId;
	}
}
