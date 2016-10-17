Bugtags Android SDK
===================
[ ![Download](https://api.bintray.com/packages/bugtags/maven/bugtags-lib/images/download.svg) ](https://bintray.com/bugtags/maven/bugtags-lib/_latestVersion)
###帮助QQ群: 210286347

[Bugtags]，为移动测试而生，随时随地改善你的移动应用。只需一步，提交 bug 及其上下文数据，自动捕捉崩溃，让修复 bug 更简单。

[免费注册](http://bugtags.com/)，邀请你的团队成员来一起来改善你的app。

下载 demo app: [DEMO.apk](screenshot/demo.apk)

> 如果你使用 Eclipse 来开发 Android App, 访问: [SDK for Eclipse]下载 SDK.

# 功能
1. 一键截屏，使用标签进行 bug 描述.
2. 自动获取设备与 app 环境参数
3. 自动捕捉闪退 bug
4. bug生命周期管理

# 使用截屏
![如何使用](screenshot/usage.gif)

# 使用gradle安装集成

## 第一步：配置依赖

* 在项目的 build.gradle（项目根目录的 build.gradle 文件）设置 `buildscript dependencies` ：

    ```
    buildscript {
        ...

        dependencies {
            ...
            //**重要**
            classpath 'com.bugtags.library:bugtags-gradle:latest.integration'
        }
    }
    ```

* 在你的 Android app(com.android.application) 模块的 build.gradle 应用插件和添加依赖：

    ```
    android {
        compileSdkVersion ...

        defaultConfig {
            ndk {
                // 设置支持的 SO 库构架
                abiFilters 'armeabi'// 'armeabi-v7a', 'arm64-v8a', 'x86', 'x86_64', 'mips', 'mips64'
            }
        }
    }

    //应用 Bugtags 插件
    apply plugin: 'com.bugtags.library.plugin'

    //Bugtags 插件配置
    bugtags {
        //自动上传符号表功能配置，如果需要根据 build varint 配置，请参考插件详细使用说明
        appKey "APP_KEY"  //这里是你的 appKey
        appSecret "APP_SECRET"    //这里是你的 appSecret，管理员在设置页可以查看
        mappingUploadEnabled true

        //网络跟踪功能配置(企业版)
        trackingNetworkEnabled true
    }

    dependencies {
        ...
        compile 'com.bugtags.library:bugtags-lib:latest.integration'
    }
    ```

    如下图：

    ![Bugtags-Android-Studio](https://upload.bugtags.com/sdks/1606/07/gradle-config-57568242e2c36.png?bucket=bt-upload)

## 第二步：添加回调

* 在你的 `Activity 基类`（或所有的 Activity）中添加3个回调：

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
            //注：回调 1
            Bugtags.onResume(this);
        }

        @Override
        protected void onPause() {
            super.onPause();
            //注：回调 2
            Bugtags.onPause(this);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent event) {
            //注：回调 3
            Bugtags.onDispatchTouchEvent(this, event);
            return super.dispatchTouchEvent(event);
        }
    }
    ```

## 第三步：启动 SDK

* `继承 Application`，在 onCreate() 方法中初始化 Bugtags：

    ```
    public class MyApplication extends Application {
        @Override
        public void onCreate() {
            super.onCreate();
            //在这里初始化
            Bugtags.start("APP_KEY", this, Bugtags.BTGInvocationEventBubble);
        }
    }
    ```
* 修改 `AndroidManifest.xml`，使用 `MyApplication` 类,例如：

    ```
    <application
        android:name=".MyApplication"
        android:label="@string/app_name"
        android:theme="@style/AppTheme" >
        ....
    </application>
    ```

    关于如何使用 Android Studio 以及 gradle，请参考 bugtags 博客[系列文章](http://blog.bugtags.com/tags/EmbraceAndroidStudio/)。

## 第四步：ProGuard 混淆规则

```
# ProGuard configurations for Bugtags
  -keepattributes LineNumberTable,SourceFile

  -keep class com.bugtags.library.** {*;}
  -dontwarn com.bugtags.library.**
  -keep class io.bugtags.** {*;}
  -dontwarn io.bugtags.**
  -dontwarn org.apache.http.**
  -dontwarn android.net.http.AndroidHttpClient

  # End Bugtags
```

###编译运行 App，将会在 App 内部看到一个小球，成功了!###

# Change log

查看 [Android SDK 更新日志](https://docs.bugtags.com/zh/start/changelog/android.html)

# License
This demo is [BSD-licensed](LICENSE).