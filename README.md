[![Android Gems](http://www.android-gems.com/badge/bugtags/Bugtags-Android.svg?branch=master)](http://www.android-gems.com/lib/bugtags/Bugtags-Android)

Bugtags Android SDK
===================
[ ![Download](https://api.bintray.com/packages/bugtags/maven/bugtags-lib/images/download.svg) ](https://bintray.com/bugtags/maven/bugtags-lib/_latestVersion)
###中文文档请移步 [README_CN](README_CN.md)
###QQ tribe for help: 210286347

[Bugtags] for Android, reports bugs and their diagnosis information in one step, captures crashes automatically. Improve your apps anywhere, anytime.

[Create a free account](http://bugtags.com/) and invite your team to improve your apps.

Download demo app here: [DEMO.apk](screenshot/demo.apk)

> If you are using Eclipse for Android development, visit [SDK for Eclipse] to download SDK.

> Bugtags also support [iOS](https://github.com/bugtags/Bugtags-iOS) !

> We are going to support English language in September.

# Features
1. Take snapshot of bug, add tags to describe the bug.
2. Automatically collect device and app context data following reporting bugs.
3. Automatically capture crashes.
4. Bug lifecycle management.

# Usage
![How to use](screenshot/usage.gif)

# Install using gradle

## Step 1:
* Setup `buildscript dependencies`  in Top-level build.gradle file:

```groovy
buildscript {
    repositories {
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:1.2.3'

        classpath 'com.bugtags.library:bugtags-gradle:latest.integration'
    }
}
allprojects {
    repositories {
        jcenter()
        mavenCentral()
    }
}
```

* Add `plugin and dependency` in your module's build.gradle file：

```groovy
    android {
        compileSdkVersion ...

        defaultConfig {
            ndk {
                // setup so arch
                abiFilters 'armeabi'// 'armeabi-v7a', 'arm64-v8a', 'x86', 'x86_64', 'mips', 'mips64'
            }
        }
    }

    //applu Bugtags plugin
    apply plugin: 'com.bugtags.library.plugin'

    //Bugtags config
    bugtags {
        //upload mapping file
        appKey "APP_KEY"  
        appSecret "APP_SECRET"   
        mappingUploadEnabled true

        trackingNetworkEnabled true
    }

    dependencies {
        ...
        compile 'com.bugtags.library:bugtags-lib:latest.integration'
    }
```

## Step 2:
* Add three callbacks in your base Activity class:

```
    package your.package.name;
    import android.app.Activity;
    import android.os.Bundle;
    import android.view.MotionEvent;
    import com.bugtags.library.Bugtags;

    public class BaseActivity extends Activity{
        @Override
        protected void onResume() {
            super.onResume();

            Bugtags.onResume(this);
        }

        @Override
        protected void onPause() {
            super.onPause();

            Bugtags.onPause(this);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent event) {
            Bugtags.onDispatchTouchEvent(this, event);

            return super.dispatchTouchEvent(event);
        }
    }
    ```

## Step 3:
* Create subclass of Application，initialize Bugtags in onCreate() method:

```java
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //initialize here
        Bugtags.start("YOUR APPKEY", this, Bugtags.BTGInvocationEventBubble);
    }
}
```

* Modify AndroidManifest.xml，use MyApplication:

```xml
<application
    android:name=".MyApplication"
    android:label="@string/app_name"
    android:theme="@style/AppTheme" >
    ....
</application>
```
## Step 4：ProGuard

```
    # ProGuard configurations for Bugtags
    -keepattributes LineNumberTable,SourceFile

    -keep class com.bugtags.library.** {*;}
    -dontwarn org.apache.http.**
    -dontwarn android.net.http.AndroidHttpClient
    -dontwarn com.bugtags.library.**
    # End Bugtags
```

  For more information about Android Studio and gradle, please visit: [Android Developer Site].

## There you go!

# Explore
1. Invoke event:
  * BTGInvocationEventBubble: Show floating circle in app.
  * BTGInvocationEventShake: Show floating circle by shake.
  * BTGInvocationEventNone: Show no floating circle, capture crash bug only(if allow), this is recommended to be used in release build.
2. Send caught exception:
  * Bugtags.sendException(Throwable ex);
3. Send feedback:
  * Bugtags.sendFeedback(String msg);

# Canary Channel
We are offering a bleeding edge builds on canary chanel, you can enjoy the new features in the first place!

> Canry: https://en.wikipedia.org/wiki/Canary

* Add repository in your project's build.gradle

```groovy
buildscript {
    repositories {
        mavenCentral()
        jcenter()
        maven{
            url "https://dl.bintray.com/bugtags/maven"//added
        }
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:1.3.0'
        classpath 'com.bugtags.library-canary:bugtags-gradle:latest.integration'//modify
    }
}
allprojects {
    repositories {
        jcenter()
        mavenCentral()
        maven{
            url "https://dl.bintray.com/bugtags/maven"//added
        }
    }
}
  ```

* Change your dependency in your module's build.gradle

```groovy
apply plugin: 'com.bugtags.library.plugin'

dependencies {
      compile 'com.bugtags.library-canary:bugtags-lib:latest.integration'//modify
}
```

# Change log
### 1.3.1: August 10, 2016
- Fix: Fixed rare OOM exception on resending cached issues.
- Enhancement: Set Bugtags.log max lines limit to 1000.
- Enhancement: Upgrade cache system.
- Fix: Fixed issue that user logo is invisible after sign in.
- Enhancement: Optimize initialization process, add start option `startAsync` and `startCallback`, please refer: [API Reference](https://docs.bugtags.com/zh/api/android/index.html).
- Feature: Added quick sign in, account that had signed in will be save and list, click item to sign in, long click to delete, still work after uninstall and re-install.
- Feature: Added new start option: `enableUserSignIn`, you can set this flag according to your requirement.
- Feature: Added plugin system,please refer: [API Reference](https://docs.bugtags.com/zh/api/android/index.html).
- Feature: Added first official plugin: [BugtagsInsta](https://docs.bugtags.com/zh/bugtagsinsta/index.html) supports real-time tracking.
- Fix: Fixed issue related to external storage permission.

### 1.2.7: June 7, 2016

- Fix: Fixed issue that stop tracking userstep doesn't take effects.
- Fix: Fixed rare issue that location will be null.
- Fix: Fixed readable log for http request error.
- Enhancement: Malformed appKey checking.
- Enhancement: Encrypted appKey.

### 1.2.6: May 16, 2016

- Fix: Fixed bug in crash report, battery level is inaccurate.
- Fix: Fixed bug in network tracking when content-type is null.
- Fix: Fixed bug in logo display after login.
- Feature: Added auto proguard mapping upload plugin, support different appKey for every build variant.
- Minor bug fixes.

### 1.2.5: April 26, 2016

- Fix: Fixed bug related to tracking in httpurlconnection.
- Enhancement: Improved overlay permission granted logic in Andoid 6.0, only requested in needed.
- Fix: Fixed rare bug that bubble invisible after splash page.

### 1.2.4: April 14, 2016

- Fix: Fixed bug related to network tracking in okhttp.
- Fix: Fixed bug bug in screenshot on devices with soft navigation bar.
- Fix: Catch exception that might happen in screenshot.
- Fix: Tuned timeout for data upload, to prevent error in bad quality network.
- Fix: Fixed ssl error happen in sending data via https.

### 1.2.1: March 30, 2016

- Enhancement: Supported `okhttp3` network request tracking.
- Enhancement: Supported `loopj/android-async-http` network request tracking.
- Enhancement: Added `uploadDataOnlyViaWiFi` start option, allow only send in WiFi network.
- Enhancement: Added `currentInvocationEvent` API, accessing the current invocation mode.
- Minor bug fixes.

### 1.2.0: March 12, 2016

- Enhancement: Network request tracking(support HTTP / HTTPS protocol)，disable by default, set `trackingNetworkEnabled true` to open, please refer:[network-demo](network).
- Enhancement: Retrieve bubble mode from server configuration.
- Fix: Fixed assignee list retrieving error.
- Fix: Fixed rotation make bubble disappear.
- Fix: Fixed issue that ui appearance not changed after priority set.
- Minor bug fixes.

### 1.1.2: March 9, 2016

- Fix: Fixed rare issue that bubble disappears
- Fix: Fixed ineffective sslv3 solution in v1.1.1
- Minor bug fixes.

### 1.1.1: February 20, 2016

- Compatibility: Java 1.6 suported.
- Enhancement: Removed phone permission.
- Fix: Fixed sslv3 protocol issue.
- Minor bug fixes.

### 1.1.0: January 6, 2016

- Feature: Supported `cocos2d-x game` screenshot(`need to build package by gradle`).
- Feature: Added `callback before and after sending issue`.
- Feature: Added `manually invoke screenshot`.
- Fix: Fixed competing thread bug on resending issue.
- Minor bug fixes.

### 1.0.9: December 6, 2015

- Fix: Fixed issue related to user step timestamp, with better layout.
- Fix: Fixed issue that some customized android ROM's missing sdcard.
- Change reference to weak reference, `prevent potential memory leak`.

### 1.0.8: November 19, 2015

- Enhancement: Screenshot with `toast and dialog`.
- Enhancement: Performance improving.

### 1.0.7: November 6, 2015

- Feature: Added API for `custom version name & version code`.
- Minor bug fixes.

### 1.0.6: October 24, 2015

- Enhancement: Supported `tartgetSdkVersion=23(Android M, 6.0)`.
- Feature: Long-press on "+" button to `restart logging`.
- Feature: Supported anonymous report options.
- Enhancement: Improved crash collecting policy, `disable when debugger connected`.
- Enhancement: Added CPU architecture for device information.
- Enhancement: Improved console log policy.
- Enhancement: Supported `uses-permission customization`.
- Feature: Start options for crashWithScrenshot.

### 1.0.5: September 29, 2015

- Enhancement: `Crash with photo`, start options.
- Enhancement: Performance improving.
- Minor bug fixes.

### 1.0.4: September 3, 2015

- Enhancement: Performance improving.

### 1.0.3: August 26, 2015

- Feature: Sending progress.
- Enhancement: Simplified dependency.
- Enhancement: Improved integration.

### 1.0.2: August 20, 2015

- Enhancement: Performance improving.

### 1.0.1: August 15, 2015

- Minor bug fixes.

### 1.0.0: August 7, 2015

- Official release.

### 0.9.0: August 1, 2015

- Pre-release.

# License
This demo is [BSD-licensed](LICENSE).

# Links
[SDK for Eclipse]:https://github.com/bugtags/Bugtags-Android-Eclipse

[Bugtags]:http://bugtags.com

[Android Developer Site]:http://developer.android.com/tools/studio/index.html
