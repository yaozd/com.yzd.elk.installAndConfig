### ElasticSearch的内存设置
- logstash与elasticsearch内存设置

```
此值的大小与当前机器的可使用资源有关
调整内容
config->jvm.options
==
#-Xms1g
#-Xmx1g
-Xms512m
-Xmx512m
```
- [限制内存使用](https://www.elastic.co/guide/cn/elasticsearch/guide/current/_limiting_memory_usage.html)

```
选择堆大小（Choosing a Heap Size）
在设置 Elasticsearch 堆大小时需要通过 $ES_HEAP_SIZE 环境变量应用两个规则：
1.
不要超过可用 RAM 的 50%
Lucene 能很好利用文件系统的缓存，它是通过系统内核管理的。如果没有足够的文件系统缓存空间，性能会受到影响。 此外，专用于堆的内存越多意味着其他所有使用 doc values 的字段内存越少。
2.
不要超过 32 GB
如果堆大小小于 32 GB，JVM 可以利用指针压缩，这可以大大降低内存的使用：每个指针 4 字节而不是 8 字节。
```
- [堆内存:大小和交换](https://www.elastic.co/guide/cn/elasticsearch/guide/current/heap-sizing.html)

```
这里有两种方式修改 Elasticsearch 的堆内存。最简单的一个方法就是指定 ES_HEAP_SIZE 环境变量。服务进程在启动时候会读取这个变量，并相应的设置堆的大小。 比如，你可以用下面的命令设置它：

export ES_HEAP_SIZE=10g
此外，你也可以通过命令行参数的形式，在程序启动的时候把内存大小传递给它，如果你觉得这样更简单的话：

./bin/elasticsearch -Xmx10g -Xms10g 


确保堆内存最小值（ Xms ）与最大值（ Xmx ）的大小是相同的，防止程序在运行时改变堆内存大小， 这是一个很耗系统资源的过程。

通常来说，设置 ES_HEAP_SIZE 环境变量，比直接写 -Xmx -Xms 更好一点。
```
- [不要触碰这些配置！](https://www.elastic.co/guide/cn/elasticsearch/guide/current/dont-touch-these-settings.html)

```
1.
垃圾回收
目前还是使用CMS做为回收，G1暂时还是不稳定（byArvin-20180917-1423）
2.
线程池
```