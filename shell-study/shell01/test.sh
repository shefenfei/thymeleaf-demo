#!/usr/bin/env sh

echo "my first shell"

# 变量定义，它里面的所有变量都是String，一般的使用大写来定义变量名
NUM=10
DOCKER_FILE="Dockerfile"
echo ${DOCKER_FILE}
echo $DOCKER_FILE
echo $NUM

echo $((NUM+2))
echo $[NUM+3]

echo #this my empty line
/bin/pwd
/bin/ls
/bin/date

DATE=`date`
echo $DATE

env | grep NUM

