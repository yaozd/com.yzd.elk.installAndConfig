

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
