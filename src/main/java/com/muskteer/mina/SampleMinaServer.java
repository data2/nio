package com.muskteer.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Date;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.muskteer.commons.logs.LoggerFactory;
import com.muskteer.commons.logs.MuskLogger;

public class SampleMinaServer {
	private static MuskLogger logger = 
			LoggerFactory.getLogInstance(SampleMinaServer.class, "SampleMinaServer");


	public static void main(String[] args) {
		IoAcceptor acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("codec", 
				new ProtocolCodecFilter(new TextLineCodecFactory(
						Charset.forName("GBK"))));
		acceptor.setHandler(new SampleMinaHandler());
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		try {
			acceptor.bind(new InetSocketAddress(5551));
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("mina server start success at {}",new Date());
	}

}
