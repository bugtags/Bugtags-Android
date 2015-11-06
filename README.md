[![Android Gems](http://www.android-gems.com/badge/bugtags/Bugtags-Android.svg?branch=master)](http://www.android-gems.com/lib/bugtags/Bugtags-Android)

Bugtags Android SDK
===================
[ ![Download](https://api.bintray.com/packages/bugtags/maven/bugtags-lib/images/download.svg) ](https://bintray.com/bugtags/maven/bugtags-lib/_latestVersion)
###中文文档请移步[README_CN](README_CN.md)
###QQ tribe for help: 479166560

[Bugtags] for Android, reports bugs and their diagnosis information in one step, captures crashes automatically. Improve your apps anywhere, anytime.

[Create a free account](http://bugtags.com/) and invite your team to improve your apps.

Download demo app here: [DEMO.apk](screenshot/demo.apk)

> If you are using Eclipse for Android development, visit [SDK for Eclipse] to download SDK.

> We are open for registration now!

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
* Setup repositories in Top-level build.gradle file:

```gradle
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:1.2.3'
    }
}
allprojects {
    repositories {
        jcenter() //repository 1
        mavenCentral()  //repository 2
    }
}
```
* Add dependency in your module's build.gradle file：

```gradle
dependencies {
    compile 'com.bugtags.library:bugtags-lib:latest.integration'
}
```
> The latest version is: **1.0.7**, you can also add dependency with specific version:

> compile 'com.bugtags.library:bugtags-lib:1.0.7'


## Step 2:
* Add three callbacks in your base Activity class:
```java
      package your.package.name;

      import android.app.Activity;
      import android.os.Bundle;
      import android.view.MotionEvent;

      import com.bugtags.library.Bugtags;

      public class CustomActivity extends Activity{
          @Override
          protected void onResume() {
              super.onResume();
              //callback 1
              Bugtags.onResume(this);
          }

          @Override
          protected void onPause() {
              super.onPause();
              //callback 2
              Bugtags.onPause(this);
          }

          @Override
          public boolean dispatchTouchEvent(MotionEvent event) {
              //callback 3
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
```gradle
maven {
    url 'https://dl.bintray.com/bugtags/maven'
}
```
* Change your dependency in your module's build.gradle
```gradle
    compile 'com.bugtags.library-canary:bugtags-lib:latest.integration'
```

# Change log
2015.11.06    1.0.7
- add api for custom version name & version code
- bug fix

2015.10.24    1.0.6
- support tartgetSdkVersion=23(Android M, 6.0)
- long-press on "+" button to restart logging
- support anonymous report options
- improve crash collecting policy, disable when debugger connected
- add CPU architecture for device information
- fix console log policy
- support uses-permission customization
- start options for crashWichScrenshot

2015.09.29    1.0.5    crash with photo, start options, bug fix, performance improving

2015.09.03    1.0.4    performance improving

2015.08.26    1.0.3    send progress, simplify dependency, improve integration

2015.08.20    1.0.2    performance improving

2015.08.15    1.0.1    bug fix

2015.08.07    1.0.0    official release

2015.08.01    0.9.0    pre-release

# License
This demo is [BSD-licensed](LICENSE).

[SDK for Eclipse]:https://github.com/bugtags/Bugtags-Android-Eclipse
[Bugtags]:http://bugtags.com
[Android Developer Site]:http://developer.android.com/tools/studio/index.html
