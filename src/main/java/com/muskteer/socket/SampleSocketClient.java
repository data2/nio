package com.muskteer.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.muskteer.commons.logs.LoggerFactory;
import com.muskteer.commons.logs.MuskLogger;

public class SampleSocketClient {

	private static MuskLogger logger = 
			LoggerFactory.getLogInstance(SampleSocketClient.class, "SampleSocketClient");
	
	public static void main(String[] args) {
		reqServer();
	}

	private static void reqServer() {
		Socket socket = null;
		logger.info("starting at date : {}", new Date());
		try {
			socket = new Socket("127.0.0.1", 5551);
			logger.info("connected to server success: {}", new Date());
		} catch (UnknownHostException e) {
			e.printStackTrace();
			logger.info("connect fail, UnknownHostException : {}", new Date());
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("connect fail, IOException : {}", new Date());
		}
		
		OutputStream os = null;
		try {
			os = socket.getOutputStream();
			os.write("hi server".getBytes());
			os.write("".getBytes());
			os.flush();
			logger.info("send data at {}", new Date());
		} catch (IOException e) {
			e.printStackTrace();
		} 
		try {
			socket.shutdownOutput();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		BufferedReader bw = null;
		InputStream is = null;
		try {
			is = socket.getInputStream();
			bw = new BufferedReader(
					new InputStreamReader(is));
			String line = bw.readLine();
			while(StringUtils.isNotEmpty(line)){
				logger.info(" attache data from server : {}", line);
				line = bw.readLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		try {
			os.close();
			is.close();
			bw.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
