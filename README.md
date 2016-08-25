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

see in [releases](https://github.com/bugtags/Bugtags-Android/releases)

# License
This demo is [BSD-licensed](LICENSE).