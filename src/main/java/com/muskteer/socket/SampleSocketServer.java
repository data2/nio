package com.muskteer.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.muskteer.commons.logs.LoggerFactory;
import com.muskteer.commons.logs.MuskLogger;
/**
 * socket通信  = java.io + java.net
 * @author muskteer
 * @date 20161221
 */
public class SampleSocketServer {
	
	private static MuskLogger logger = 
			LoggerFactory.getLogInstance(SampleSocketServer.class, "SampleSocketServer");

	public static void main(String[] args) {
		runServer();
	}

	private static void runServer() {
		//java.net
		ServerSocket server = null;
		logger.info("starting at date : {}", new Date());
		try {
			int port = 5551;
			server = new ServerSocket(port);
			logger.info("started use port : {}", port);
		} catch (IOException e) {
			logger.info("instance server at date : {}", new Date());
		}
		
		Socket socket = null;
		while(true){
			run(server, socket);
		}
		
	}

	private static void run(ServerSocket server, Socket socket) {
		try {
			socket = server.accept();
			InputStream is = socket.getInputStream();
			BufferedReader bw = new BufferedReader(
					new InputStreamReader(is));
			String line = bw.readLine();
			//apache commons-lang
			while(StringUtils.isNotEmpty(line)){
				logger.info("attache client data : {}", line);
				line = bw.readLine();
			}
			//java.io
			OutputStream os = socket.getOutputStream();
			os.write("server deal success".getBytes());
			logger.info("server deal success : {}", new Date());
			os.flush();
			
			is.close();
			os.close();
			bw.close();
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
