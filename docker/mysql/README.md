# Dockerfile构建MySQL镜像

运行命令
```
docker run --name mysql -p 3307:3306 -v /home/jiang/mysql/bwton/data:/var/lib/mysql -v /home/jiang/mysql/bwton/db:/data/sql -e MYSQL_ROOT_PASSWORD=123456 -d mysql
```