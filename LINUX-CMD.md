### 查看当前磁盘容量
```aidl
df -hT
(T是大写的字母)
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
#### which命令的绝对路径
```
which命令用于查找并显示给定命令的绝对路径
http://man.linuxde.net/which
which pwd
```
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
   