#!/bin/bash

pwd=$(pwd)
echo "当前路径：$pwd"

if [ -n "$1" ];then
    new_version="$1"
    old_version=`cat $pwd/docs/bin/version.txt`
    echo "$old_version 替换为新版本 $new_version"
else
    # 参数错误，退出
    echo "ERROR: 请指定新版本！"
    exit
fi

if [ ! -n "$old_version" ]; then
    echo "ERROR: 旧版本不存在，请确认docs/bin/version.txt中信息正确"
    exit
fi

# 替换README.md等文件中的版本
sed -i "s/${old_version}/${new_version}/g" $pwd/blog-core/src/main/resources/config/application-center.yml
sed -i "s/${old_version}/${new_version}/g" $pwd/README.md

# 替换pom.xml中的版本
sed -i "s/${old_version}/${new_version}/g" $pwd/pom.xml

# 保留新版本号
echo "$new_version" > $pwd/docs/bin/version.txt
