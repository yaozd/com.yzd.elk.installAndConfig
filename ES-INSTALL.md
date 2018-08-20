## 01：ES -CentOS 7.4.1708 (Core) 安装
```$xslt
ES官网
https://www.elastic.co/products/elasticsearch

ELK-5.6.10版本的安装与配置

1.查看当前系统
cat /etc/centos-release
参考：
Linux安装JDK完整步骤
https://www.cnblogs.com/Dylansuns/p/6974272.html
[01]==
linux 安装 Elasticsearch5.6.x 详细步骤以及问题解决方案
https://www.cnblogs.com/lizichao1991/p/7809156.html
==
附： 
./elasticsearch console ——-前台运行 
./elasticsearch start ——-后台运行 
./elasticsearch install——-添加到系统自动启动 
./elasticsearch remove——-取消随系统自动启动 
[02]==
CentOS7使用firewalld打开关闭防火墙与端口
https://www.cnblogs.com/moxiaoan/p/5683743.html
附：
1、firewalld的基本使用
启动： systemctl start firewalld
关闭： systemctl stop firewalld
查看状态： systemctl status firewalld 
开机禁用  ： systemctl disable firewalld
开机启用  ： systemctl enable firewalld
[03]==
安装nodejs与grunt
cd /tmp/
wget https://npm.taobao.org/mirrors/node/v8.9.3/node-v8.9.3-linux-x64.tar.xz
tar -xvf node-v8.9.3-linux-x64.tar.xz 
mkdir -p /usr/local/node
mv node-v8.9.3-linux-x64 /usr/local/node/
vim /etc/profile.d/node.sh
==
加入
export NODE_PATH=/usr/local/node/node-v8.9.3-linux-x64
export PATH=$NODE_PATH/bin:$PATH
==
. /etc/profile
. /etc/bashrc
node -v
npm -v
npm install -g grunt-cli
grunt -version
参考地址：
node js npm grunt安装，elasticsearch-head 5.X安装
https://blog.csdn.net/fenglailea/article/details/52934263
[04]==
安装head
git clone git://github.com/mobz/elasticsearch-head.git
cd elasticsearch-head
修改
执行命令vim Gruntfile.js文件：增加hostname属性，设置为*
修改
vim _site/app.js 文件：修改head的连接地址:

npm install
npm run start 或者 grunt server
open http://localhost:9100/
linux 安装 Elasticsearch5.6.x 详细步骤以及问题解决方案
https://www.cnblogs.com/lizichao1991/p/7809156.html
[05]==
安装kibana
修改
kibana.yml
server.host: "0.0.0.0"
elasticsearch.url: "http://192.168.0.52:9200"
[06]==
安装logstash
vim first-pipeline.conf
2.测试配置文件
  ./logstash  -f first-pipeline.conf --config.test_and_exit
3.自动加载配置文件的修改
  ./logstash  -f first-pipeline.conf --config.reload.automatic
[07]==
 安装filebeat
 .\filebeat.exe -e -c filebeat.yml
[08]==
修改linux系统的时间EDT为CST
https://blog.csdn.net/yjh314/article/details/51669238
EDT：指美国东部夏令时间，波士顿、纽约市、华盛顿哥伦比亚特区，都在这个时区内，跟北京时间有12小时的时差，晚12小时
那么现在只要改成北京时间的时区CST就可以了，修改如下：
mv /etc/localtime /etc/localtime.bak
ln -s /usr/share/zoneinfo/Asia/Shanghai  /etc/localtime
date

[99]==
下载到window
yum install lrzsz    // 下载 上传下载工具sz和rz
sz xx.tar    // 下载 sz
==
在CentOS7.2中安装htop 安装 和 supervisor
https://blog.csdn.net/q56231293811/article/details/78455884
安装GCC及其编译的库
如果没有安装gcc，按如下来安装
yum install -y gcc
安装后，编译htop需要安装一个编译Linux内核的库
yum install -y ncurses-devel
HTOP下载，编译和安装
htop下载
wget http://sourceforge.net/projects/htop/files/latest/download
解压
tar -zxf download
cd htop-1.0.2
./configure && make && make install
```
### IK分词插件
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
### 目录结构
```$xslt
/usr/es/:
elasticsearch-5.6.10  
elasticsearch-5.6.10.zip  
elasticsearch-head-master  
elasticsearch-head-master.zip  
kibana-5.6.10-linux-x86_64  
kibana-5.6.10-linux-x86_64.tar.gz  
logstash-5.6.10  
logstash-5.6.10.zip
==
```
### nohup不输出日志信息的方法
```
nohup不输出日志信息的方法
https://blog.csdn.net/u012164361/article/details/71527026
推荐方案使用2 
解决方案 
只输出错误信息到日志文件 
nohup ./program >/dev/null 2>log & 
什么信息也不要 
nohup ./program >/dev/null 2>&1 & 
知识补充，关于Linux的重定向

```