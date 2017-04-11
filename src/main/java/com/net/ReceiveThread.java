package com.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.NetUtil;

/**
 * 接收数据线程
 * @author Administrator
 *
 */
public class ReceiveThread extends Thread{

	@Override
	public void run() {
		InetSocketAddress isa = new InetSocketAddress("192.168.102.12",ContantUtil.port);  
		try {
			ServerSocket server = new ServerSocket();  

			server.bind(isa);  
			System.out.println("isBound: " + server.isBound());  
			System.out.println("SocketAddress: " + server.getLocalSocketAddress());  

			Socket client = null;
			 
			while(true){  
				client = server.accept();  
				System.out.println(DateUtils.formatDateTime(new Date(System.currentTimeMillis()))+":::"+"this main thread:"+client.getInetAddress());  
				
				
				
				InputStream in = client.getInputStream();  
				byte[] bs = new byte[60];
				in.read(bs);
				System.out.println(DateUtils.formatDateTime(new Date(System.currentTimeMillis()))+":::read1");
				NetUtil.print16String(bs);
				Map<byte[],String> map = new HashMap<byte[],String>();
				String sourceIp = client.getInetAddress().getHostName();
				map.put(bs, sourceIp);
				ContantUtil.monitorResponseLinkedQueue.add(map);
				System.out.println(bs[0]+"|||||"+bs[1]);
				if(bs[0] == -21 && bs[1]==96){
					String str = "EB 60 00 00 00 01 00 03 00 01 00 04 00 00 00 00 00 00 00 00 05 0D 0A";
					String[] strs = str.split(" ");
					byte[] b = new byte[strs.length];
					for (int i = 0; i < b.length; i++) {
						b[i] = (byte) Integer.parseInt(strs[i], 16);
					}
					OutputStream outputStream = client.getOutputStream();
					outputStream.write(b);
				}
				in.read(bs);
				System.out.println(DateUtils.formatDateTime(new Date(System.currentTimeMillis()))+":::read2");
				NetUtil.print16String(bs);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
