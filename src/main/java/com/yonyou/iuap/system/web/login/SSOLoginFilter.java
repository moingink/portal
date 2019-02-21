package com.yonyou.iuap.system.web.login;

/** 
 * @author zzh
 * @version 创建时间：2017年2月28日
 * 类说明 
 */

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.yonyou.iuap.cache.CacheManager;
import com.yonyou.iuap.system.entity.MgrUser;


public class SSOLoginFilter implements Filter {
	private Pattern url_exclude_pattern;

	
	@Autowired
	protected CacheManager cacheManager;

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		url_exclude_pattern=null;

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String path = req.getContextPath();
		String uri = req.getRequestURI();
		String returnurl = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + path + req.getServletPath();
		String parameter=req.getQueryString();
		HttpSession session = req.getSession(true);

		System.out.println("uri======="+uri);
		if(uri.startsWith("/portal/login") || uri.startsWith("/portal/base") ||uri.startsWith("/portal/buttonBuild") || uri.endsWith(".js") || uri.endsWith(".png")|| uri.endsWith(".jpg")
				|| uri.endsWith(".css")|| uri.endsWith(".ico") || uri.endsWith(".woff") || uri.endsWith(".html")){
			arg2.doFilter(request, response);
			return ;
		}
		String token = null;
	        
        Cookie[] cookies = req.getCookies();
        for(Cookie cookie : cookies){
        	if("token".equals(cookie.getName())){
        		token=cookie.getValue();
        	}
        }
        
        if(null==token){
        	token=req.getParameter("token");
        }
        
        if(null!=token){
        	
        	MgrUser u1 = cacheManager.get("SESSION_"+token);
        	
        	if(null!= u1){
        		arg2.doFilter(request, response);
        		
        	}else{
        		res.sendRedirect("/portal/login");
        	}
        }else{
        	arg2.doFilter(request, response);
        	System.out.println("没有传输token："+token+":uri:"+uri);
        	//res.sendRedirect("/portal/login");
        	
        }

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		//this.url_exclude_pattern = Pattern.compile(filterConfig.getInitParameter("url_exclude_pattern"));
		
		 SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,   
				 filterConfig.getServletContext());   
	}
	
	 /**
     * 退出登录
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void logout(HttpServletRequest request, HttpServletResponse response){
        //获得session，有可能不存在
//        HttpSession session = RmJspHelper.getSession(request, false);
//        if(session != null) {
//        	((IRmLoginService) RmBeanFactory.getBean(IRmLoginService.class.getName())).executeDestroyUserInfo(session);
//			//清除session
//			session.invalidate();
//        }
//    	RmJspHelper.clearProfile(request, response, Para.login_id.name());
//    	RmJspHelper.clearProfile(request, response, Para.password.name());       
    }

}
