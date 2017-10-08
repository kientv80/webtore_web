package com.xyz.hayhay.website.collector;

import org.apache.log4j.Logger;

public  abstract class WebsiteCollector implements Runnable{
	Logger l = Logger.getLogger(WebsiteCollector.class);
	Boolean isRunning = false;
	public WebsiteCollector() {
	}
	public abstract void processWebsiteContent() throws Exception;
	public abstract String getCollectorName();
	public void run() {
		try {
			if(!isRunning){
				isRunning = true;
				l.info("Run " + getCollectorName());
				processWebsiteContent();
				l.info("End Running " + getCollectorName());
				isRunning = false;
			}
		} catch (Throwable  e) {
			l.error("============================================", e);
			e.printStackTrace();
		}
	}
}
