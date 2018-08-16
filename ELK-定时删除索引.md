
#### elk每日清除30天索引脚本

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

