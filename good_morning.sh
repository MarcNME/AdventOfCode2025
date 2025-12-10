#!/usr/bin/env sh
day=$1

cp ./src/main/kotlin/days/Day00.kt ./src/main/kotlin/days/$1.kt
# TODO: Rename class
touch ./inputs/$1.txt ./inputs/$1_example.txt
# TODO: add day to Days in Day.kt