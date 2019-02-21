package com.yonyou.util.mail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;




public class MailHelper extends JavaMailSenderImpl{
		
	private static SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static Logger logger = Logger.getLogger(MailHelper.class);
	private SimpleMailMessage mailMessage ;
	private void  initMailConf(String host,String username,String password)
	{	
		setDefaultEncoding("utf-8");
		setHost(host);
		setUsername(username) ; 
		setPassword(password) ; 
		Properties prop = new Properties() ;
		prop.put("mail.smtp.auth", "true") ; // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		setJavaMailProperties(prop) ;
	}
	public void sendMail(String bizType,String serviceName, String errorInfo){
		String mailText = getMailTextByErrorInfo(bizType,serviceName,errorInfo);
		this.mailMessage.setText(mailText);
		try {
			this.send(mailMessage);
			logger.info("邮件发送成功！");
		} catch (MailException e) {
			logger.info("邮件发送失败！\r\n"+e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * 拼装错误信息
	 * @param bizType     --业务类型 ERP、HR、OA等等
	 * @param serviceName --服务名称
	 * @param errorInfo   --错误信息
	 * @return            --返回拼装的错误信息
	 */
	private static String getMailTextByErrorInfo(String bizType,String serviceName, String errorInfo){
		StringBuffer mailText = new StringBuffer();
		mailText.append(bizType + "接口服务"+serviceName+"于 "+bartDateFormat.format(new Date())+" 同步数据产生异常!\r\n");
		mailText.append("异常信息如下：\r\n");
		mailText.append(errorInfo).append("\r\n");
		mailText.append("\r\n请相关人员检查确认!\r\n");
		return mailText.toString();
	}

	public static void main(String[] args){
//		PropertyConfigurator.configure("conf/log4j.properties");
//		ConfiguarHelper.initMailConf();
//		ConfiguarHelper.getHrMailSenderImpl().getMailMessage().setText(new Date()+"bbbb");
//		ConfiguarHelper.getHrMailSenderImpl().sendEmail();
		
		MailHelper mh=new MailHelper();
		mh.init();
		mh.setMailTo("ERP", "14");
		mh.sendMail("ERP", "<总账凭证验证服务>","test" );
		
	}
	

	public SimpleMailMessage getMailMessage() {
		return mailMessage;
	}

	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}

	public void sendEmail() {
		try{
			send(mailMessage);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public SimpleMailMessage init(){
		Map<String,String> mailInfo=MailConf.getInstance().getMailInfo();
		String host=mailInfo.get("host");
		String username=mailInfo.get("username");
		String password=mailInfo.get("password");
		String mainFrom=mailInfo.get("mailFrom");
		String mainSubject=mailInfo.get("mailSubject");
		initMailConf(host,username,password);
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		mailMessage.setFrom(mainFrom);
		mailMessage.setSubject(mainSubject);
		setMailMessage(mailMessage);
		//this.mailMessage=mailMessage;
		return mailMessage;
	}
	public void setMailTo(String bizType,String provinceCode){
		logger.info("method setMailTo start with "+bizType +" provinceCode="+provinceCode+"...");
		String mailTo=MailConf.getInstance().getMailRecive(bizType, provinceCode);

		if(this.mailMessage!=null){
			this.mailMessage.setTo(mailTo);
		}
		logger.info("method setMailTo end with address="+mailTo+"...");
	}

	public boolean sendMailSuper(String bizType,String title, String sendMessage,String to){
		boolean res=false;
		this.mailMessage.setText(sendMessage);
		this.mailMessage.setSubject(title);

		this.mailMessage.setTo(to.split(";"));
		try {
			this.send(mailMessage);
			logger.info("邮件发送成功！");
			res=true;
		} catch (MailException e) {
			logger.info("邮件发送失败！\r\n"+e.getMessage());
			e.printStackTrace();
		}
		return res;
	}
}
