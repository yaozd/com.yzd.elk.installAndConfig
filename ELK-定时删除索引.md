#### 1.elk索引保存策略
```
1.每日关闭7天前的日志
关闭：主要是减少ES对内存的使用开销，当需要查询7日前的数据，把索引再次手动打开就可以
2.每日删除15天前的日志
删除：主要是减少ES的存储空间。
```
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
#### [cron在线表达式](http://cron.qqe2.com/)
- [cron在线生成工具-byArvin推荐](http://cron.qqe2.com/)

#### 1.elk每日清除15天索引脚本-demo
- [elasticsearch的索引自动清理及自定义清理](https://www.cnblogs.com/vijayfly/p/6763127.html)

1.es-index-clear.sh(版本二)
- 每日关闭7天前的日志+每日删除15天前的日志

```
#!/bin/bash
#定时清除elk索引，15天
DATEForClose=`date -d "7 days ago" +%Y.%m.%d`
DATEForDelete=`date -d "15 days ago" +%Y.%m.%d`
echo ${DATEForClose}
echo ${DATEForDelete}
#当前日期
time=`date`
echo $time
#关闭15天前的日志
curl -XPOST http://192.168.0.52:9200/*-${DATEForClose}/_close
#删除15天前的日志
curl -XDELETE http://192.168.0.52:9200/*-${DATEForDelete}
#删除日志
if [ $? -eq 0 ];then
echo $time"-->close $DATEForClose index del $DATEForDelete index log success.." >> /tmp/es-index-clear.log
else
echo $time"-->close $DATEForClose index del $DATEForDelete index log fail.." >> /tmp/es-index-clear.log
fi

```
1.es-index-clear.sh(版本一)
```
#!/bin/bash
#定时清除elk索引，15天
DATE=`date -d "15 days ago" +%Y.%m.%d`
#DATE='2018.08.13'
echo ${DATE}
#当前日期
time=`date`
echo $time
#关闭15天前的日志
curl -XPOST http://192.168.0.52:9200/*-${DATE}/_close
#删除15天前的日志
curl -XDELETE http://192.168.0.52:9200/*-${DATE}
#删除日志
if [ $? -eq 0 ];then
echo $time"-->del $DATA log success.." >> /tmp/es-index-clear.log
else
echo $time"-->del $DATA log fail.." >> /tmp/es-index-clear.log
fi
```
2.LINUX系统下crontab最小单位是分钟【最小单位是分钟】

2.添加到任务计划(每天凌晨1点执行)
```
crontab -e
0 1 * * *  /usr/es/cmd-shell/es-index-clear.sh
0 1 * * *  /usr/es/cmd-shell/es-index-clear.sh > /dev/null 2>&1
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



#### 3.[linux下删除指定crontab定时任务](https://blog.csdn.net/ivnetware/article/details/51246871)
1. crontab -e
2. crontab -l
3. cat /var/log/cron
4. cat /var/spool/cron/root
5. sed -i '/test2.sh/d' /var/spool/cron/root 
6. crontab -l

```
test1.sh
==
echo 'hello world'
==
2、通过crontab -e命令编辑crontab任务，增加内容如下：
*/1 * * * * /dd/shell/test1.sh
*/1 * * * * /dd/shell/test2.sh
==
添加完成后，查看下crontab内容：
[root@localhost shell]# crontab -l
*/1 * * * * /dd/shell/test1.sh
*/1 * * * * /dd/shell/test2.sh
==
查看执行日志：crontab 的默认日志位置
cat /var/log/cron
tail /var/log/cron
==
增加了crontab任务后，在/var/spool/cron目录下会有一个当前登录账号命名的文件。比如我的登录账号是root。则会存在一个root文件。该文件的内容就是刚添加的crontab任务。
[root@localhost cron]# cat /var/spool/cron/root 
*/1 * * * * /dd/shell/test1.sh
*/1 * * * * /dd/shell/test2.sh
==
3、删除crontab内容里的test2.sh的任务
其实该处是使用sed命令来处理/var/spool/cron/root 文件，将含test2.sh的行的内容删除掉。
 sed -i '/test2.sh/d' /var/spool/cron/root 
==
命令执行完后，再通过crontab -l命令查看。
[root@localhost shell]# crontab -l
*/1 * * * * /dd/shell/test1.sh

可以看到test2.sh的任务被删除掉了。通过观察，test2.sh的脚步也不再被执行。说明确实删除成功。


```
#### crontab定时任务不执行的原因
- [分享一次Linux任务计划crontab不执行的问题排查过程](https://zhangge.net/5093.html)

```
 - 1.用户对执行文件的执行权限
 - 2.环境变量问题
于是在脚本里面载入环境变量：
#!/bin/bash
#先载入环境变量
source /etc/profile
#其他代码不变
==
```

### [Centos7:利用crontab定时执行任务](https://www.cnblogs.com/ihuangjianxin/p/7837193.html)
```
cron服务是Linux的内置服务，但它不会开机自动启动。可以用以下命令启动和停止服务：

systemctl start crond

systemctl stop crond

systemctl restart crond

systemctl reload crond
以上1-4行分别为启动、停止、重启服务和重新加载配置。
要把cron设为在开机的时候自动启动，在 /etc/rc.d/rc.local 脚本中加入 /sbin/service crond start 即可
查看当前用户的crontab，输入 crontab -l；
编辑crontab，输入 crontab -e；
删除crontab，输入 crontab -r

添加任务
  crontab -e
  0 */1 * * * command
  0 */2 * * * command
查询任务是否加了：
  crontab -l -u root #查看root用户
  0 */1 * * * command
  0 */2 * * * command
```
