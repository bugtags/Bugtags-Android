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
        //**重要**
        classpath 'com.bugtags.library:bugtags-gradle:latest.integration'
    }
}
allprojects {
    repositories {
        jcenter() //注：repository 1
        mavenCentral()  //注：repository 2
    }
}
```

* Add `plugin and dependency` in your module's build.gradle file：

```groovy
apply plugin: 'com.bugtags.library.plugin'

//mapping upload
bugtags {
    appKey 'APP_KEY'  //your appKey
    appSecret 'APP_SECRET'    //your appSecret，admin can access in setting page
}

dependencies {
    compile 'com.bugtags.library:bugtags-lib:latest.integration'
}
```

> The latest version is: **1.1.1**, you can also add dependency with specific version:

> compile 'com.bugtags.library:bugtags-lib:1.1.1'


## Step 2:
* Add three callbacks in your base Activity class:

```java
package your.package.name;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.bugtags.library.Bugtags;

public class CustomActivity extends Activity {
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
### 2016.03.12 v1.2.0
- network request tracking（support HTTP / HTTPS protocol），diable by default, set `trackingNetworkEnabled true` to open
- retrieve buble mode from server configuration
- fix issue: assignee list retrieving error
- fix issue: rotation make bubble disappear
- fix issue: ui appearance after priority set
- other bug fix

### 2016.03.09 v1.1.2
- fix issue: under some circumstance bubble disappears 
- fix ineffective sslv3 solution in v1.1.1
- other bug fix

### 2016.02.20 v1.1.1

- Java 1.6 compatibility
- remove phone permission
- fix sslv3 protocol issue
- other bug fix

### 2016.01.06 v1.1.0

- support `cocos2d-x game` screenshot(`need to build package by gradle`)
- add `callback before and after sending issue`
- add `manually invoke screenshot`
- fix competing thread bug on resending issue
- other bug fix

### 2015.12.05 v1.0.9

- bugfix for user step timestamp, better layout
- bugfix for some customized android ROM's missing sdcard
- change reference to weak reference, `prevent potential memory leak`

### 2015.11.19 v1.0.8

- screenshot with `toast and dialog`
- performance improving

### 2015.11.06 v1.0.7

- add api for `custom version name & version code`
- bug fix

### 2015.10.24 v1.0.6

- support `tartgetSdkVersion=23(Android M, 6.0)`
- long-press on "+" button to `restart logging`
- support anonymous report options
- improve crash collecting policy, `disable when debugger connected`
- add CPU architecture for device information
- fix console log policy
- support `uses-permission customization`
- start options for crashWithScrenshot

### 2015.09.29 v1.0.5

- `crash with photo`, start options,
- bug fix
- performance improving

### 2015.09.03 v1.0.4

- performance improving

### 2015.08.26 v1.0.3

- send progress
- simplify dependency
- improve integration

### 2015.08.20 v1.0.2

- performance improving

### 2015.08.15 v1.0.1

- bug fix

### 2015.08.07 v1.0.0

- official release

### 2015.08.01 v0.9.0

- pre-release

# License
This demo is [BSD-licensed](LICENSE).

[SDK for Eclipse]:https://github.com/bugtags/Bugtags-Android-Eclipse
[Bugtags]:http://bugtags.com
[Android Developer Site]:http://developer.android.com/tools/studio/index.html
