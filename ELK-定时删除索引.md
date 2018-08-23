#### 1.elk删除索引思路
```
删除索引：目前推荐的做法：先关闭索引然后再删除索引。-byArvin(2018-08-23)
关闭索引后就不会再有新的数据写到索引，这时再删除索引会好一些
原因：
elasticsearch关闭历史索引??
https://elasticsearch.cn/question/2661
关闭索引。是的,你没有看错。尤其是日志数据, 一般短期内的数据还会被用于数据统计分析, 历史数据再被翻阅的可能性比较小, 由于倒排词典的索引常驻内存,无法 GC, 尤其随着日积月累索引越来越多, 内存捉襟见肘(主要是穷), 动辄 gc 几十秒, 告警个不停, 或者直接 gg 了, 索引我们做了定时关闭历史索引, 只保留近期一段时间的索引开启
着, 如果需要查询历史数据再单独开启。
求教如何实现ES关闭历史索引?不是很理解上面说的..
==
关闭索引，主要是倒排词典的索引常驻内存,无法 GC。减少内存中的历史索引值。
==
关闭索引命名：
curl -XPOST http://example.comr:9200/my_index/_close
curl -XPOST http://example.comr:9200/my_index/_open

```
#### 2.elk每日清除30天索引脚本

日常elk产生日志太多，故写个脚本放在定时任务，定时清理脚本

查询索引：curl -XGET 'http://127.0.0.1:9200/_cat/indices/?v'

删除索引：curl -XDELETE 'http://127.0.0.1:9200/索引名字'

定时删除30天前的索引
```
#!/bin/bash
#定时清除elk索引，30天
DATE=`date -d "30 days ago" +%Y.%m.%d`
#INDEX=`curl -XGET 'http://127.0.0.1:9200/_cat/indices/?v'|awk '{print $3}'`
curl -XDELETE 'http://127.0.0.1:9200/*-$DATE'
```
参考地址：
elk每日清除30天索引脚本[https://www.iyunw.cn/archives/elk-mei-ri-qing-chu-30-tian-suo-yin-jiao-ben/]

