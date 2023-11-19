#!/bin/bash

echo "Current JAVA_HOME: "
echo $JAVA_HOME

export JAVA_HOME=$(/usr/libexec/java_home -v 17)

echo "Installed JAVA_HOME: "
echo $JAVA_HOME
