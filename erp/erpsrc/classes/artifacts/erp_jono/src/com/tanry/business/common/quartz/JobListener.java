package com.tanry.business.common.quartz;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class JobListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		SchedulerFactory sf = new StdSchedulerFactory();
		try {
			Scheduler scheduler = sf.getScheduler();
			scheduler.shutdown(true);
			// Sleep for a bit so that we don't get any errors
			Thread.sleep(1000);
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
	}
}
