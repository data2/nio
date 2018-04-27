package com.muskteer.commons.logs;

import org.slf4j.Logger;

public class MuskLogger {
	private Logger logger;
	private String preTag;
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public String getPreTag() {
		return preTag;
	}
	public void setPreTag(String preTag) {
		this.preTag = preTag;
	}
	public MuskLogger(Logger logger, String preTag) {
		super();
		this.logger = logger;
		this.preTag = preTag;
	}
	public void info(String str){
		logger.info("【"+preTag+"】 "+ str);
	}
	public void info(String format , Object obj){
		logger.info("【"+preTag+"】 "+ format , obj);
	}
	
}
