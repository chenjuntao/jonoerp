package com.tanry.framework.cookie;
import java.io.File;
import java.lang.System;
import java.util.Locale;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

public class CookieAction extends ActionSupport
{
	public static Logger log = Logger.getLogger(CookieAction.class);

	private MacForm macForm;

	public void setMacForm(MacForm f)
	{
		macForm = f;
	}
	
	public MacForm getMacForm()
	{
		return macForm;
	}
	
	public String macCookie()
	{
		ActionContext actionContext = ActionContext.getContext();

		if(macForm != null && macForm.getMac() != "")
		{
			Cookie cookie = new Cookie("mac", macForm.getMac()); 
			//cookie.setMaxAge(31536000);//one year
			cookie.setMaxAge(-1);// valid in session only

			//HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);         

			//HttpServletResponse response = (HttpServletResponse)actionContext.get(ServletActionContext.HTTP_RESPONSE); 
			ServletActionContext.getResponse().addCookie(cookie); 
		}

		return SUCCESS;
	}
}

