/*
 * Copyright 2013-2018 Lilinfeng.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;

import com.thinkgem.jeesite.modules.nodedata.service.NodedataService;


/**
 * @author peng
 */
public class NettyServer {

	RedisTemplate<String, Object> redisTemplate;
	
	public NettyServer(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
    public void bind() throws Exception {
	// 配置服务端的NIO线程组
	EventLoopGroup bossGroup = new NioEventLoopGroup();
	EventLoopGroup workerGroup = new NioEventLoopGroup();
	ServerBootstrap b = new ServerBootstrap();
	b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
		.option(ChannelOption.SO_BACKLOG, 100)
		.handler(new LoggingHandler(LogLevel.INFO))
		.childHandler(new ChannelInitializer<SocketChannel>() {
		    @Override
		    public void initChannel(SocketChannel ch)
			    throws IOException {
			ch.pipeline().addLast("readTimeoutHandler",
				new ReadTimeoutHandler(50));
			ch.pipeline().addLast(new ServerHandler(redisTemplate));
		    }
		});

	// 绑定端口，同步等待成功
	b.bind(ContantUtil.port).sync();
	System.out.println("Netty server start ok : "
		+ ("127.0.0.1" + " : " + ContantUtil.port));
    }

}
