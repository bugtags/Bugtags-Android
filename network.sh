#!/bin/bash

BUILD_TYPE=$1

if [ -z "${BUILD_TYPE}" ]; then
    BUILD_TYPE="debug"
    else
    BUILD_TYPE="release"
fi

./gradlew -p network clean "assemble${BUILD_TYPE}"

adb install -r "network/build/outputs/apk/network-${BUILD_TYPE}.apk"
adb shell am start -n com.bugtag.networkapp/.MainActivity