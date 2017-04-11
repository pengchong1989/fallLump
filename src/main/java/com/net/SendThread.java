package com.net;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;

/**
 * 发送数据线程
 * @author Administrator
 *
 */
public class SendThread extends Thread{
	
	@Override
	public void run() {
		try {
			while(true){
				Iterator<Map<byte[],String>> is = ContantUtil.monitorSendLinkedQueue.iterator();
				while(is.hasNext()){
					Map<byte[],String> map = is.next();
					Iterator<byte[]> i = map.keySet().iterator();
					while(i.hasNext()){
						String targetIp = map.get(i.next());
						Socket socket = new Socket(targetIp, ContantUtil.port);
						OutputStream outputStream = socket.getOutputStream();
						outputStream.write(i.next());
						outputStream.close();
						socket.close();
					}
				}
				Thread.sleep(50);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}

}
