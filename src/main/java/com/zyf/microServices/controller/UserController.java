package com.zyf.microServices.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@RequestMapping("/doGetRealIp.do")
    public String doGetRealIp(HttpServletRequest req, HttpServletResponse resp){
 		System.out.println("开始。。。。。。");    	
		String ip = "";
		try {
			//ip = HttpUtil.sendRequest("http://pv.sohu.com/cityjson?ie=utf-8", "", "");
			ip = getRealIp(req);
			//ip = request.getHeader("X-Forwarded-For"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		//String real = ip;//"{\"realIp\":\""+ ip +"\"}";
		String real = "{\"realIp\":\""+ ip +"\"}";
		System.out.println(real);
		return real;
	}
	
	public static String getRealIp(HttpServletRequest request) {
        try {
        	
        	String ip = "";
        	ip = request.getHeader("X-Forwarded-For");  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("Proxy-Client-IP");  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("WL-Proxy-Client-IP");  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("HTTP_CLIENT_IP");  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getRemoteAddr();  
            }
            System.out.println("realIpAll---" + ip);
            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
            if(ip!=null && ip.length()>15){ //"***.***.***.***".length() = 15  
                if(ip.indexOf(",")>0){  
                	ip = ip.substring(0,ip.indexOf(","));  
                }  
            }  
            return ip;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return "获取ip出错了";
		}
    }

}
