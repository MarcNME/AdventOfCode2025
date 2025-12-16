#!/usr/bin/env sh
day=$1

cp ./src/main/kotlin/days/Day00.kt ./src/main/kotlin/days/$day.kt
#sed -i 's/Day00/$day/g' src/main/kotlin/days/$day.kt

#day_lower=echo $day | tr '[:upper:]' '[:lower:]'
#touch ./inputs/$day_lower.txt ./inputs/$day_lower_example.txt
# TODO: add day to Days in Day.kt