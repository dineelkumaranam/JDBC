#!/bin/sh
set -e
mkdir -p out
find src/main/java -name '*.java' > sources.txt
javac -cp 'lib/mysql-connector-j.jar' -d out @sources.txt
java -cp 'out:lib/mysql-connector-j.jar' com.studentmanagement.Main
