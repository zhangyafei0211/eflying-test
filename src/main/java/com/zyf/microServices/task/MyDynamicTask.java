package com.zyf.microServices.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.zyf.microServices.exception.CmsException;
import com.zyf.microServices.manager.RedisManager;
/**
 * 
    * @ClassName: MyDynamicTask  
    * @Description: 动态定时任务 
    * @author Eflying  
    * @date 2018年7月27日  
    *
 */
@Component
public class MyDynamicTask implements SchedulingConfigurer{

	private static Logger log = LoggerFactory.getLogger(MyDynamicTask.class);
	@Autowired
    RedisManager redisManager;
	private static String cron;
	
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.addTriggerTask(doTask(), getTrigger());
	}

	private Trigger getTrigger() {
		return new Trigger() {
			public Date nextExecutionTime(TriggerContext triggerContext) {
				//触发器
				CronTrigger trigger = new CronTrigger(getCron());
				
				return trigger.nextExecutionTime(triggerContext);
			}
		};
	}

	protected String getCron() {
		String newCron = redisManager.getStr("cms:MyDynamicTask");
        if (StringUtils.isEmpty(newCron)) {
            throw new CmsException("The config cron expression is empty");
        }
        if (!newCron.equals(cron)) {
            log.info(new StringBuffer("Cron has been changed to:'").append(newCron).append("'. Old cron was:'").append(cron).append("'").toString());
            cron = newCron;
        }
        return cron;
	}

	//任务
	private Runnable doTask() {
		return new Runnable() {
			public void run() {
				System.out.println("start");
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("执行了MyDynamicTask,时间为:" + new Date(System.currentTimeMillis()));
			}
		};
	}

}
