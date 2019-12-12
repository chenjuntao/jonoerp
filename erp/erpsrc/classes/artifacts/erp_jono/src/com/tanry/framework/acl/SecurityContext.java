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
// $Id: SecurityContext.java,v 1.6 2006/09/19 01:26:37 HuangJie Exp $
//
// **********************************************************************

package com.tanry.framework.acl;

import java.lang.String;
import java.lang.ThreadLocal;

public class SecurityContext
{
	private static ThreadLocal threadLocal_ = new ThreadLocal();
	
	public static void setRoles(String[] roles)
	{
		//System.err.println("======== [SecurityContext] set security context");
		
		threadLocal_.set(roles);
	}
	
	public static String[] getRoles()
	{
		if(threadLocal_.get() != null)
		{
			//System.err.println("======== [SecurityContext] get security context");
			return (String[])(threadLocal_.get());
		}
		else
		{
			//System.err.println("======== [SecurityContext] get security context, there are no roles");
			return new String[0];
		}
	}
}
