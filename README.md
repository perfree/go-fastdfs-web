## Go-Fastdfs web管理平台
> go-fastdfs 是一个简单的分布式文件存储，具有高性能，高可靠，免维护等优点，支持断点续传，分块上传，小文件合并，自动同步，自动修复。本项目为go-fastdfs的web管理端

[前往 Go-Fastdfs](https://github.com/sjqzhang/go-fastdfs)

## 简单预览图
![控制台](./screen/01.png)

![文件上传](./screen/02.png)

![文件列表](./screen/03.png)

![集群列表](./screen/04.png)

## 注意事项
1. 默认端口8088,修改默认端口号更改config/application.yml即可
2. 如go-fastdfs开启了按组管理,则需要填写组名,反之不用填写
3. 进入安装页填写集群地址时,该地址需要在go-fastdfs配置文件配置管理ip白名单,否则获取不到数据!
4. 文件列表功能需要go-fastdfs服务版本在v1.2.8以上
5. 遇到获取不到信息的功能,先试一下本地调用go-fastdfs接口看是否能获取到

## 运行步骤
首先需要安装java运行环境
[下载地址](https://github.com/perfree/go-fastdfs-web/releases)

### Docker安装运行
```shell script
docker run --name fastdfsweb -d -p 8088:8088 perfree/fastdfsweb
```

### Windows运行
解压压缩包后,直接运行start.bat
### Linux运行
解压压缩包后,运行脚本,命令如下:
```
1.运行
./goFastDfsWeb.sh start
2.查看运行状态
./goFastDfsWeb.sh status
3.重新启动
./goFastDfsWeb.sh restart
4.停止
./goFastDfsWeb.sh stop
```
如遇到-bash: ./goFastDfsWeb.sh: /bin/bash^M: 坏的解释器: 没有那个文件或目录错误,则执行以下命令再运行
```bash
sed -i 's/\r//' ./goFastDfsWeb.sh
```

## 开发说明
项目使用SpringBoot,Mybatis-Plus,Shiro进行开发,为方便后期用户安装,数据库采用Sqlite.

## 打包方式
1. maven运行mvn clean package
2. 完成之后得到jar,zip,tar.gz三种格式文件
## 支持
![微信](./screen/wechat.jpg)

![支付宝](./screen/alipay.jpg)
