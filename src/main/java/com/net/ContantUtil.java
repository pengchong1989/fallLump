package com.net;

import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ContantUtil {
	public static ConcurrentLinkedQueue<Map<byte[],String>> monitorResponseLinkedQueue = new ConcurrentLinkedQueue<Map<byte[],String>>();
	public static TcpNetwork tcpNetwork;
	public static ServerSocket serverSocket;
	public static ConcurrentLinkedQueue<Map<byte[],String>> monitorSendLinkedQueue = new ConcurrentLinkedQueue<Map<byte[],String>>();
	public static Integer port = 1234;
	public static ConcurrentHashMap<Integer,Integer> sendStatus = new ConcurrentHashMap<Integer, Integer>();//是否已发送给设备
	public static ConcurrentHashMap<Integer,byte[]> sendDatas = new ConcurrentHashMap<Integer, byte[]>();//需要发送的内容
}
