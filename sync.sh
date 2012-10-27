#!/bin/sh

while true ; do
rsync -r --exclude '.DS_Store' --exclude 'WEB-INF' --exclude 'META-INF' --exclude 'crossdomain.xml' src/main/webapp/ target/napkin-vld-0.0.1-SNAPSHOT/
sleep 1
done
