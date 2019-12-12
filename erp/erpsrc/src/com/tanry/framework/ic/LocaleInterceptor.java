package com.tanry.framework.ic;
import java.util.Map;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;  
import com.opensymphony.xwork2.ActionInvocation;  
import com.opensymphony.xwork2.interceptor.AbstractInterceptor; 
import org.apache.log4j.Logger;

public class LocaleInterceptor extends AbstractInterceptor 
{  
	public static Logger log = Logger.getLogger(LocaleInterceptor.class);
	public String intercept(ActionInvocation invocation) 
		throws Exception 
	{  

		ActionContext actionContext = invocation.getInvocationContext();  
		HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);  

		log.debug("=============ic actionContext.getSession =============");
		Map session = actionContext.getSession();  
		if (session != null)
		{  
			log.debug("============= session =============");
			Object o = session.get("locale");
			if(o != null) 
			{
				log.debug("============= locale =============");
				Locale l = (Locale)o;
				if(l != null)
				{
					log.debug("=============ic setLocale =============");
					actionContext.getContext().setLocale(l);
				}
			}
		}  
		return invocation.invoke();  
	}
}
