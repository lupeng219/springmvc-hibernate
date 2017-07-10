package com.crdloo.loanloop.utils;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppCallLogUtil {
    
	
	private static Logger logger = LoggerFactory.getLogger(AppCallLogUtil.class);
	
	public static void printMsg(HttpServletRequest request,String method ,String ...msg){
		try {
			if(msg.length>0){
				StringBuilder sb = new StringBuilder ( "" );
				 for(String s :msg){
					 sb.append(s);
					 sb.append("=");
					 sb.append(request.getParameter(s));
					 sb.append(",");
				 }
				 String param =sb.toString();
				 param= param.substring(0,param.length()-1);
				 StringBuilder printmsg = new StringBuilder ( "" );  
				 printmsg.append("Call--");
				 printmsg.append(method);
				 printmsg.append("--param[");
				 printmsg.append(param);
				 printmsg.append("]--");
				 printmsg.append("source[");
				 printmsg.append("COFFER51-Version=");
				 printmsg.append(request.getHeader("COFFER51-Version"));
				 printmsg.append(",COFFER51-OSVersion=");
				 printmsg.append(request.getHeader("COFFER51-OSVersion"));
				 printmsg.append(",COFFER51-IMEI=");
				 printmsg.append(request.getHeader("COFFER51-IMEI"));
				 printmsg.append(",COFFER51-OS=");
				 printmsg.append(request.getHeader("COFFER51-OS"));
				 printmsg.append(",COFFER51-Channel=");
				 printmsg.append(request.getHeader("COFFER51-Channel"));
				 printmsg.append("]");
				 logger.info(printmsg.toString());
			}else{
				 StringBuilder printmsg = new StringBuilder ( "" );  
				 printmsg.append("Call--");
				 printmsg.append(method);
				 printmsg.append("---source[");
				 printmsg.append("COFFER51-Version=");
				 printmsg.append(request.getHeader("COFFER51-Version"));
				 printmsg.append(",COFFER51-OSVersion=");
				 printmsg.append(request.getHeader("COFFER51-OSVersion"));
				 printmsg.append(",COFFER51-IMEI=");
				 printmsg.append(request.getHeader("COFFER51-IMEI"));
				 printmsg.append(",COFFER51-OS=");
				 printmsg.append(request.getHeader("COFFER51-OS"));
				 printmsg.append(",COFFER51-Channel=");
				 printmsg.append(request.getHeader("COFFER51-Channel"));
				 printmsg.append("]");
				 logger.info(printmsg.toString());
			}
		} catch (Exception e) {
			 logger.error(method+e);
		}
		
	}
	
}
