/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.net;

import org.springframework.data.redis.core.RedisTemplate;

import com.thinkgem.jeesite.common.constants.ZTConstants;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.utils.NetUtil;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author peng
 */
public class ServerHandler extends ChannelHandlerAdapter {
	
	private String nodeId;//节点id
	
	private byte[] bs = {(byte) 0xEB,0x60,0x00,0x00,0x00, 0x01, 0x00, 0x03, 0x00, 0x01, 0x00 
			,0x04 ,0x00, 0x00, 0x00, 0x00 ,0x00, 0x00, 0x00 ,0x00 ,0x05, 0x0D, 0x0A};
	
	public ServerHandler(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
		
	}
	
	private RedisTemplate<String, Object> redisTemplate;
	
	
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    	System.out.println("nodedataService");
    	ByteBuf buf = (ByteBuf) msg;
    	byte[] req = new byte[buf.readableBytes()];
    	buf.readBytes(req);
    	System.out.println(DateUtils.getDateTime()+"++"+req[0]+"+"+req[1]+(req[0] == -21 && req[1]==-112));
    	NetUtil.print16String(req);
    	if(req[0] == -21 && req[1]==96){//"EB 60" 节点第一次请求软件
    		//2、 根据节点ID判断是否存在该节点，不存在入库
    		byte[] segment = new byte[4];
    		segment[0] = 0;
    		segment[1] = 0;
    		segment[2] = req[2];
    		segment[3] = req[3];
    		byte[] id = new byte[4];
    		id[0] = 0;
    		id[1] = 0;
    		id[2] = req[4];
    		id[3] = req[5];
    		String nodeid = NetUtil.bytesToInt(segment)+"_"+NetUtil.bytesToInt(id);
    		this.setNodeId(nodeid);
//    		bs[8] = req[2];
//    		bs[9] = req[3];
//    		bs[10] = req[4];
//    		bs[11] = req[5];
//    		bs[20] = (byte) (NetUtil.bytesToInt(req[2])+NetUtil.bytesToInt(req[3])+NetUtil.bytesToInt(req[4])+NetUtil.bytesToInt(req[5]));
//			ByteBuf resp = Unpooled.copiedBuffer(bs);
    		byte[] bb = (byte[]) JedisUtils.getObject(ZTConstants.jedisKey.send_data_byte+nodeid);
			ByteBuf resp = Unpooled.copiedBuffer(bb);
    		ctx.write(resp);
    	}else if(req[0] == -21 && req[1]==-112){//"EB 90" 节点上报状态信息
    		
    		byte[] segment = new byte[4];
    		segment[3] = 0;
    		segment[2] = 0;
    		segment[1] = req[2];
    		segment[0] = req[3];
    		byte[] id = new byte[4];
    		id[3] = 0;
    		id[2] = 0;
    		id[1] = req[40];
    		id[0] = req[41];
    		String data_nodeid = NetUtil.bytesToInt(segment)+"_"+NetUtil.bytesToInt(id);
    		/*if(nodeId == null){
    			redisTemplate.convertAndSend(ZTConstants.channel.dataLog, ZTConstants.resultCode.noNodeId+":"+nodeId);
    		}
    		if(!data_nodeid.equals(nodeId)){
    			redisTemplate.convertAndSend(ZTConstants.channel.dataLog, ZTConstants.resultCode.nodeIdError+":"+nodeId);
    		}*/
    		System.out.println("收到");
    		redisTemplate.convertAndSend(ZTConstants.channel.reciveData, req);
    		
        	
    	}else if(req[0]==81){//4、收到节点的配置信息返回值，51表示成功，将成功记录入库
    		redisTemplate.convertAndSend(ZTConstants.channel.dataLog, ZTConstants.resultCode.success+":"+nodeId);
    	}
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    	ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	ctx.close();
    }

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
    
    
}
