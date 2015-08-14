Bugtags Android SDK
===================
###帮助QQ群: 479166560
[Bugtags]，为移动测试而生，随时随地改善你的移动应用。只需一步，提交bug及其上下文数据，自动捕捉崩溃，让修复bug更简单。

[免费注册](http://bugtags.com/)，邀请你的团队成员来一起来改善你的app。
> 如果你使用Eclipse来开发Android App, 访问: [SDK for Eclipse]下载SDK.

# 功能
1. 一键截屏，使用标签进行bug描述.
2. 自动获取设备与app环境参数
3. 自动捕捉闪退bug
4. bug生命周期管理

# 安装集成:
### Gradle
* 1.SDK已经上传到Maven Central仓库， 在你项目的build.gradle 文件中添加以下依赖, 同步gradle，就会自动下载SDK：
```gradle
compile 'com.bugtags.library:bugtags-lib:1.0.1'
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
  *也可以在你的Activity中手动添加回调，请参考：CustomActivity:*

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
    关于如何使用Android Studio以及gradle，请参考：[Android Developer Site].

# 使用截屏:
![如何使用](screenshot/usage.gif)

# 高级选项:
1. 呼出方式Invoke event:
  * BTGInvocationEventBubble: 通过悬浮小球呼出Bugtags。
  * BTGInvocationEventShake: 通过摇一摇呼出Bugtags。
  * BTGInvocationEventNone: 不显示悬浮小球，只收集崩溃信息（如果允许）。
2. 手动发送Exception:
  * Bugtags.sendException(Throwable ex);
3. 发送文字反馈信息:
  * Bugtags.sendFeedback(String msg);

[SDK for Eclipse]:https://github.com/bugtags/Bugtags-Android-Eclipse
[Bugtags]:http://bugtags.com
[Android Developer Site]:http://developer.android.com/tools/studio/index.html
