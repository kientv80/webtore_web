package com.xyz.hayhay.logcentral;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.xyz.hayhay.util.ConfigurationHelper;

public class TextFileLogger {
	private static TextFileLogger instance;
	BlockingQueue<String> logingQueue = new ArrayBlockingQueue<>(1000);

	public static TextFileLogger getInstance() {
		if (instance == null)
			instance = new TextFileLogger();
		return instance;
	}

	public void log(String data) {
		try {
			if (data != null)
				logingQueue.put(data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void consumeLog() {
		new Thread() {
			@Override
			public void run() {
				FileOutputStream writer = null;
				try {
					SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd");
					File f = new File(ConfigurationHelper.getInstance().getValue("appDir")
							+ df.format(new java.util.Date(System.currentTimeMillis())) + "_activitylog.log");
					writer = new FileOutputStream(f, true);
					String today = df.format(new java.util.Date(System.currentTimeMillis()));
					while (true) {
						String log = logingQueue.take();
						if (today.equals(df.format(new java.util.Date(System.currentTimeMillis())))) {
							writer.close();
							f = new File(ConfigurationHelper.getInstance().getValue("appDir")+ df.format(new java.util.Date(System.currentTimeMillis())) + "_activitylog.log");
							writer = new FileOutputStream(f, true);
						}
						writer.write(log.getBytes());
						writer.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (writer != null)
						try {
							writer.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
			}
		}.start();
		;

	}

	public TextFileLogger() {
		consumeLog();
	}
}
