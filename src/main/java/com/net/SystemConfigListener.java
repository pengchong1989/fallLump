package com.net;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SystemConfigListener implements ServletContextListener {
	

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {
		try {
//			new NettyServer().bind();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ReceiveThread threadNetWork = new ReceiveThread();
		Thread t = new Thread(threadNetWork);
		t.start();
	}
}
