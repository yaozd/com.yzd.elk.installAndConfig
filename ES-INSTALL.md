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
==
linux 安装 Elasticsearch5.6.x 详细步骤以及问题解决方案
https://www.cnblogs.com/lizichao1991/p/7809156.html
==
附： 
./elasticsearch console ——-前台运行 
./elasticsearch start ——-后台运行 
./elasticsearch install——-添加到系统自动启动 
./elasticsearch remove——-取消随系统自动启动 
==
CentOS7使用firewalld打开关闭防火墙与端口
https://www.cnblogs.com/moxiaoan/p/5683743.html
附：
1、firewalld的基本使用
启动： systemctl start firewalld
关闭： systemctl stop firewalld
查看状态： systemctl status firewalld 
开机禁用  ： systemctl disable firewalld
开机启用  ： systemctl enable firewalld
==
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
==
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
==
安装kibana
修改
kibana.yml
server.host: "0.0.0.0"
elasticsearch.url: "http://192.168.0.52:9200"
==
下载到window
yum install lrzsz    // 下载 上传下载工具sz和rz
sz xx.tar    // 下载 sz
==
```