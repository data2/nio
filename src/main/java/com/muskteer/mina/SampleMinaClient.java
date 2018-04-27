package com.muskteer.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class SampleMinaClient {

	public static void main(String[] args) {
		IoConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast( "codec", 
				new ProtocolCodecFilter( new TextLineCodecFactory( Charset.forName( "UTF-8" ))));
		connector.setHandler(new SampleMinaClientHandler());
		ConnectFuture connFuture = connector.connect(new InetSocketAddress("192.168.7.27", 2000));
		connFuture.awaitUninterruptibly();
		connFuture.getSession().write("client params");
		connFuture.getSession().getCloseFuture().awaitUninterruptibly();
		connector.dispose(true); 
		
	}

}
