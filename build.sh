#!/bin/bash

profileActive=prod
if [ -n "$1" ]; then
    profileActive=$1
fi

mvn -X clean package -Dmaven.test.skip=true -P$profileActive