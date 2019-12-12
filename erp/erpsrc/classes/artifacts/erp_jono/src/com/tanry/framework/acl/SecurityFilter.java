// **********************************************************************
//
// Copyright (c) 2006
// National University of Defence Technology
// ChangSha, China
//
// All Rights Reserved
//
// Authors: Huang Jie
//
// $Id: SecurityFilter.java,v 1.8 2007/02/07 00:35:32 HuangJie Exp $
//
// **********************************************************************

package com.tanry.framework.acl;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;

import java.lang.System;

public class SecurityFilter implements Filter 
{
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
		throws IOException, ServletException 
	{
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		if(httpRequest != null)
		{
			//System.err.println("======== [Filter] find httpRequest by filter");
			
			String[] roles = (String[])httpRequest.getSession().getAttribute("roles");

			/*
			if(roles == null || roles.length == 0)
			{
				roles = new String[1];
				roles[0] = "normal";
			}
			*/
			
			SecurityContext.setRoles(roles);
			chain.doFilter(request, response);
			SecurityContext.setRoles(null);
		}
		else
		{
			//System.err.println("======== [Filter] can't find httpRequest by filter");
		}
    }

	public void init(FilterConfig filterConfig) 
		throws ServletException 
	{	
	}
	
	public void destroy() 
    {
    }
}

