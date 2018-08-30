## 主要用于ES-首次安装测试

###1. logstash与ES联通性测试
1.配置：first-pipeline-stdin.conf

```
input {
    stdin {
    }
}
output {
	elasticsearch { hosts => ["192.168.0.52:9200"] }
	stdout { codec => rubydebug }    
}
```
2.验证与启动

```
1.
验证
./logstash  -f first-pipeline-stdin.conf --config.test_and_exit
2.
启动
 ./logstash  -f first-pipeline-stdin.conf
```
3.数据验证
```
通过elasticsearch-head进行查看数据
```
###2. filebeat与logstash联通性测试
1.通过telnet 
```
telnet 192.168.0.52 9200
```
####3.配置参考”es-linux-conf“文件夹
```
1.
filebeat-[pj_business].yml
2.
filebeat-[pj_front_api].yml
```