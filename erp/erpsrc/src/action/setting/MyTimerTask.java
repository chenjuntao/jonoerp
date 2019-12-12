package action.setting;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tanry.framework.util.DateTimeUtil;

public class MyTimerTask implements ServletContextListener {
	private Timer timer = null;

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		event.getServletContext().log("定时器销毁");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 3); // 控制时
		calendar.set(Calendar.MINUTE, 0); // 控制分
		calendar.set(Calendar.SECOND, 0); // 控制秒

		Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的3：00：00

		// 如果第一次执行定时任务的时间 小于当前的时间
		// 此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
		if (time.before(new Date())) {
			time = DateTimeUtil.addDays(time, 1);
		}
		Timer timer = new Timer();

		// 在这里初始化监听器，在tomcat启动的时候监听器启动，可以在这里实现定时器功能
		timer = new Timer(true);
		event.getServletContext().log("定时器已启动");// 添加日志，可在tomcat日志中查看到
		// 调用exportHistoryBean，0表示任务无延迟，5*1000表示每隔5秒执行任务，60*60*1000表示一个小时；
		timer.schedule(new SendEmail(event.getServletContext()), time, 24 * 60 * 60 * 1000);
	}

}
