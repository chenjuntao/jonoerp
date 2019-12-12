package action.setting;

import java.net.InetAddress;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;

import logic.NoConnection;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.price.PriceAdjustDao;

public class SendEmail extends TimerTask {
	private ServletContext context = null;
	private String contents;

	@Resource(name = "priceAdjustDao")
	PriceAdjustDao priceAdjustDao;

	public SendEmail(ServletContext context) {
		this.context = context;
		// 取得上下文，然后从上下文中获得applicationcontext，之后可以获得想要的bean。
		WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(context);
		this.priceAdjustDao = (PriceAdjustDao) wc.getBean("priceAdjustDao");
	}

	@Override
	public void run() {
		/*
		 * 以下为javamail的邮件发送
		 */

		System.out.println("正在发送邮件");

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.163.com");// 发件人使用发邮件的电子信箱服务器我使用的是163的服务器
		props.put("mail.smtp.auth", "true"); // 这样才能通过验证
		// props.setProperty("mail.smtp.auth", "true");
		Session s = Session.getInstance(props);
		s.setDebug(true);
		String contents = "111";
		try {
			contents = priceAdjustDao.computeStorage();// 邮件内容查询
		} catch (NoPrivilegeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoConnection e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		MimeMessage message = new MimeMessage(s);

		try {
			InternetAddress from = new InternetAddress("tanry09412@163.com"); // 密码：xiaozehappy
																				// 发邮件的出发地（发件人的信箱），这是我的邮箱地址，使用请改成你的有效地址
			message.setFrom(from);
			InternetAddress to = new InternetAddress("tanry09412@163.com");// tto为发邮件的目的地（收件人信箱）

			message.setRecipient(Message.RecipientType.TO, to);
			String hostname = InetAddress.getLocalHost().getHostName();
			message.setSubject(hostname + "进出库数据验证");// ttitle为邮件的标题
			message.setSentDate(new Date());
			BodyPart mdp = new MimeBodyPart();// 新建一个存放信件内容的BodyPart对象
			mdp.setContent(contents, "text/html;charset=utf-8");// 给BodyPart对象设置内容和格式/编码方式tcontent为邮件内容
			Multipart mm = new MimeMultipart();// 新建一个MimeMultipart对象用来存放BodyPart对
			// 象(事实上可以存放多个)
			mm.addBodyPart(mdp);// 将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
			message.setContent(mm);// 把mm作为消息对象的内容

			message.saveChanges();
			Transport transport = s.getTransport("smtp");
			transport.connect("smtp.163.com", "tanry09412", "czc1018");// 发邮件人帐户密码,此外是我的帐户密码，使用时请修改。
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}
		// 给消息对象设置发件人/收件人/主题/发信时间

	}

	public void setPriceAdjustDao(PriceAdjustDao priceAdjustDao) {
		this.priceAdjustDao = priceAdjustDao;
	}

}
