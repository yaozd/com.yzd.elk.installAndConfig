
## 全量日志只有在分布式日志跟踪的情况下才有意义
1. 尽可能收集请求的入口信息（大数据请求除外）
2. 建议请求的参数进行json序列化操作，放便以后出问题时请求数据的回放

### http请求分类
1. 多媒体或文件等大请求（上传的数据比较大不需要做日志监控）
2. 普通请求（通过AOP对请求方法的参数进行json格式化输出到ELK中）

### ELK日志请求拦截流程（对请求数据全量监控）
1. 请求的URL地址-是否需要对静态资源地址过滤（LogURLInterceptor extends HandlerInterceptorAdapter）
2. AOP-》请求方法的所有参数进行json格式化(LogControllerAPIAspect)
3. dubbo请求的方法+参数（json格式化）

### controller命名空间的定义
1. 多媒体或文件等大请求的controller名称：controllerMultipartfile或者controllerFile
   - eg:com.xx.xx.controllerFile
2. Rest full api请求的controller名称：controllerApi
   - eg:com.xx.xx.controllerApi
3. 普通请求的controller名称：controller
   - eg:con.xx.xx.controller
4. 命名空间的定义主要是为了AOP时的拦截做隔离。

### json格式化
1. 暂时使用fastjson组件
