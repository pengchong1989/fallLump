package com.thinkgem.jeesite.common.constants;

public interface ZTConstants {
	/**
	 * 数据发送状态
	 * @author Administrator
	 *
	 */
	public interface SendStatus {
	    String no = "0"; // 未发送
		String ye = "1"; // 已发送
	}
	
	public interface channel{
		String reciveData = "reciveData";//接受节点的数据
		String dataLog = "dataLog";//接受节点成功标示
	}
	
	public interface resultCode{
		String success = "1";//成功接受
		String noNodeId = "2";//收到配置前，无节点id信息
		String nodeIdError = "3";//节点id与收到数据的节点id不一致
	}
	
	public interface jedisKey{
		String node_id = "node_id";//节点id
		String send_status = "send_status";//需要发送的数据的状态
		String send_data_byte = "send_data_byte";//需要发送的数据
		String send_data_string = "send_data_string";//需要发送的数据
	}
}
