package com.net;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("192.168.100.35", ContantUtil.port);  
			//		      BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getOutputStream())); 
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));  
			writer.write("message2");  
			writer.flush();  
			socket.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
