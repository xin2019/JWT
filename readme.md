# SringBoot实现JWT登录（生成token方法在utils包下的JWTUtil中）
#### 前提条件：电脑开启redis和mysql
#### 项目背景：SrpingBoot项目的登录验证方式想要使用JWT登录，而不是Cookie或者Session
#### 使用技术栈：SringBoot、Redis、JWT
#### 为什么使用JWT，而不是Cookie或者Session方式：
   ##### 使用Cookie的弊端：
   Cookie一般存放于浏览器端，如果有人用了你的电脑，则可以不用输入你的账号密码便可浏览你登陆的该网站的信息。
   ##### 使用Session：
   session存储在服务器端，但是如果并发人数多，该服务器需要同时处理的请求激增，为了分担激增的请求数量，新增了新的服务器，然后此时为了使得每台服务器都存储有同一个人的session，多台服务器直接要进行session同步，但是由代码同步这种方式效率低，所以可以将session存储到数据库，并且同步到redis中。
#### 实现流程：
    用户第一次请求时携带他的用户名和密码发送请求，服务器验证用户合法性并生成JWT令牌以及将该token存储到redis中并设置在redis中的过期时间，然后返回给客户端JWT令牌。客户端在下次请求时会向服务器发送该令牌，然后服务器做以下两件事：1、检验token合法性；2、在token合法的情况下检验redis看是否时间过期。
    用户登出：只需将服务器的redis对应的该token清除即可。
#### JWT令牌结构图
<img width="463" alt="image" src="https://github.com/xin2019/JWT/assets/47937067/7e1d1b83-4132-4d2c-9f09-2b94e3aabeb8">

