# 模块化服务启动脚手架

## 项目结构


### 概述

模块化手脚架，模块化封装基础的一些底层通用服务，按照其功能做拆分，分成相互独立的模块，以便于每个模块只包含与其功能相关的内容，模块之间通过接口调用。将一个大的系统模块化之后，每个模块都可以被高度复用。模块化后可以方便重复使用和插拨到不同的平台，不同的业务逻辑过程中；目前脚手架主要是对四个功能进行模块化封装，分别是：starter-user(用户模块)、starter-login(登录模块)、starter-menu(菜单模块)、starter-role(角色权限模块)

-----

### 项目结构详细说明

#### starter-common
* 基础工具类模块，主要是其他模块通用的工具类方法；
* Swagger基础配置类，可以通过注解直接启动swagger的配置；
* result返回实体定义
* exception的相关捕捉定义
* 引入其他模块时会自动依赖该模块
* 如果需要使用该模块定义的swagger配置，可在启动类上加上@EnableStarterSwagger注解

#### starter-user
* user模块的数据字典： 
  |字段 |字段类型|说明     
  ---| --- | ---|  
  id|integer|用户id  
  user_name | varchar | 用户账号
  password | varchar|密码
  nick_name | varchar | 昵称
  phone | varchar | 电话号码
  email | varchar | 邮箱
  icon | varchar | 头像
* user模块主要是用户信息的增删改查
* 用户密码通过BCrypt明文加密
* 整个user模块的加载通过注解@EnableUserStarter进行启动类加载
* maven引用：  
 ```
<dependency>
    <groupId>com.startdt.platform</groupId>
    <artifactId>starter-user</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

#### starter-login
* login模块主要是用作登录，目前主要用作用户登录；
* 该模块会依赖user模块，如果启动login模块的时候，会默认启动user模块
* 登录成功会将用户信息通过Jwt进行加密生成tocken返回前端，前端下次请求头部附带token进行解密出相应的用户信息并进行后续操作；
* 登录拦截器采用的是读取配置文件中的自定义“login.un.filter”配置的值，通过该值接入方可以自定义哪些url需要拦截登录，默认不拦截；
示例：login.un.filter=/login,/register
* 登录成功的信息是存储在CurrentUser当中，接口中可以直接通过该类获取用户信息；(只有拦截接口才能生效)
* 登录模块启动通过注解@EnableLoginStarter进行启动加载
* maven引入：  
  
```
<dependency>
    <groupId>com.startdt.platform</groupId>
    <artifactId>starter-login</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```


#### starter-role
* role模块的数据字典： 
* role_permission（角色权限表）
  |字段 |字段类型|说明     
  ---| --- | ---|  
  id|integer|用户id  
  role_name | varchar | 角色名称
  permission | text | 权限list，json格式
  nick_name | varchar | 昵称
  status | tinyint | 角色启用状态：0->禁用；1->启用
* grant_permission_info（授权表）
  |字段 |字段类型|说明     
  ---| --- | ---|  
  id|integer|用户id  
  principal_part | varchar | 授权主体（目前是用户id）
  principal_part_type | tinyint | 主体类型，1用户，2运用
  resources | varchar | 资源
  resources_type | tinyint | 资源类型，1 角色，2 菜单权限，3 其他权限
  status | tinyint | 授权是否开启：0->禁用；1->启用  
* 角色权限模块主要是对角色直接插入对应的权限列表，然后主体关联相应的角色，从而展现拥有的权限列表，和菜单树的权限标识；
* 基础接口主要是对角色的权限进行增删改查，根据用户对菜单树的权限查询及普通权限列表查询；
* 模块开启通过注解@EnableRoleStarter
* maven引入：  
  
```
<dependency>
    <groupId>com.startdt.platform</groupId>
    <artifactId>starter-role</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

#### starter-menu
* 菜单模块主要是菜单缓存工具类，各个模块的json菜单静态文件初始化加载到缓存里面；
* 无权限的菜单树的统一接口加载；


## 脚手架的说明
* 采用mybatis通用sql映射框架，没有采用mybatis-plus，虽然整体代码简洁，但是无法良好的在未采用mp框架的服务上运行；
* 底层dao层的代码自动生成采用了generator自动生成工具，具有所有基本的sql方法，除了连表查询外
* database采用了业务方的配置，脚手架对各个模块加了@MapperScan扫描注解，会自动加载mapper实例，利用业务方同一数据源，不会产生冲突

## 使用

1. maven依赖需要引用模块的jar包进行引用
2. 根据需要加载的模块加上对应模块加载的注解
3. 在数据库下新建对应模块所需要的sql表
4. 最后就是启动自己的服务即可加载所需要的模块并提供服务；

## 注意事项

如果需要自定义菜单静态json文件必须采用本脚手架的设计理念，读取json流文件转成对应的菜单树实体加载到菜单模块的缓存当中，在服务启动完成之前加载，以保证后续利用菜单树可直接在缓存中获取；

