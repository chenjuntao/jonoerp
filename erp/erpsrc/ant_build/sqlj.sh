#!/bin/sh
lib_dir="/home/cjt/code/jono/jonoerp/erp/erpsrc/WEB-INF/lib"
java -version
export JAVA_HOME=/opt/jdk1.7.0_80
export PATH=${JAVA_HOME}/bin:$PATH
java -version
java -cp ${lib_dir}/runtime12.jar:${lib_dir}/translator.jar:${lib_dir}/ojdbc6.jar sqlj.tools.Sqlj $@
