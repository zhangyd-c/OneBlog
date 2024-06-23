#!/bin/bash

help(){
  echo "--------------------------------------------------------------------------"
  echo ""
  echo "usage: ./cli.sh [updv]"
  echo ""
  echo "-updv [version num]   Update all oneblog related versions."
  echo ""
  echo "--------------------------------------------------------------------------"
}

case "$1" in
  'updv')
    docs/bin/updVersion.sh $2
	;;
  'b')
    docs/bin/build.sh
	;;
  'p')
    docs/bin/push.sh
	;;
  'dd')
    read -p '是否重新执行 mvn package？[y/n]' repeat
    if [ "${repeat}" = "y" -o "${repeat}" = "Y" ];then
      mvn -X clean package -Dmaven.test.skip=true
      STATUS=$?
      if [[ $STATUS == 0 ]]; then
        docs/bin/deploy-docker.sh
      else
        echoError "打包失败"
        exit 1
      fi
    else
      docs/bin/deploy-docker.sh
    fi

	;;
  *)
    help
esac
