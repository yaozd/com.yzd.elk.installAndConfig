### linux awk命令详解
- [linux awk命令详解](https://www.cnblogs.com/xudong-bupt/p/3721210.html)
- [Linux三剑客之awk命令-byArvin推荐](https://www.cnblogs.com/ginvip/p/6352157.html)
- [shell脚本如何从json文件读取一个某个值](https://blog.csdn.net/lys07962000/article/details/53792312?locationNum=15&fps=1)

示例1：
```
vim json.txt 
{
    "people": [
        {
            "firstName": "Brett",
            "lastName": "McLaughlin",
            "email": "aaaa"
        },
        {
            "firstName": "Jason",
            "lastName": "Hunter",
            "email": "bbbb"
        },
        {
            "firstName": "Elliotte",
            "lastName": "Harold",
            "email": "cccc"
        }
    ]
}
==
cat json.txt 
cat json.txt | awk  '/firstName/{print$0}'| awk -F '"' '{print$4}'
```
示例2(读取ES的JSON数据)：
- [linux shell awk获得外部变量（变量传值）简介](https://www.cnblogs.com/chengmo/archive/2010/10/03/1841753.html)
- [linux shell 获取 curl 返回值](https://www.v2ex.com/t/181172)

```
curl  http://192.168.0.52:9200 |awk '/cluster_name/{print$0}'|awk -F '"' '{print$4}'
clusterName=curl  http://192.168.0.52:9200 |awk '/cluster_name/{print$0}'|awk -F '"' '{print$4}'
==
#!/bin/bash
RESULT=$(curl -s http://192.168.0.52:9200)
DATA=$(echo $RESULT|awk '/cluster_name/{print$0}'|awk -F '"' '{print$4}')
echo $DATA
if ["$DATA" -eq "OK"]; then
echo "OK"
fi

```
IF ELSE条件示例：
```
check() {
RESULT=$(curl -s http://192.168.5.100/xxx.php)
echo $RESULT

if [ "$RESULT" -eq "1111" ] ; then
echo "again"
sleep 1
check
elif [ "$RESULT" -eq "0000" ] ; then
echo "exit"
exit 0
else 
echo "error"
fi
}

check
```

### shell 中　exit0 exit1 的区别
```
exit（0）：正常运行程序并退出程序；

exit（1）：非正常运行导致退出程序；
```