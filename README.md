# 抓取新闻入库到mongodb

### mongodb安装
[下载mongo](http://pan.baidu.com/s/1nuP1Nvz)

	cd /data
	tar zxvf mongodb-linux-x86_64-3.2.8.tgz
	mv mongodb-linux-x86_64-3.2.8 mongodb
	mkdir /data/mongodb/db
	mkdir /data/mongodb/logs
	mkdir /data/mongodb/conf
	
	vi /data/mongodb/conf/mongodb.conf
	dbpath = /data/mongodb/data
	logpath = /data/mongodb/logs/mongodb.log
	port = 27017
	fork = true
	logappend = true
	pidfilepath = /data/mongodb/logs/mongodb.pid
	
	启动
	/data/mongodb/bin/mongod -f /data/mongodb/conf/mongodb.conf