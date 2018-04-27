package com.muskteer.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

import org.apache.commons.lang.StringUtils;

import com.muskteer.netty.handler.ConfDecoder;
import com.muskteer.netty.handler.ConfEncoder;

public class SampleNettyClient {

	public static void main(String[] args) {
		EventLoopGroup event = new NioEventLoopGroup();
		try {
			Bootstrap boot = new Bootstrap();
			boot.group(event)
				.channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline()
							.addLast(new ConfEncoder(String.class))
							.addLast(new ConfDecoder(String.class))
							.addLast(new ChildHandler());
					}
				}).option(ChannelOption.SO_KEEPALIVE, true);
			
			ChannelFuture f = boot.connect("127.0.0.1", 5551).sync();

			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			String in = sc.nextLine();
			while(!StringUtils.equals("bye", in)){
				System.out.println(in);
				f.channel().writeAndFlush(in).sync();
				in = sc.nextLine();
			}
		} catch (Exception e) {
		}
	}

}
