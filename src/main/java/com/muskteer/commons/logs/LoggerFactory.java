package com.muskteer.commons.logs;

public class LoggerFactory {
	
	public static MuskLogger getLogInstance(Class<?> clas, String preTag){
		//使用org.slf4j
		//而不是java.util.logging, Apache的log4j
		return new MuskLogger(org.slf4j.LoggerFactory.getLogger(clas),preTag);
	}
}
