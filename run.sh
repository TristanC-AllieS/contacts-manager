#!/bin/sh

rm -f out/*.class
javac -d out/ src/*
java -cp out/ Main