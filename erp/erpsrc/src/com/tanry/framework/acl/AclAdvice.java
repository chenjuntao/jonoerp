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
// $Id: AclAdvice.java,v 1.17 2006/09/27 04:00:57 HuangJie Exp $
//
// **********************************************************************

package com.tanry.framework.acl;

import java.lang.String;
import java.lang.System;
import java.lang.reflect.Method;
//import org.springframework.aop.MethodBeforeAdvice;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.apache.xerces.parsers.DOMParser;

public class AclAdvice // implements MethodBeforeAdvice
{	
	public static Document document_ = SecurityContextListener.document_;

	public void before(Method method, Object[] args, Object target)
		throws Throwable
	{	
		String className = target.getClass().getName();
		String methodName = method.getName();
		
		//System.err.println("======== [RDSS] check permission");
		
		if(className.startsWith("acl"))
			return;
			
		if(className.startsWith("org.apache"))
			return;
		
		if(className.startsWith("logic.setting.LoginBean"))
			return;
		
		String fullName = className + "." + methodName;
		
		//System.err.println("======== [RDSS] operation = " + fullName);
		
		checkRoles(fullName);
	}
	
	public void checkRoles(String operation)
		throws Throwable
	{
		String[] roles = SecurityContext.getRoles();
		
		if(roles == null || roles.length == 0)
		{
			//System.err.println("======== [RDSS] no role in security context");
			throw new NoPrivilegeException();
		}
		
		for(int i = 0;i < roles.length; i++)
		{
			//System.err.println("======== [RDSS] role = " + roles[i]);
			
			if(roles[i].equals("god"))
				return;
				
			if(checkRole(roles[i], operation))
				return;
		}
		throw new NoPrivilegeException();
	}
	
	public boolean checkRole(String role, String operation)
		throws Throwable
	{
		if(document_ == null)
		{
			//System.err.println("======== [RDSS] document_ = null, can't find security declaration");
			throw new NoPrivilegeException();
		}
		
		//System.err.println("======== [RDSS] check exclude");
		
		if(internalCheckRole(role, operation, false, 1))
		{
			return false;
		}
		
		//System.err.println("======== [RDSS] check include");
		
		return internalCheckRole(role, operation, true, 1);
	}
	
	public boolean internalCheckRole(String role, String operation, boolean include, int level)
		throws Throwable
	{
		NodeList rolelist = document_.getElementsByTagName(role);
		
		if(rolelist.getLength() == 0)
		{
			return false;
		}
		else
		{
			NodeList privelege = rolelist.item(0).getChildNodes();
			for(int i = 0; i < privelege.getLength(); i++)
			{
				if(privelege.item(i).getNodeName().equals("privilege"))
				{
					//System.err.println("======== [RDSS] privelege = " + privelege.item(i).getTextContent());
					if(!(level == 1 && !include))
					{
						if(operation.startsWith(privelege.item(i).getTextContent()))
						{
							return true;
						}	
					}
				}
				else
				{
					if(include)
					{
						if(privelege.item(i).getNodeName().equals("include"))
						{
							//System.err.println("======== [RDSS] include = " + privelege.item(i).getTextContent());
							
							if(internalCheckRole(privelege.item(i).getTextContent(), operation, include, level + 1))
								return true;
						}
					}
					else
					{
						if(privelege.item(i).getNodeName().equals("exclude"))
						{
							//System.err.println("======== [RDSS] exclude = " + privelege.item(i).getTextContent());
							
							if(internalCheckRole(privelege.item(i).getTextContent(), operation, include, level + 1))
								return true;
						}
					}
				}
			}
			return false;
		}
	}
}
