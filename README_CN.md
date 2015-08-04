Bugtags Android SDK
===================
###帮助QQ群: 479166560
[Bugtags]，为移动测试而生，随时随地改善你的移动应用。只需一步，提交bug及其上下文数据，自动捕捉崩溃，让修复bug更简单。

[免费注册](http://bugtags.com/)，邀请你的团队成员来一起来改善你的app。
> 如果你使用Eclipse来开发Android App, 访问: [SDK for Eclipse]下载SDK.

# 安装集成:
-----
### Gradle
* 1.SDK已经上传到Maven Central仓库， 在你项目的build.gradle 文件中添加以下依赖, 同步gradle，就会自动下载SDK：
```gradle
compile 'com.bugtags.library:bugtags-lib:0.9.0'
```
* 2.在你的Application的onCreate() 方法中初始化Bugtags：
```java
Bugtags.start("YOUR APPKEY", this, Bugtags.BTGInvocationEventBubble);
```
* 3.让你的Activity中继承以下Activity，即可自动跟踪用户步骤：
```java
  BugtagsAppCompatActivity: 继承自 android.support.v7.app.AppCompatActivity
  BugtagsActionBarActivity: 继承自android.support.v7.app.ActionBarActivity
  BugtagsActivity: 继承自android.app.activity
  BugtagsFragmentActivity: 继承自android.support.v4.app.FragmentActivity
```
  也可以在你的Activity中手动添加回调，请参考：[CustomActivity](#customactivity).

  关于如何使用Android Studio以及gradle，请参考：[Android Developer Site].


### Eclipse
1. [下载SDK](https://github.com/bugtags/Bugtags-Android-Eclipse)并添加到你的工作空间，然后将其添加为你的应用程序的项目的依赖。 Eclipse项目需要以下库的依赖关系：

  ```
  Android v4 support library
  Android v7 app compat support library
  ```
2. 在 AndroidManifest.xml中添加以下权限：

  ```xml
  <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
  <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
  <uses-permission android:name="android.permission.INTERNET"/>
  <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
  <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
  <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
  <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
  <uses-permission android:name="android.permission.READ_LOGS"/>
  <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
  <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
  ```
3. 在AndroidManifest.xml中添加所需的activity和服务：

  ```xml
    <activity android:name="com.bugtags.library.BugtagsActivity"
              android:configChanges="keyboardHidden|orientation|screenSize"
              android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen"/>
  <service android:name="com.bugtags.library.BugtagsService"/>
  <receiver android:name="com.bugtags.library.BugtagsReceiver">
              <intent-filter>
                  <action android:name="android.net.conn.CONNECTIVITY_CHANGE"/>
              </intent-filter>
  </receiver>
  ```
4. 在你的Application的onCreate() 方法中初始化Bugtags：

  ```java
    Bugtags.start("YOUR APPKEY", this, Bugtags.BTGInvocationEventBubble);
  ```
5. 让你的Activity中继承以下Activity, 则可自动跟踪用户步骤：
```java
 BugtagsAppCompatActivity: This extends android.support.v7.app.AppCompatActivity
 BugtagsActionBarActivity: This extends android.support.v7.app.ActionBarActivity
 BugtagsActivity: This extends android.app.activity
 BugtagsFragmentActivity: This extends android.support.v4.app.FragmentActivity
```
也可以在你的Activity中手动添加回调，请参考：[CustomActivity](#customactivity).

##	CustomActivity
```java
      package your.package.name;
      import android.app.Activity;
      import android.os.Bundle;
      import android.view.MotionEvent;

      import com.bugtags.library.Bugtags;

      public class CustomActivity extends Activity{
          @Override
          protected void onCreate(Bundle savedInstanceState) {
              super.onCreate(savedInstanceState);
              Bugtags.onCreate(this);
          }

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
          protected void onDestroy() {
              super.onDestroy();
              Bugtags.onDestroy(this);
          }

          @Override
          public boolean dispatchTouchEvent(MotionEvent event) {
              Bugtags.onDispatchTouchEvent(this, event);
              return super.dispatchTouchEvent(event);
          }
      }
```

[SDK for Eclipse]:https://github.com/bugtags/Bugtags-Android-Eclipse
[Bugtags]:http://bugtags.com
[Android Developer Site]:http://developer.android.com/tools/studio/index.html
