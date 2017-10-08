package com.xyz.hayhay.logcentral;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class VisistorStats {
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName("webstorestats").setMaster("local");
		JavaSparkContext context = new JavaSparkContext(conf);
		JavaRDD<String> data = context.textFile("activitylog.log").map(new Function<String, String>() {
			@Override
			public String call(String line) throws Exception {
				return line;
			}
		});
		System.out.println(data.take(10));
	}
}
