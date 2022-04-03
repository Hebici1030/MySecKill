# Mall项目总结与学习

# 前端：

## 注解

GetMapping

PostMapping

 PutMapping;如果修改的，就倾向于使用PutMapping

DeleteMapping

PatchMapping,

RequestMapping:**@RequestMapping如果没有指定请求方式，将接收Get、Post、Head、Options等所有的请求方式。**

@RestController：相当于@ResponseBody+@Controller注解的组合，如果使用该注解，

则Controller中的方法无法返回jsp页面，或者html页面，配置的视图解析器不起作用，染回的内容，就是Return中的内容。

因为ResponseBody将java对象转为json格式

## 前后端消息传递方式

### 前端的数据类型：变量、对象、数组、JSON

### 1.变量

变量一般是在表单中指定的name对应的形参，也可以在形参中使用别名。

前端的<HTML>的表单Form的input 的名字。

接收表单数据只需要在**方法的参数加入响应的字段**，**对应**表单input的**name属性**，因为是**通过反射技术实现的所以字段要完全相同**

表单提交后会获得数据。跳转方式就是Controller返回的字符串地址页面。可以通过继承视图解析提来改写前缀后缀。

### 2.对象

Spring MVC可以使用bean来接收参数，因为是反射技术，所以属性字段依然要保持完全一样。

```java
public class user {
    private String username;
    private String password;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}
//
 @RequestMapping(value="/Model",method=RequestMethod.POST)
    public String loginModel(user u){
        System.out.println(u.getUsername()+" "+u.getPassword());
        return "form.jsp";
    }　
```

### 3.数组

批量提交就是在表单部分完成的操作，共用一个name.因此在Controller只需要载入name相同的数组形参。

4.JSON

- JSON 指的是 JavaScript 对象表示法（**J**ava**S**cript **O**bject **N**otation）

- JSON 是轻量级的文本数据交换格式

- JSON 独立于语言：JSON 使用 Javascript语法来描述数据对象，但是 JSON 仍然独立于语言和平台。JSON 解析器和 JSON 库支持许多不同的编程语言。 目前非常多的动态（PHP，JSP，.NET）编程语言都支持JSON。

- JSON 具有自我描述性，更易理解

  Restful风格的架构中，传递数据使用JSON差不多可以算是一种标准。MVC提供了自动序列化JSON数据的注解。

  Spring MVC解包需要jackson这个东西

  @ResponseBody是返回值写到响应体中，

  @RequestBody是把请求主体信息中的内容转换成Demo类。

### 4.SpringMVC 还支持的传递参数方式

1. @PathVariable

2. @RequestParam

   在方法入参处使用 @RequestParam 注解指定其对应的请求参数。@RequestParam 有以下三个参数：

   - value：参数名
   - required：是否必须，默认为 true，表示请求中必须包含对应的参数名，若不存在将抛出异常
   - defaultValue：参数默认值


   通过 @RequestParam 接收请求参数适用于 get 和 post 提交请求方式，示例代码如下。

3. @ModelAttribute

## Rest风格

1. Restful是一种软件设计风格，使得代码更有层次更简洁。
2. 每一个请求对应唯一的url地址
3. GET用来获取资源，POST用来新建资源（也可以用于更新资源），PUT用来更新资源，DELETE用来删除资源，这样就统一了数据操作的接口，仅通过HTTP方法，就可以完成对数据的所有增删查改工作。

## Session与Cookie Token

1. Cookie

   Cookie是为了解决http协议的无状态导致服务器端无法区分用户的缺陷。因此一个服务器可以通过向客户端发送Cookie来识别西悉尼

   Cookie是长度为4K的标识性文本，在和服务器连接时会随着网址一同发过去。

   Cookie的重要特点：

   1. 不可跨域性，通过DNS的域名系统，不同域名之间的cookie不能访问
   2.  cookie设置有有效期maxAge 以秒为单位 0代表cookie失效 -1代表关闭浏览器后失效。
   3. cookie需要存放在浏览器中，可以在本地做保存。

2. Session

   Session是存放在服务器端的识别用户。其通过键值对的结构保存用户的信息。setAttribute()。

   其在用户第一次访问的时候创建。服务器会保存多个客户端的Session。获取Session的时候也不需要声明获取谁的Session。

   

   **Session机制（Servlet的request()获得）决定了当前客户只会获取到自己的Session，而不会获取到别人的Session。各客户的Session也彼此独立，互不可见**。
   
   
   
   重要特点：
   
   1. Session依据该Cookie来识别是否为同一用户，该Cookie为服务器自动生成的，它的maxAge属性一般为–1。
   2. Session在浏览器关闭后生命周期就会结束。
   3. Session为了快速响应会存放在内存当中。

SpringMVC中使用restful（返回ResponseVo）。即包装类。

创建 vo 包和 ResponseVo 类实现返回值的统一包装。

HTTP协议

## 拦截器

拦截器就是在访问url时会进行权限检查。通过此我们可以实现登陆验证、以及一些登录日记记录等等

实现方法：继承或实现 HandlerInterceptor或WebRequestInterceptor

其有三个方法：preHande postHandler after;

拦截器的配置要在springmvc 的配置文件中 ：

1. SSM框架中 web.XML配置dispatcher制定了springmvc的路径
2. springboots只需要创建一个全局变量 一个实现 ，一个使用@Configuration注解

## 表单数据校验

在controller层的使用

# 后端：

## Redis使用



## Json序列化

序列化：将JAVA对象转换为二进制字节存储在硬盘或在网络上传播

反序列化

## 错误通过枚举类打包

JAVA枚举

## Spring事务（Spring）

- 事物的四个特性：

  A:

  C: 

  I:

  D:

- Spring支持的事务管理方式

  1. 编程事务管理：在代码中编写事务管理方式，可以控制事物的粒度。
  2. 声名事务管理：注解方式使用事务管理，没有侵入性，耦合度低。其底层是使用AOP技术来动态代理事务管理。

- 事务管理器：PlatformTransactionManager

  

- TransactionDefinition 接口(封装XML中事务配置信息)

  定义了事务隔离级别、传播行为、只读等等。

  1. 事务隔离级别：
     1. READ_UNCOMMINTED
     2. READ_COMMINTED
     3. REPEATABLE_READ
     4. SERIALIZABLE 
  2. 事务的传播行为 （是指事务A调用事务B时，事务B是在当前A的事务下执行还是创建新的事务）
     1. 支持当前事务
        1. MANDTOARY 
        2. NESTED 
     2. 不支持当前事务
        1. NEVER
        2. NOT_SUPPORT
     3. 创建新的事务
        1. REQUIRE
        2. REQUIRE_NEW
        3. SUPPORT
  3. dificiton还定义了readonly、Timeout

- 基于注解方式

  tx 命名空间提供了一个 <tx:annotation-driven> 元素，用来开启注解事务

  通过 <tx:annotation-driven> 元素开启注解事务后，Spring 会自动对容器中的 Bean 进行检查，找到使用 @Transactional 注解的 Bean，并为其提供事务支持。

- 基于XML方式

  1. 引入tx命名空间
  2. 创建事务管理器和dataSource
  3. 创建事务通知
  4. 创建AOP

# 菜单系统

## 数据库：

1. 菜单有分级，父目录和子目录保存在统一数据库中。父目录的parentId = 0;
2. 选出全部目录后，将父目录保存然后遍历子目录的parentID.

## Service层：

主要目标点：将一个主目录找到其全部子目录并且打包成VO

会有Enum的更新。

## Controller层：

getMapping获得全部目录即可。

# 用户模块

## 数据库

表结构：

id 唯一标识

username 唯一标识

password 

email 唯一标识

role

Q&A

time

SpringBoots配置数据库信息。在application.yml配置

## Service层总结

开发流程：逆向工程生成pojo dao mapper

1. 登录功能

   

2. 注册功能

   注册email **username**不能重复，所以需要通过mapper查询。

   注意点：密码使用MD5加密存入数据库

   返回值采用restful模式的json数据格式

   ```
   T data;
   String mag;
   Integer status
   ```

3. 登出功能

   Session中移除属性

## Controller层总结

特殊点：引入了from类完成了表单验证。

1. 表单验证

2. 通过session验证用户身份。login成功后，通过VO的T DATA向attribution填写对象

3. 引入了拦截器.

   拦截器继承了handlerinterceptor和webinterceptor。

   返回false中断。返回true代表通过允许。

   随后再configuration注解一个类实现WebMvcConfigurer。然后在方法载入拦截器类即拦截的路径。

   难点：

   拦截器返回true继续执行，返回false如何返回到JSON数据？不能直接returnVo包装类，因为重载方法为Boolean。

   解决方案：

   - @ControllerAdvice配合@ExceptionHandler使用。将异常抛到controller时,可以对异常进行统一处理,规定返回的json格式
   - 在service层没有被catch到，那么，这个异常就传递到了controller层，如果controller不做处理，页面看到的就是500了。
   - 全局异常处理，针对的是这种情况：当异常不能被catch的时候，避免给用户展示500界面，在controller层做的统一处理，跳转到我们自定义的错误页面，或者返回自定义的error信息。

   
