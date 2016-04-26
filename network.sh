#!/bin/bash
./gradlew -p network clean assembleDebug > bugtags.log

adb install -r network/build/outputs/apk/network-debug.apk
adb shell am start -n com.bugtag.networkapp/.MainActivity