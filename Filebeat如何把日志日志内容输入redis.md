### 1.数据输出流程
Filebeat->Logstash->Redis->Logstash-ES

注：Redis支持用户名与密码连接方式

### Filebeat直接把数据存储到Redis

- [Filebeat之input和output（包含Elasticsearch Output 、Logstash Output、 Redis Output、 File Output和 Console Output）](https://www.cnblogs.com/zlslch/p/6623106.html)
- [Redis Output| Filebeat Reference](https://www.elastic.co/guide/en/beats/filebeat/1.3/redis-output.html)

```
output:
  redis:
    # Set the host and port where to find Redis.
    host: "localhost"
    port: 6379

    # Uncomment out this option if you want to store the topology in Redis.
    # The default is false.
    save_topology: true

    # Optional index name. The default is filebeat and generates filebeat keys.
    index: "filebeat"

    # Optional Redis database number where the events are stored
    # The default is 0.
    db: 0

    # Optional Redis database number where the topology is stored
    # The default is 1. It must have a different value than db.
    db_topology: 1

    # Optional password to authenticate with. By default, no
    # password is set.
    # password: ""

    # Optional Redis initial connection timeout in seconds.
    # The default is 5 seconds.
    timeout: 5

    # Optional interval for reconnecting to failed Redis connections.
    # The default is 1 second.
    reconnect_interval: 1
```

### Filebeat把数据输出到控制台

- [Console output| Filebeat Reference ](https://www.elastic.co/guide/en/beats/filebeat/1.3/console-output.html#console-output)

### [filebeat如何把日志日志内容输入redis，又如何取出呢？我的日志无法输入到redis](https://elasticsearch.cn/question/2169)

```
下面是我配置文件：
1、filebeat:
filebeat.prospectors:
- input_type: log
  paths:
    - /var/log/message.log
  document_type: linux
- input_type: log
  paths:
    - /var/log/httpd/access_log 
  document_type: api
  hosts: ["192.168.1.3:9200"]
output.logstash:
  hosts: ["192.168.1.3:5044"]
  ssl.certificate: "/etc/pki/tls/certs/logstash-forwarder.crt"
  ssl.key: "/etc/pki/tls/private/logstash-forwarder.key"
 
 
这里是logstash配置文件：
1、logstash-5.5.1/conf/redis-input.con
 
input {
 beats {
    port => "5043"
    ssl_certificate => "/etc/pki/tls/certs/logstash-forwarder.crt"
    ssl_key => "/etc/pki/tls/private/logstash-forwarder.key"
 }
}
output {
   redis {
        host => "192.168.1.3"
        port => 6379
        data_type => "list"
        key => "logstash:redis"
        password => "123456"
    }
    stdout { codec => rubydebug }
}
 
 
2、logstash-5.5.1/conf/redis-output.conf
 
input {
    redis {
        data_type => "list"
        key => "logstash:redis"
        host => "192.168.1.3"
        port => 6379
        password => "123456"
    }
}
output {
    if[type] =="linux"{
     elasticsearch {
        hosts => [ "192.168.1.3:9200" ]
    index => "linux-%{+YYYY.MM.dd}"
        document_type => "%{[@metadata][type]}"
        flush_size => 20000
        idle_flush_time => 10
        template_overwrite => true
    }
   }else if[type] =="api"{
     elasticsearch {
        hosts => [ "192.168.1.3:9200" ]
        index => "api-%{+YYYY.MM.dd}"
        document_type => "%{[@metadata][type]}"
        flush_size => 20000
        idle_flush_time => 10
        template_overwrite => true
    }
   }
    
  stdout { codec => rubydebug }
}
 
 
配置文件成功启动没有问题：
但redis上查看是没有日志过来的
127.0.0.1:6379> auth 123456
OK
127.0.0.1:6379> llen "linux"
(integer) 0
127.0.0.1:6379> llen "api"
(integer) 0
127.0.0.1:6379> 
```
