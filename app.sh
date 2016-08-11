#!/bin/bash

BUILD_TYPE=$1

if [ -z "${BUILD_TYPE}" ]; then
    BUILD_TYPE="debug"
    else
    BUILD_TYPE="release"
fi

./gradlew -p app clean "assemble${BUILD_TYPE}"

adb install -r "app/build/outputs/apk/app-${BUILD_TYPE}.apk"
adb shell am start -n com.bugtags.demo/.MainActivity