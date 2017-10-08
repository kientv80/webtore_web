#!/bin/bash

echo "Start running `date`" >> /kientv/hayhay/monitorlog.txt

if [ $running == "true" ]; then
	echo "Other cron is running" >> /kientv/hayhay/monitorlog.txt
else
	running="true"
	export $running

	echo "Checking webserver ....." >> /kientv/hayhay/monitorlog.txt
	result=`curl http://360hay.com/googleb15db46baf3c868f.html | grep googleb15db46baf3c868f.html`
	ok="google-site-verification: googleb15db46baf3c868f.html"
	if [ "$result" == "$ok" ]; then
		echo "Webserver is ok" >> /kientv/hayhay/monitorlog.txt
	else
		echo "Restart server `date`....." >> /kientv/hayhay/monitorlog.txt
		cd /kientv/hayhay
		app=`jps | grep JettyServer`
		echo "Found app $app " >> /kientv/hayhay/monitorlog.txt
		pid=${app:0:5}
		echo "Found PID $pid " >> /kientv/hayhay/monitorlog.txt
		kill -9 $pid
		echo "check if pid is killed"  >> /kientv/hayhay/monitorlog.txt
		jps >> /kientv/hayhay/monitorlog.txt
		
		if [ "$app" != "" ] && [ "$pid" == "" ]; then
			echo "pid not found correctly and is NOT killed"  >> /kientv/hayhay/monitorlog.txt
		else
			echo "Start server "  >> /kientv/hayhay/monitorlog.txt
			nohup sh runservice start  >> logfile.log &
		fi
	fi
	#Check webcollector
	cd /kientv/webcollector
	echo "Ping collector `date`....." >> /kientv/hayhay/monitorlog.txt
	ping=`ant ping | grep alive`
	echo "Collector status $ping" >> /kientv/hayhay/monitorlog.txt
	collector=`jps | grep WebCollector` 
	colpid=${collector:0:5}
	echo "found collector pid $colpid" >> /kientv/hayhay/monitorlog.txt
		
	if [ "$ping" != "" ]; then
		echo "Collector still alive"  >> /kientv/hayhay/monitorlog.txt
	else
		echo "Collector is die so restart"  >> /kientv/hayhay/monitorlog.txt
		if [ "$colpid" != "" ]; then
			echo "Kill colpid $colpid" >> /kientv/hayhay/monitorlog.txt
			kill -9 $colpid
			echo "Check after kill" >> /kientv/hayhay/monitorlog.txt
			jps >> /kientv/hayhay/monitorlog.txt
		fi
		nohup sh runservice start  >> logfile.log &
		
	fi
	running="false"
	export $running

	echo "Finished"  >> /kientv/hayhay/monitorlog.txt


fi

