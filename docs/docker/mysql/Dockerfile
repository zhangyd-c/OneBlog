# FROM mysql:8.0.20
FROM mysql:5.7

MAINTAINER yadong.zhang0415@gmail.com

ENV TZ=Asia/Shanghai

ADD ./config/mysqld.cnf /etc/mysql/mysql.conf.d/mysqld.cnf

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY ./dblog.sql /docker-entrypoint-initdb.d

COPY ./init_data.sql /docker-entrypoint-initdb.d
