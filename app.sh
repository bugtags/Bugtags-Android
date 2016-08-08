#!/bin/bash

usage(){ echo "Usage: $0 [r]" 1>&2; exit 1; }

BUILD_TYPE=$1

if [ -z "${BUILD_TYPE}" ]; then
    BUILD_TYPE="debug"
    else
    BUILD_TYPE="release"
fi

first=`echo $BUILD_TYPE|cut -c1|tr [a-z] [A-Z]`
second=`echo $BUILD_TYPE|cut -c2-`

./gradlew -p app clean "assemble$first$second"

adb install -r "app/build/outputs/apk/app-${BUILD_TYPE}.apk"
adb shell am start -n com.bugtags.demo/.MainActivity