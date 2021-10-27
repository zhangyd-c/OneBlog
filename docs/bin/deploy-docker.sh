#!/bin/bash

pwd=$(pwd)
echo ">> 当前路径：$pwd"

current_version=`cat $pwd/docs/bin/version.txt`
echo ">> 获取到当前版本：$current_version"

read -p '版本是否正确？如果正确请直接回车，否则输入正确的版本：' version
if [ "$version" ]; then
  current_version=${version}
  echo ">> 当前版本已更正为：$current_version"
fi

cd $pwd/blog-admin/ || exit
docker build -t justauth/blog-admin:v${current_version} .
docker push justauth/blog-admin:v${current_version}

cd $pwd/blog-web/ || exit
docker build -t justauth/blog-web:v${current_version} .
docker push justauth/blog-web:v${current_version}

read -p '是否需要重新构建 blog-mysql？[y/n]' repeat
if [ "${repeat}" = "y" -o "${repeat}" = "Y" ];then
  cd $pwd/docs/docker/mysql/ || exit
  docker build -t justauth/blog-mysql:v${current_version} .
  docker push justauth/blog-mysql:v${current_version}
fi

