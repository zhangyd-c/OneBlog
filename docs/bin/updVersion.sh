#!/bin/bash

if [ ! -n "$1" ]; then
        echo "ERROR: 新版本不存在，请指定参数1"
        exit
fi

# 替换所有模块pom.xml中的版本
mvn versions:set -DnewVersion=$1

# 替换其它地方的版本
source $(pwd)/docs/bin/repVersion.sh "$1"
