package com.tanry.framework.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tanry.framework.util.SysUtil;

public class FrameworkConfigListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
		String webappRoot = event.getServletContext().getRealPath("/");
		SysUtil.setWebappRoot(webappRoot);
	}

	public void contextDestroyed(ServletContextEvent event) {
		// To change body of implemented methods use File | Settings | File
		// Templates.
	}

}
