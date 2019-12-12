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
// $Id: SecurityContextListener.java,v 1.11 2006/10/07 18:53:06 HuangJie Exp $
//
// **********************************************************************

package com.tanry.framework.acl;

import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.apache.xerces.parsers.DOMParser;

@SuppressWarnings({"rawtypes","unchecked"})
public class SecurityContextListener implements ServletContextListener 
{
	public static class Role
	{
		private String role_ = "";
		private String desc_ = "";
		
		public String getRole()
		{
			return role_;
		}
		
		public void setRole(String role)
		{
			role_ = role;
		}
		
		public String getDesc()
		{
			return desc_;
		}
		
		public void setDesc(String desc)
		{
			desc_ = desc;
		}
	}
	
	public static Document document_ = null;
	
	public void contextInitialized(ServletContextEvent event) 
	{
		ServletContext servletContext = event.getServletContext();
		
		String securityConfigPath = servletContext.getRealPath("/WEB-INF/jono-security.xml");
		
		try 
		{
			DOMParser parser = new org.apache.xerces.parsers.DOMParser();
			parser.parse(securityConfigPath);
			//System.err.println("======== [SecurityContextListener] parse /WEB-INF/jono-security.xml ok");
			document_ = parser.getDocument();
		}
		catch(Exception e)
		{
			System.err.println("======== [SecurityContextListener] parse /WEB-INF/jono-security.xml failed");
		}
	}

	public void contextDestroyed(ServletContextEvent event) 
	{
	}
	
	
	public static Vector getAllRoles()
	{
		NodeList rolelistroot = document_.getElementsByTagName("role");
		
		if(rolelistroot.getLength() == 0)
		{
			return new Vector();
		}
		
		NodeList rolelist = rolelistroot.item(0).getChildNodes();
		
		Vector roles = new Vector();
		
		for(int i = 0; i < rolelist.getLength(); i ++)
		{
			//System.out.println("nodename = " + rolelist.item(i).getNodeName());
			//System.out.println("content = " + rolelist.item(i).getTextContent());
			if(!rolelist.item(i).getNodeName().equals("god"))
			{
				Role role = new Role();
				role.setRole(rolelist.item(i).getNodeName());
				NamedNodeMap attrMap = rolelist.item(i).getAttributes();
				if(attrMap != null)
				{
					Node attrNode = attrMap.getNamedItem("description");
					if(attrNode != null)
					{
						role.setDesc(attrNode.getTextContent());
						roles.add(role);
					}
					else
					{
						System.out.println("Can't find description for " + role.getRole());
					}
					
				}
				else
				{
					//System.out.println("error: getAttributes nodename = " + role.getRole());
				}
			}
		}
		
		return roles;
	}
	
	public static String roleToDesc(String role)
	{
		Vector roles = getAllRoles();
		for(int i = 0; i < roles.size(); i++)
		{
			if(((Role)roles.elementAt(i)).getRole().equals(role))
			{
				return ((Role)roles.elementAt(i)).getDesc();
			}
		}
		return null;
	}
	
	public static String descToRole(String desc)
	{
		Vector roles = getAllRoles();
		for(int i = 0; i < roles.size(); i++)
		{
			if(((Role)roles.elementAt(i)).getDesc().equals(desc))
			{
				return ((Role)roles.elementAt(i)).getRole();
			}
		}
		return null;
	}
}
