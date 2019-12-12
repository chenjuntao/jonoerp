package com.tanry.business.common.quartz;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;


public class JobLauncher {
	private JobBean jobBean;

	@SuppressWarnings("rawtypes")
	public void init() throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();

		File file = new File(JobLauncher.class.getResource("").getPath() + "/job.xml");
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new FileInputStream(file));
		List nodeLst = document.selectNodes("/root/job");
		for (Object obj : nodeLst) {
			Element element = (Element) obj;
			String desc = element.element("description").getTextTrim();
			String procName = element.element("procName").getTextTrim();
			String time = element.element("time").element("value").getTextTrim();

			MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
			jobDetail.setTargetObject(jobBean);
			jobDetail.setTargetMethod("callProc");
			jobDetail.setArguments(new Object[] { desc, procName });
			jobDetail.setName(procName + "_JD");
			jobDetail.afterPropertiesSet();
			CronTriggerBean trigger = new CronTriggerBean();
			trigger.setName(procName + "_CT");
			trigger.setJobDetail(jobDetail.getObject());
			trigger.setJobName(procName + "_JD");
			trigger.setCronExpression(time);
			sched.addJob(jobDetail.getObject(), true);
			sched.scheduleJob(trigger);
		}
		sched.start();
	}

	public void setJobBean(JobBean jobBean) {
		this.jobBean = jobBean;
	}

}
