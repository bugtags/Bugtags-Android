Bugtags Android SDK
===================
###中文文档请移步[README_CN](README_CN.md)
###QQ tribe for help: 479166560

[Bugtags] for Android, reports bugs and their diagnosis information in one step, captures crashes automatically. Improve your apps anywhere, anytime.

[Create a free account](http://bugtags.com/) and invite your team to improve your apps.
> If you are using Eclipse for Android development, visit [SDK for Eclipse] to download SDK

# Features
1. Take snapshot of bug, add tags to describe the bug.
2. Automatically collect device and app context data following reporting bugs.
3. Automatically capture crashes.
4. Bug lifecycle management.

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

# Usage
![How to use](screenshot/usage.gif)

# Explore
1. Invoke event:
  * BTGInvocationEventBubble: Show floating circle in app.
  * BTGInvocationEventShake: Show floating circle by shake.
  * BTGInvocationEventNone: Show no floating circle, capture crash bug only(if allow), this is recommended to be used in release build.
2. Send caught exception:
  * Bugtags.sendException(Throwable ex);
3. Send feedback:
  * Bugtags.sendFeedback(String msg);

# License
This demo is [BSD-licensed](LICENSE). 

[SDK for Eclipse]:https://github.com/bugtags/Bugtags-Android-Eclipse
[Bugtags]:http://bugtags.com
[Android Developer Site]:http://developer.android.com/tools/studio/index.html
