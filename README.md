# com.yzd.elk.installAndConfig
ELK-5.6.10版本的安装与配置
### Grok Debugger中文站,ELK日志解析工具
http://grok.qiexun.net/
```
#
通过http://localhost:9200/查看集群信息
=========================================================
#修改Elasticsearch配置文件 =》Head请求Elasticsearch服务跨域问题 elasticsearch\config\elasticsearch.yml
# ---------------------------------- custom -----------------------------------
#修改一下ES的监听地址，这样别的机器也可以访问
network.host: 0.0.0.0   
#端口号，默认就好    
http.port: 9200             
# 增加新的参数，这样head插件可以访问es
http.cors.enabled: true 
http.cors.allow-origin: "*"

=========================================================
#
http://localhost:9200/

/////////////////////////////////////////////////////////
安装node
网址：https://nodejs.org/en/download/ 下载Windows版msi的
node -v
==
安装grunt
npm install  grunt 
npm install -g grunt-cli
==
grunt -version

安装head

git clone git://github.com/mobz/elasticsearch-head.git
cd elasticsearch-head
npm install
npm run start
open http://localhost:9100/
==========================================================
参考地址：
在Windows上安装Elasticsearch 5.x
https://www.cnblogs.com/puke/p/7145687.html?utm_source=itdadao&utm_medium=referral

/////////////////////////////////////////////////////////
下载地址：
https://www.elastic.co/downloads/past-releases
软件版本列表：
elasticsearch-5.6.10
filebeat-5.6.10
logstash-5.6.10
/////////////////////////////////////////////////////////
fiebeat的配置
1.IP问题：
name: 192.168.1.111
2.索引问题：
在output，es地址下，添加如此内容 index: "index_name"
3.分行问题
4.时间问题

/////////////////////////////////////////////////////////
logstash的配置
1.需要提取的内容统一使用“[提取的信息]”
例："\[ELK=]\[(?<T1>[^]]*)] \| \[(?<T2>[^]]*)] \| \[(?<T3>[^]]*)] \| \[(?<T4>[^]]*)] \| \[(?<T5>[^]]*)] \| \[(?<T6>[^]]*)] \| -->[\s\S]*"
2.测试配置文件
 .\logstash.bat -f first-pipeline.conf --config.test_and_exit
3.自动加载配置文件的修改
 .\logstash.bat -f first-pipeline.conf --config.reload.automatic
4.
document type->匹配正则提取信息(log_java)
fields.service->来判断索引(service_name)索引必须是小写
5.logstash处理时区问题
https://blog.csdn.net/wuyinggui10000/article/details/77879016
5.fied
参考地址：
ELK+FileBeat+Log4Net搭建日志系统
https://www.cnblogs.com/yanbinliu/p/6208626.html
/////////////////////////////////////////////////////////
配置Kibana
config/kibana.yml
1.kibana 8小时->时区问题
@timestamp 时区问题
由于Kibana在时间选择空间上的max date设置为UTC时间，而该死的UTC时间比中国时间少了8个小时
Kibana解决使用Custom无法正确选择时间的问题
https://blog.csdn.net/gsyzhu/article/details/8706019
==
2.

```
## kibana 显示字段顺序
```
1.traceId、level、logger、message、createTime、beat.name(IP地址)
```
## 启动
```
启动：
filebeat:
 .\filebeat.exe -e -c filebeat.yml
 ==
logstash:
2.测试配置文件
 .\logstash.bat -f first-pipeline.conf --config.test_and_exit
3.自动加载配置文件的修改
 .\logstash.bat -f first-pipeline.conf --config.reload.automatic
  ==
01-elasticsearch.bat启动：
cd .\elasticsearch-5.6.10\bin\
elasticsearch
  ==
02-elasticsearch-head.bat启动：
cd .\elasticsearch-head-master\
npm run start  
  ==
kibana启动：
cd .\kibana-5.6.10-windows-x86\bin\
kibana.bat
```
## logstash使用之日期处理
```
grok {
            match => {
             "message" => "\[ELK=]\[(?<traceId>[^]]*)]-\[(?<createTime>[^]]*)]-\[(?<level>[^]]*)]-\[(?<logger>[^]]*)][\s\S]*"
             } 
            overwrite => ["message"]
        }
date {
			match => ["createTime", "yyyy-MM-dd HH:mm:ss.SSS","yyyy-MM-dd HH:mm:ss.SSS"]
			target => "logTime"
			timezone => "Asia/Shanghai"
		}
使用logTime代替@timestamp
将时间字符串转日期类型，在新建索引时可以使用logTime作为索引的时间区域（Time）
注意：timezone => "Asia/Shanghai"（没有时区会出时间差问题）
参考：
logstash使用之日期处理
https://blog.csdn.net/qq_32292967/article/details/78623855
```