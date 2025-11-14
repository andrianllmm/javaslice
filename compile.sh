#!/bin/bash
mkdir -p bin

find src -name "*.java" > sources.txt
javac -d bin @sources.txt
rm sources.txt

echo "Compilation complete"
