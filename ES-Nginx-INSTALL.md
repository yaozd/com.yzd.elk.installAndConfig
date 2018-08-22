### 编译安装nginx 1.8.1 及配置
1.环境准备：先安装准备环境
```
yum install gcc gcc-c++ automake pcre pcre-devel zlip zlib-devel openssl openssl-devel 
```

2.下载nginx 安装包
```
[root@localhost ~]# cd /usr/local/src/
wget http://nginx.org/download/nginx-1.8.1.tar.gz 
tar zxvf nginx-1.8.1.tar.gz 
cd nginx-1.8.1
./configure && make && make install
```

3.启动Nginx
```
whereis nginx (查看nginx的目录)
cd /usr/local/nginx/
sbin/nginx (启动Nginx)
ps -aux|grep nginx |grep -v 'grep'
netstat -ntlp
```

### nginx的基本操作
```
启动
[root@localhost ~]# /usr/local/nginx/sbin/nginx
停止/重启
[root@localhost ~]# /usr/local/nginx/sbin/nginx -s stop(quit、reload)
重启
[root@localhost ~]# /usr/local/nginx/sbin/nginx -s reload
命令帮助
[root@localhost ~]# /usr/local/nginx/sbin/nginx -h
验证配置文件
[root@localhost ~]# /usr/local/nginx/sbin/nginx -t
配置文件
[root@localhost ~]# vim /usr/local/nginx/conf/nginx.conf
```
### nginx参考文档
- [Nginx Linux详细安装部署教程](https://www.cnblogs.com/taiyonghai/p/6728707.html)
- [Nginx 之一：编译安装nginx 1.8.1 及配置](https://www.cnblogs.com/zhang-shijie/p/5294162.html)

### 使用nginx代理kibana并设置身份验证
```
server {
        listen       80;
        server_name  localhost;
        #charset koi8-r;
        #access_log  logs/host.access.log  main;
		
	    auth_basic "Restricted Access";
	    auth_basic_user_file /usr/local/nginx/conf/htpasswd.users; #auth_basic_user_file表示账号密码存放的文本地址 
        location /{
            proxy_http_version 1.1;
            proxy_set_header   Connection          "";
            proxy_set_header   Host             $host;
            proxy_set_header   X-Real-IP        $remote_addr;
            proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;
            proxy_pass http://192.168.0.52:5601; #转发到kibana
        }
		
        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }
    }
```
```
账号密码生成：
方案一：
通过http://www.matools.com/htpasswd 来生成采用Crypt(all Unix servers)方式密的密码
方案二：
# yum install -y httpd-tools
# htpasswd -bc /usr/local/nginx/conf/htpasswd.users admin admin
# cat /usr/local/nginx/conf/htpasswd.users
```
### 使用nginx代理kibana并设置身份验证-参考文档
- [使用nginx代理kibana并设置身份验证](https://www.cnblogs.com/keithtt/p/6593866.html)
- [ElasticSearch 通过nginx做HTTP验证](https://www.cnblogs.com/phpshen/p/8600582.html)
- [Nginx + http basic 限制访问Elasticsearch](https://blog.csdn.net/ysl1242157902/article/details/77101109)

### HTTP-[Nginx环境下http和https可同时访问方法](https://www.cnblogs.com/doseoer/p/5663182.html)

### 防火墙设置
```
编辑防火墙白名单
[root@localhost ~]# vim /etc/sysconfig/iptables
增加下面一行代码
-A INPUT -p tcp -m state -- state NEW -m tcp --dport 80 -j ACCEPT
保存退出，重启防火墙
[root@localhost ~]# service iptables restart
参考：
Nginx Linux详细安装部署教程
https://www.cnblogs.com/taiyonghai/p/6728707.html
```