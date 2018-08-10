### ELK服务宕机后的启动脚本
```$xslt
01-elasticsearch
注：启动ES不能使用root用户
su elsearch
nohup /usr/es/elasticsearch-5.6.10/bin/elasticsearch  >/dev/null 2>&1 & 
查看一下端口验证是否启动：
netstat -tunlp 
http://192.168.0.52:9200/
==
02-elasticsearch-head
cd /usr/es/elasticsearch-head-master/
nohup grunt server >/dev/null 2>&1 & 
查看一下端口验证是否启动：
netstat -tunlp 
http://192.168.0.52:9100/
参考：
后台启动elasticsearch-head
nohup grunt server &exit
nohup grunt server &
https://www.cnblogs.com/cheyunhua/p/8087665.html
==
03-kibana
 nohup /usr/es/kibana-5.6.10-linux-x86_64/bin/kibana>/dev/null 2>&1 & 
==
04-logstash
cd  /usr/es/logstash-5.6.10/bin
nohup ./logstash  -f first-pipeline.conf --config.reload.automatic >/dev/null 2>&1 & 
==
05-filebeat

cd /usr/es/filebeat-5.6.10-linux-x86_64
./filebeat -e -c filebeat.yml 
nohup ./filebeat -e -c filebeat.yml  >/dev/null 2>&1 & 
 .\filebeat.exe -e -c filebeat.yml
```
### nohup不输出日志信息的方法
```
nohup不输出日志信息的方法
https://blog.csdn.net/u012164361/article/details/71527026
推荐方案使用2 
解决方案 
只输出错误信息到日志文件 
nohup ./program >/dev/null 2>log & 
什么信息也不要 
nohup ./program >/dev/null 2>&1 & 
知识补充，关于Linux的重定向

```
### 启动和关闭：
```$xslt
日志采集客户端 filebeat 安装部署
https://www.cnblogs.com/zhaojonjon/p/7289498.html
启动：nohup /home/elk/filebeat/filebeat -e -c /home/elk/filebeat/filebeat.yml  &
关闭：kill -9 `ps aux|grep filebeat|head -1|awk -F" " '{print $2}'`
查看进程：ps aux |grep filebeat
```