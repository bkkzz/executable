#!/bin/bash

# jar包依赖路径
EXT_DIR=$JAVA_HOME/jre/lib/ext:$JCLI_HOME/lib/ext

java -Djava.ext.dirs=$EXT_DIR -jar $JAR_FILE $@
