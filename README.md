Bugtags Android SDK
===================
[Bugtags] for Android, reports bugs and their diagnosis information in one step, captures crashes automatically.

[Create a free account](http://bugtag.com/) and invite your team to improve your apps.
> If you are using Eclipse for Android development, visit [SDK for Eclipse] to download SDK.

# Installation:
-----


### Gradle
* 1.The SDK is available through Maven Central. In your build.gradle file, add the following dependency, then sync your gradle files:
```gradle
compile 'com.bugtags.library:bugtags-lib:0.9.0'
```
* 2.Then initialize Bugtags in your application’s onCreate() method:
```java
Bugtags.start("YOUR APPKEY", this, Bugtags.BTGInvocationEventBubble);
```
* 3.Change your base activity to extend one of the following activities, to enable automatically tracking user steps:
```java
 BugtagsAppCompatActivity: This extends android.support.v7.app.AppCompatActivity
 BugtagsActionBarActivity: This extends android.support.v7.app.ActionBarActivity
 BugtagsActivity: This extends android.app.activity
 BugtagsFragmentActivity: This extends android.support.v4.app.FragmentActivity
```
  or invoke the callbacks manually, see in [CustomActivity](#customactivity).

  For more information about Android Studio and gradle, please visit: [Android Developer Site].


### Eclipse
1. Download the [SDK for Eclipse] and add it to your workspace then add it as a dependency in your application's project. The eclipse project requires the following library dependencies:

  ```
  Android v4 support library
  Android v7 app compat support library
  ```
2. Request the following permissions in your AndroidManifest.xml:

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
3. add activities and service in AndroidManifest.xml:

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
4. Add the following init call to your application's onCreate() method:

  ```java
    Bugtags.start("YOUR APPKEY", this, Bugtags.BTGInvocationEventBubble);
  ```
5. Change your base activity to extend one of the following activities, to enable automatically tracking user steps:
```java
 BugtagsAppCompatActivity: This extends android.support.v7.app.AppCompatActivity
 BugtagsActionBarActivity: This extends android.support.v7.app.ActionBarActivity
 BugtagsActivity: This extends android.app.activity
 BugtagsFragmentActivity: This extends android.support.v4.app.FragmentActivity
```
or invoke the callbacks manually, see in: [CustomActivity](#customactivity).

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
