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

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

//@Aspect 
public class SecAclAspect {
	public static Logger log = Logger.getLogger(SecAclAspect.class);

	public static Document document_ = SecurityContextListener.document_;

	// @Pointcut("within(logic..)")
	public void inServiceLayer() {
	}

	// @Before("acl.SecAclAspect.inServiceLayer()")
	public void beforeCheck(JoinPoint jp) throws Throwable {
		log.debug("======== [SecAclAspect] check permission");
		
		String className = jp.getTarget().getClass().getName();
		log.debug("class:" + className);
		String methodName = jp.getSignature().getName();
		log.debug("method:" + jp.getSignature().getName());
	
		
		// if (!className.startsWith("logic"))
		// return;

		String fullName = className + "." + jp.getSignature().getName();

		 log.debug("======== [SecAclAspect] operation = " + fullName);

		// checkRoles(fullName);
	}

	// @After("acl.SecAclAspect.inServiceLayer()")
	public void afterCheck(JoinPoint jp) throws Throwable {
		log.debug("======== [SecAclAspect] check permission");

		String className = jp.getTarget().getClass().getName();
		log.debug("class:" + className);

		String methodName = jp.getSignature().getName();
		log.debug("method:" + jp.getSignature().getName());
		// if (!className.startsWith("logic"))
		// return;
	}

	public void checkRoles(String operation) throws Throwable {
		String[] roles = SecurityContext.getRoles();

		if (roles == null || roles.length == 0) {
			// log.debug("======== [SecAclAspect] no role in security context");
			throw new NoPrivilegeException();
		}

		for (int i = 0; i < roles.length; i++) {
			// log.debug("======== [SecAclAspect] role = " + roles[i]);

			if (roles[i].equals("god"))
				return;

			if (checkRole(roles[i], operation))
				return;
		}
		throw new NoPrivilegeException();
	}

	public boolean checkRole(String role, String operation) throws Throwable {
		if (document_ == null) {
			// log.debug("======== [SecAclAspect] document_ = null, can't find security declaration");
			throw new NoPrivilegeException();
		}

		// log.debug("======== [SecAclAspect] check exclude");
		NodeList rolelist = document_.getElementsByTagName(role);
		if (rolelist.getLength() == 0) {
			return false;
		}

		if (internalCheckRole(role, operation)) {
			return true;
		}

		return internalCheckRoleInclude(role, operation);
	}

	public boolean internalCheckRole(String role, String operation) throws Throwable {
		NodeList rolelist = document_.getElementsByTagName(role);

		NodeList privelege = rolelist.item(0).getChildNodes();
		for (int i = 0; i < privelege.getLength(); i++) {
			if (privelege.item(i).getNodeName().equals("privilege")) {
				if (operation.startsWith(privelege.item(i).getTextContent())) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean internalCheckRoleInclude(String role, String operation) throws Throwable {
		NodeList rolelist = document_.getElementsByTagName(role);

		NodeList privelege = rolelist.item(0).getChildNodes();
		for (int i = 0; i < privelege.getLength(); i++) {
			if (privelege.item(i).getNodeName().equals("include")) {
				// log.debug("======== [SecAclAspect] include = " +
				// privelege.item(i).getTextContent());
				if (internalCheckRole(privelege.item(i).getTextContent(), operation))
					return true;
				else if (internalCheckRoleInclude(privelege.item(i).getTextContent(), operation))
					return true;
			}
		}
		return false;
	}
}
