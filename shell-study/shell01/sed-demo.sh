#!/bin/bash

a=190
b=18

if [ $a > $b ]
then
    echo "a is equal to b"
fi

echo -e "Value of a is $a \n"

test='c:/windows/boot.ini'
echo ${test#/}

echo ${test#*/}

if [[ -f "agent.sample.config" ]];
then
    sed -e 's/SERVICE_NAME/' center-notice#*/
fi