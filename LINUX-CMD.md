### linux 通过命令行获取本机外网IP
```
curl myip.dnsomatic.com
```
### chown更改文件的所有者与chmod改变文件或目录的访问权限
```
用途：更改文件的所有者或组。命令由单词change owner组合而成
chown -R elsearch:elsearch /data/
==
chmod 777 XXX
```
### 查看当前磁盘容量
```
df -hT
(T是大写的字母)
```
### linux下，一个运行中的程序，究竟占用了多少内存（单位kb）
- [linux下，一个运行中的程序，究竟占用了多少内存](https://www.cnblogs.com/benmm/p/4248956.html)
- [Linux下查看某一进程所占用内存的方法](https://www.cnblogs.com/freeweb/p/5407105.html)
- [Linux进程的内存使用情况](https://segmentfault.com/a/1190000008125059)
- [Linux下查看内存使用情况方法总结](https://www.cnblogs.com/zhuiluoyu/p/6154898.html)

```
[RSS]表示程序占用了多少物理内存
[VSZ]表示程序占用了多少物理内存
ps aux --sort -rss
ps -aux|sort -k 4 -rn |head -10 （与ps aux --sort -rss是一样的，推荐使用ps aux --sort -rss）
//显示物理内存占用前10
ps aux --sort -rss|head -10
//显示单独一个进程
top -p 32475
//显示详细信息
cat /proc/32475/status
//实际上每段占用了多少物理内存，要想看到RSS，需要使用-X参数
pmap -X 32475

```
### 查看当前服务
```
whereis nginx (查看nginx的目录)
```
### mv命令
```
mv /usr/lib/* /001
是将 /usr/lib/下所有的东西移到/001/中。
mv /usr/lib/ /001
是将lib和其内部的所有东西移到/001/中。 此后，/usr里不再有lib; /001里有lib/及其原有的东西。
```

### linux查看端口占用情况
```$xslt
linux查看端口占用情况
https://www.cnblogs.com/wangtao1993/p/6144183.html
lsof -i:端口号 用于查看某一端口的占用情况，比如查看8000端口使用情况，lsof -i:8000
netstat -tunlp |grep 端口号，用于查看指定的端口号的进程情况，如查看8000端口的情况，netstat -tunlp |grep 8000
```
#### linux下查看进程的命令
```$xslt
ps | grep 和 ps aux | grep两个命令差别在于ps的参数aux

ps aux | grep ssh | grep -v "grep"
grep不显示本身进程
ps aux|grep \[s]sh
ps aux | grep ssh | grep -v "grep"
```
### grep 命令
```$xslt
每天一个linux命令（39）：grep 命令
https://www.cnblogs.com/peida/archive/2012/12/17/2821195.html
```
### 启动和关闭：
```$xslt
日志采集客户端 filebeat 安装部署
https://www.cnblogs.com/zhaojonjon/p/7289498.html

启动：nohup /home/elk/filebeat/filebeat -e -c /home/elk/filebeat/filebeat.yml  &
关闭：kill -9 `ps aux|grep filebeat|head -1|awk -F" " '{print $2}'`
查看进程：ps aux |grep filebeat
```
### CentOS7：没有netstat与ifconfig
```$xslt
yum install net-tools
sudo yum install net-tools
```
### tail命令使用方法演示例子
```$xslt
1、tail -f filename
说明：监视filename文件的尾部内容（默认10行，相当于增加参数 -n 10），刷新显示在屏幕上。退出，按下CTRL+C。
2、tail -n 20 filename
说明：显示filename最后20行。
3、tail -r -n 10 filename
说明：逆序显示filename最后10行。
补充：
跟tail功能相似的命令还有：
cat 从第一行開始显示档案内容。
tac 从最后一行開始显示档案内容。
more 分页显示档案内容。
less 与 more 相似，但支持向前翻页
head 仅仅显示前面几行
tail 仅仅显示后面几行
n 带行号显示档案内容
od 以二进制方式显示档案内容
``` 
####　awk命令
- [linux shell awk获得外部变量（变量传值）简介](https://www.cnblogs.com/chengmo/archive/2010/10/03/1841753.html)

```
curl  http://192.168.0.52:9200 |awk '/cluster_name/{print$0}'|awk -F '"' '{print$4}'
clusterName=curl  http://192.168.0.52:9200 |awk '/cluster_name/{print$0}'|awk -F '"' '{print$4}'
==
#!/bin/bash
RESULT=$(curl -s http://192.168.0.52:9200)
DATA=$(echo $RESULT|awk '/cluster_name/{print$0}'|awk -F '"' '{print$4}')
echo $DATA
``` 

#### which命令的绝对路径
```
which命令用于查找并显示给定命令的绝对路径
http://man.linuxde.net/which
which pwd
```

#### [shell脚本中字符串的常见操作及"command not found"报错处理(附源码)](https://www.cnblogs.com/han-1034683568/p/7217047.html)
```
字符串的定义与赋值
# 定义STR1变量，值为abc
STR1 = "abc"(错误写法)
STR1="abc"(正确写法)
==
字符串连接
STR1="abc"
STR2="abbcd abc ccc"

#连接STR1和STR2变量并赋值给STR3
STR3=$STR1$STR2
echo $STR3

#连接STR1与"abc" 并赋值给STR4
STR4=$STR1"abc"
echo $STR4
==
if条件语句中的空格
# 比较两个字符串是否相等中if语句的写法

STR1="abc"
STR2="abcd"

if[$STR1=$STR2](错误写法)

if [ $STR1 = $STR2 ](正确写法)
```
#### [shell中判断字符串包含关系的方法](https://www.cnblogs.com/han-1034683568/p/7217047.html)
```
利用grep关键字查找
#!/bin/bash

# file:testStr.sh
# author:13
# date:2017-07-21

STR1="abc"
STR2="abbcd abc ccc"

result=$(echo $STR2 | grep "${STR1}")
echo $result
 if [[ "$result" != "" ]]
 then
     echo "包含"
 else
     echo "不包含"
 fi
==
利用字符串运算符(更简洁、推荐使用)
#!/bin/bash

# file:testStr.sh
# author:13
# date:2017-07-21

STR1="abc"
STR2="abbcd abc ccc"

if [[ $STR2 =~ $STR1 ]]
 then
     echo "包含"
 else
     echo "不包含"
 fi
```

### [101个shell脚本](http://blog.51cto.com/zero01/2046242)

### 70个经典的 Shell 脚本
```
参考：
分享70个经典的 Shell 脚本面试题与答案
https://www.jb51.net/article/135168.htm
Shell 教程
http://www.runoob.com/linux/linux-shell.html
shell脚本在线运行
http://www.runoob.com/try/runcode.php?filename=helloworld&type=bash
eg:
-bash: ./filebeat-start.sh: Permission denied
//提升权限
chmod 777 filebeat-start.sh 
```
### shell 示例
```
#!/bin/bash
echo "Hello World !"

```
   