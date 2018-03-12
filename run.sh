#!/bin/sh

rm -f out/*.class
find -name "*.java" > sources.txt
javac -d out/ @sources.txt
java -cp out/ ContactManager
