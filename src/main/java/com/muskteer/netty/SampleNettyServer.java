package com.muskteer.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import com.muskteer.netty.handler.ConfDecoder;
import com.muskteer.netty.handler.ConfEncoder;

public class SampleNettyServer {

	public static void main(String[] args) {
		runNettyServer();
	}

	private static void runNettyServer() {
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup work = new NioEventLoopGroup();
		try {
			ServerBootstrap boot = new ServerBootstrap();
			boot.group(boss, work)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel arg0)
							throws Exception {
						arg0.pipeline()
							.addLast(new ConfDecoder(String.class))
							.addLast(new ConfEncoder(String.class))
							.addLast(new ServerHandler());
					}
				})
				.option(ChannelOption.SO_BACKLOG, 128)
				.childOption(ChannelOption.SO_KEEPALIVE, true);
			
			ChannelFuture f = boot.bind(5551).sync();
			f.channel().closeFuture().sync();
			
		} catch (Exception e) {
		}
		finally{
			work.shutdownGracefully();
			boss.shutdownGracefully();
		}
	}

}
