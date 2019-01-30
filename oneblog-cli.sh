#!/bin/sh

###  项目配置  ####

# 项目名
NAME=OneBlog开源博客
# 项目根目录
BASE_DIR=oneblog
# 根目录
ROOT_PATH=/var/www
# 项目目录
PROJECT_PATH=${ROOT_PATH}/gitee/${BASE_DIR}
# 部署目录
DEPLOY_PATH=${ROOT_PATH}/site/${BASE_DIR}
# 项目远程仓库地址（gitee）
REMOTE_REP_URL=https://gitee.com/yadong.zhang/DBlog

###  打包相关配置  ###
ENV=test
PROFILE_PATH=/src/main/resources/application-${ENV}.yml
BASE_PROFILE_PATH=/src/main/resources/application.yml

####### 颜色代码 ########
## https://www.cnblogs.com/lr-ting/archive/2013/02/28/2936792.html ##
RED="31m"      # Error message
GREEN="32m"    # Success message
YELLOW="33m"   # Warning message
BLUE="36m"     # Info message

echoLog(){
	color="$1"
    echo -e "\033[${color} ${@:2} \033[0m"
}


echoSuccess(){
	echoLog ${GREEN} $1
}
echoWarning(){
    echoLog ${YELLOW} $1
}
echoInfo(){
    echoLog ${BLUE} $1
}
echoError(){
    echoLog ${RED} $1
}



useage(){
    echo -e "
Copyright @ https://www.zhyd.me . Current Version : v1.0.0

使用方法:
    sh oneblog-cli.sh [options] [modules]
    [options]
        install                : 安装
        update                 : 更新
    [modules]
        web                  : 前台程序(blog-web)
        admin                : 后台程序(blog-admin)
示例:
    sh oneblog-cli.sh install web
    sh oneblog-cli.sh update web
"
	echoWarning "注：如果脚本无法执行（报异常），请使用dos2unix命令编译（yum install dos2unix）"
    echoWarning "----"
	echoWarning "注：本工具不支持一键安装MySQL. 需自行安装MySQL/MariaDB,并创建名为dblog的数据库"
    exit 1
}

install(){
	# 项目类型： web | admin
	PROJECT_TYPE=$1
	MODULE_NAME=blog-${PROJECT_TYPE}
	
	echoWarning "注：本工具不支持一键安装MySQL. 需自行安装MySQL/MariaDB,并创建名为dblog的数据库"
	
	installMaven
	
	installJdk
	
	installGit
	
	# installMysql
    
	cloneSourceCode
    
    
	if  [ ! -n "$PROJECT_TYPE" && 'all' != "$PROJECT_TYPE" ] ;then
		# 只编译指定模块
		config ${PROJECT_TYPE} ${MODULE_NAME}
		build ${PROJECT_TYPE} ${MODULE_NAME}
	else
		# 全部编译
		config 'web' 'blog-web'
		config 'admin' 'blog-admin'
		
		build 'web' 'blog-web'
		build 'admin' 'blog-admin'
	fi
	
	
	
	echo -e "----------------------------------------------------"
	echoSuccess "Congratulations! \n ${NAME} 安装成功..."
	echoSuccess "使用systemctl start oneblog-[web|admin]启动"
	echoSuccess "使用systemctl enable oneblog-[web|admin]将oneblog-[web|admin]加入开机启动"
	echo -e "----------------------------------------------------"
}

installMaven(){
	read -p '是否安装maven？[y/n]' maven
	if [ "${maven}" = "y" -o "${maven}" = "Y" ];then
		wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
		yum install -y -q apache-maven
		echoSuccess "Maven安装完成，版本为: `mvn --version | head -1`"
	fi
}

installJdk(){
	read -p '是否安装OpenJDK1.8？[y/n]' jdk
	if [ "${jdk}" = "y" -o "${jdk}" = "Y" ];then
		yum install -y -q java-1.8.0-openjdk  java-1.8.0-openjdk-devel
		echoSuccess "OpenJDK安装完成，版本为: `java -version | head -1`"
	fi
}

installGit(){
	read -p '是否安装Git？[y/n]' git
	if [ "${git}" = "y" -o "${git}" = "Y" ];then
		yum install -y -q git
		echoSuccess "Git安装完成，版本为: `git --version | head -1`"
	fi
}

cloneSourceCode(){
	if [[ -d ${PROJECT_PATH} ]]; then
        rm -rf ${PROJECT_PATH}
    fi
    echo -e "----------------------------------------------------"
    echoInfo "正在下载${NAME}..."
    echo -e "----------------------------------------------------"
    git clone ${REMOTE_REP_URL} ${PROJECT_PATH}
}

installMysql(){
	rpm -Uvh http://dev.mysql.com/get/mysql-community-release-el7-5.noarch.rpm
	yum repolist enabled | grep "mysql.*"
	yum -y install mysql-community-server
    echoSuccess "MySQL版本为: `mysql -V | head -1`"
}

config() {

	PROJECT_TYPE=$1
	MODULE_NAME=$2
	
    echoInfo "开始配置 ${MODULE_NAME}"
    
	cd ${PROJECT_PATH}
	
	# 配置redis
    read -p 'Redis的密码(无密码请直接回车)：' -s redisPassword
	if [ -n "${redisPassword}" ]; then
		sed -i "72s/: /${redisPassword}/" ${PROJECT_PATH}/${MODULE_NAME}${BASE_PROFILE_PATH}
	else
		sed -i '72s/^[^#]/#&/' ${PROJECT_PATH}/${MODULE_NAME}${BASE_PROFILE_PATH}
	fi
	echo -e "\n"
	
	# 配置mysql
    read -p 'Mysql的用户名：' username
    read -p 'Mysql的密码：' -s password
	sed -i "11s/root/${username}/" ${PROJECT_PATH}/${MODULE_NAME}${PROFILE_PATH}
	sed -i "12s/root/${password}/" ${PROJECT_PATH}/${MODULE_NAME}${PROFILE_PATH}
	echo -e "\n"
	
	# 配置mail
	read -p '是否配置mail？[y/n]' mail
	if [ "${mail}" = "y" -o "${mail}" = "Y" ];then
		read -p 'host：' mailHost
		read -p 'username：' mailUsername
		read -p 'password：' -s mailPassword
		
		sed -i "19s/xxx/${mailHost}/" ${PROJECT_PATH}/${MODULE_NAME}${PROFILE_PATH}
		sed -i "22s/xxx/${mailUsername}/" ${PROJECT_PATH}/${MODULE_NAME}${PROFILE_PATH}
		sed -i "24s/xxx/${mailPassword}/" ${PROJECT_PATH}/${MODULE_NAME}${PROFILE_PATH}
		
		echo -e "\n"
	fi
	
	# 配置验证码
	read -p '是否启用验证码？[y/n]' kaptcha
	if [ "${kaptcha}" = "y" -o "${kaptcha}" = "Y" ];then
		sed -i "44s/false/true/" ${PROJECT_PATH}/${MODULE_NAME}${PROFILE_PATH}
	fi
	
    echoInfo "${MODULE_NAME} 配置完成 >> "
    echoInfo "----------------------------------------------------"
}

build(){

	PROJECT_TYPE=$1
	MODULE_NAME=$2
	echo -e "----------------------------------------------------"
	echoInfo "开始打包${MODULE_NAME}..."
	cd ${PROJECT_PATH}
	sh ${PROJECT_PATH}/build.sh ${ENV}
    STATUS=$?
    if [[ $STATUS == 0 ]]; then
        
        echoSuccess "${MODULE_NAME}打包成功..."
		
        mkdir -p ${DEPLOY_PATH}/${PROJECT_TYPE}
        cp -R ${MODULE_NAME}/target/${MODULE_NAME}.jar ${DEPLOY_PATH}/${PROJECT_TYPE}
        touch /etc/systemd/system/oneblog-${PROJECT_TYPE}.service
		cat > /etc/systemd/system/oneblog-${PROJECT_TYPE}.service << EOF
[Unit]
Description=oneblog-${PROJECT_TYPE}
After=network.target
Wants=network.target

[Service]
Type=simple
ExecStart=/usr/bin/java -server -Xms256m -Xmx512m -jar ${DEPLOY_PATH}/${PROJECT_TYPE}/blog-${PROJECT_TYPE}.jar > ${DEPLOY_PATH}/${PROJECT_TYPE}/blog-${PROJECT_TYPE}.log
ExecStop=/bin/kill -s QUIT $MAINPID
Restart=always
StandOutput=syslog

StandError=inherit

[Install]
WantedBy=multi-user.target
EOF
		systemctl daemon-reload
    else
        echoError "${MODULE_NAME}打包失败"
        exit 1
    fi
	echo -e "----------------------------------------------------"
}



if [[ $# > 0 ]]; then
	module="all"
	if  [ -n $2 ] ;then
		module=$2
	fi
    case $1 in 
        "install")
    install ${module}
    ;;
        "update")
    update ${module}
    ;;
        *)
    useage
    ;;
esac
else
    useage
fi
