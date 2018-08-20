----
### 通过ES实现键外索引
- 参考代码：

```
老版本的ES
https://github.com/yaozd/MyNote/blob/master/framework/src/main/java/elasticsearch/elasticsearchs_233_demo
https://github.com/yaozd/MyNote/tree/master/framework/src/main/java/elasticsearch
新版本的ES-5.X
https://github.com/yaozd/MyNote/tree/master/elastic5/src/main/java/com/gourd/erwa/elastic5/demo
```
### Elasticsearch优化
- 参考代码：[新浪是如何分析处理32亿条实时日志的](http://www.open-open.com/lib/view/open1437018728240.html)

```

我们首先做了Elasticsearch优化，Hardware Level由于我们当时拿到机器没有选择余地，只开启了超线程；System Level的优化如关闭swap，调整max open files等；App Level的优化如Java运行环境版本的选择，ES_HEAP_SIZE的设置，修改bulk index的queue size等，另外还设置了默认的index template，目的是更改默认的shard，replica数并将string改为not_analyzed，开启doc_values以应对 elasticsearch进程OOM。详细的优化内容见 Elasticsearch Optimization Checklist。 
```
```
1， elasticsearch 进程JVM Heap High Usage（ > 90% ）。 

很长一段时间，我们都在应对JVM Heap High Usage，他带了的问题是Old GC次数多，时间长，es节点频繁退出集群，整个集群几乎停止响应。现在我们的主要策略是开启doc_values；[限制query执行时占用的JVM Heap size；analyzed string只允许做query，不允许facets或者aggs；]定期close 用户不需要的index。 
注：facets或者aggs代指：分组查询与聚合查询-byArvin 20180820-1111

2， Elasticsearch Query DSL、Facets、Aggs学习困惑。 

有人为此开发了使用SQL执行ES Query的插件，一定程度上减轻了进入门槛。我们给出的学习他们的建议是观察Kibana的Request Body或试用Marvel的Senese插件，它有自动完成Query、Facets、Aggs的功能。另外最常用的query是query string query，最常用的aggs是Terms、Date Histogram，可以应付大部分需求。
```

### DSL的诞生 | 复杂sql转成Elasticsearch DSL深入详解
```
https://blog.csdn.net/laoyang360/article/details/78556221
1、sql语句转成DSL有哪些方法？
方案一：借助工具 NLP团体开发的Elasticsearch-sql; 
2.X安装过，5.X没有再安装。 
===
方案二：借助工具ElasticHQ的自动转换模块： 
方案一、方案二和Github上其他语言开发的sql转DSL工具对简单的sql生成的DSL相对准确，但对于复杂的sql生成的不一定精确。（如上所示）
方案三：徒手生成。

```
### Elastic HQ插件-360企业安全集团终端安全子公司版(目录：/usr/es/)-byArvin推荐
```
https://github.com/360EntSecGroup-Skylar/ElasticHD
Elasticsearch 可视化DashBoard, 支持Es监控、实时搜索，Index template快捷替换修改，索引列表信息查看， SQL converts to DSL等
下载地址：（目前百度云有备份）
https://github.com/360EntSecGroup-Skylar/ElasticHD/releases/
目前使用的是-版本1.4.1
启动：
Linux and MacOS use ElasticHD
Step1: Download the corresponding elasticHD version，unzip xxx_elasticHd_xxx.zip
Step2: chmod 0777 ElasticHD
Step3: exec elastichd ./ElasticHD -p 127.0.0.1:9800 
windows
Step1: Download the corresponding elasticHD version，Double click zip package to unzip
Step2: exec elastichd ./ElasticHD -p 127.0.0.1:9800 
3.源码编译打包Source code compilation
git clone https://github.com/360EntSecGroup-Skylar/ElasticHD
cd ElasticHD
npm install
npm run build
cd ./main
statik -src=../dist
# go build
GO_ENABLED=0 GOOS=windows GOARCH=amd64  go build -o elasticHD.exe github.com/elasticHD/main
4.Docker Quick Start:
docker run -p 9200:9200 -d --name elasticsearch elasticsearch
docker run -p 9800:9800 -d --link elasticsearch:demo containerize/elastichd
Open http://localhost:9800 in Browser
```
#### Elastic HD(-360企业安全集团终端安全子公司)安装问题解决
```
问题一：
exec: "xdg-open": executable file not found in $PATH
https://blog.csdn.net/qq_35719950/article/details/80935677
没有安装xdg-open
安装xdg-utils之后xdg-open命令就可以使用了.
yum install xdg-utils
问题二：
xdg-open: no method available for opening 'http://127.0.0.1:9800'
把【./ElasticHD -p 127.0.0.1:9800 】改为【./ElasticHD -p 192.168.0.28:9800 】
就可以通过浏览器访问http://192.168.0.28:9800/
注：如何只希望只有本机访问使用：127.0.0.1
如何希望其他机器同样可以访问使用：0.0.0.0 或本机IP地址


```
### Elastic HQ插件(目录：/usr/es/)-byArvin不推荐使用
```
ElasticSearch5.6.3 安装步骤
https://blog.csdn.net/yqh5566/article/details/78558977
1. 下载
# wget https://github.com/royrusso/elasticsearch-HQ/archive/master.zip
2. 解压
# unzip elasticsearch-HQ-master.zip
3. 安装
# cd elasticsearch-HQ-master
4. 启动
以下二选一： 
# python -m SimpleHTTPServer（前台启动） 
# nohup python -m SimpleHTTPServer > elasticsearch-HQ.file 2>&1 &（后台启动）
5. 验证是否安装成功
访问http://192.168.102.30:8000/#cluster 
```
### IK分词插件(目录：/usr/es/)
```
IK分词插件
ElasticSearch5.6.3 安装步骤
https://blog.csdn.net/yqh5566/article/details/78558977
每台机器都需要安装 
以插件形式安装： 
# cd elasticsearch-5.6.3 
# ./bin/elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v5.6.3/elasticsearch-analysis-ik-5.6.3.zip 
访问: 
```
### ElasticSearch5.2.2 基本概念
- [ElasticSearch5.2.2 基本概念和集群配置详解](https://blog.csdn.net/WuLex/article/details/71194653)
- [ElasticSearch DSL结构的一些说明](https://blog.csdn.net/u011031430/article/month/2018/03)
- [Elasticsearch DSL 常用语法介绍](http://www.youmeek.com/elasticsearch-dsl/)
- [DSL的诞生 | 复杂sql转成Elasticsearch DSL深入详解](https://blog.csdn.net/laoyang360/article/details/78556221)

### 参考文档
- ./reference/Elasticsearch的DSL_2018_8_20.html

