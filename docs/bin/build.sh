#!/bin/bash

mvn -X clean package -Dmaven.test.skip=true -Pdev
