#!/bin/bash

# 安装目录
INSTALL_HOME=/usr/local/jcli

if [ -d $INSTALL_HOME ];then
  rm -r $INSTALL_HOME/*
else
  mkdir $INSTALL_HOME
fi

cp -r ./bin $INSTALL_HOME
cp -r ./lib $INSTALL_HOME

echo "Install Success"
