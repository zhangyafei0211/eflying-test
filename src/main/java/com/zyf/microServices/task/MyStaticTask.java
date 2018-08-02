package com.zyf.microServices.task;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * 
    * @ClassName: MyStaticTask  
    * @Description: 静态的定时任务 
    * @author Eflying  
    * @date 2018年7月27日  
    *
 */
@Component
public class MyStaticTask {
	
	/**
	 * 
	    * @Title: doTask  
	    * @Description: TODO(这里用一句话描述这个方法的作用)  
	    * @param     参数  
	    * @return void    返回类型  
	    * @throws
	 */
	@Scheduled(cron = "5 * * * * *")
	public void doTask() {
		System.out.println("执行了MyStaticTask,时间为:"+new Date(System.currentTimeMillis()));
	}
	
}
