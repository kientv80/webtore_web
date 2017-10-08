package com.xyz.hayhay.worker;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.xyz.hayhay.website.collector.GoldCollector;

public class CoreWorker {
	private static final int FIFTEEN_MINUTES= 15*60*1000;
	private static final int THIRTY_MINUTES= 2*FIFTEEN_MINUTES;
	private static final int ONE_HOUR= 2*THIRTY_MINUTES;
	private static final int FOUR_HOURS= 4*ONE_HOUR;
	private static final int EIGHT_HOURS= 8*ONE_HOUR;
	Logger log = Logger.getLogger(CoreWorker.class);
	public static StringBuilder collectResult = new StringBuilder();
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
	public void start() {
		GoldCollector gv = new GoldCollector();
		scheduler.scheduleAtFixedRate(gv, 0, 3600, TimeUnit.SECONDS);
//		Timer t5 = new Timer();
//		t5.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				SocialPostFeedNews.getInstance().run();
//			}
//		}, 0, 1 * 15 * 60 * 1000);
	}
	
}
