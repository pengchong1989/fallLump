package com.net;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.RedisTemplate;

import com.thinkgem.jeesite.modules.nodedata.service.NodedataService;

public class InitNettyService implements ApplicationListener<ContextRefreshedEvent>{
	@Resource
	RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){//root application context 没有parent，他就是老大.  
	         //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。  
			try {
				new NettyServer(redisTemplate).bind();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }  
		
	}

}
