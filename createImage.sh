#! /bin/bash

mvn clean install
cp target/TwitterAnalyzer.war dockerBuild/
cd dockerBuild
docker build -t minalbajaj/tweetify .
