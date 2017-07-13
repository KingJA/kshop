# TODO
* Spring MVC数据绑定
# GET
* 不建议用select * ，因为随着业务的发展，表有可能越来越大，而且维护表的
不止一个人，别人可能加了你并不要的字段
* parameteMap 和parameteType的区别
parameteMap存放的是定义的JavaBean,parameteType是Map等数据结构，可缩写，比如
com.util.Map=>map
* json添加@ResponseBody注解
* 切换其他分支前要提交当前分支git commit
* count(1)比count(*)效率高些
private ServerResponse(int resutlCode, String message)
private ServerResponse(int resutlCode, T data)
第二个参数为String类型则调用第一个构造方法，如非String则调第二个