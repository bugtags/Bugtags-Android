#!/bin/bash
./gradlew -p app clean assembleDebug > bugtags.log

adb install -r app/build/outputs/apk/app-debug.apk
adb shell am start -n com.bugtags.demo/.MainActivity