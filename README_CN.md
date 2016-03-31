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

### 第一步：配置依赖

* Bugtags SDK 已经同步到 jcenter 和 MavenCentral，请在项目的 build.gradle（项目最外层的 build.gradle 文件，所谓的 Top-level build file）设置 `buildscript dependencies` ：

```
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

* 在模块的 build.gradle `应用插件和添加依赖`：

```
//应用插件
apply plugin: 'com.bugtags.library.plugin'

//自动上传插件
bugtags {
    appKey 'APP_KEY'  //这里是你的 appKey
    appSecret 'APP_SECRET'    //这里是你的 appSecret，管理员在设置页可以查看
}

dependencies {
    compile 'com.bugtags.library:bugtags-lib:latest.integration'
}
```

### 第二步：添加回调

在你的 `Activity 基类`（或所有的 Activity）中添加3个回调：

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

### 第三步：启动 SDK

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

##### 编译运行 App，将会在 App 内部看到一个小球，成功了!

# 高级选项
1. 呼出方式 Invoke event:
  * BTGInvocationEventBubble: 通过悬浮小球呼出Bugtags。
  * BTGInvocationEventShake: 通过摇一摇呼出Bugtags。
  * BTGInvocationEventNone: 不显示悬浮小球，只收集崩溃信息（如果允许）。
2. 手动发送 Exception:
  * Bugtags.sendException(Throwable ex);
3. 发送文字反馈信息:
  * Bugtags.sendFeedback(String msg);

# Canary 发布渠道
我们在canary渠道发布具有最新特性的版本，欢迎喜欢尝新的用户使用这个版本！
> Canary: 金丝雀，早期旷工探洞，使用金丝雀来侦查环境，寓意勇敢尝试新特性。

> From youdao dic: http://dict.youdao.com/search?q=canary&keyfrom=dict.index

* 在project的build.gradle添加repository

```
buildscript {
    repositories {
        mavenCentral()
        jcenter()
        maven{
            url "https://dl.bintray.com/bugtags/maven"//新增
        }
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:1.3.0'
        classpath 'com.bugtags.library-canary:bugtags-gradle:latest.integration'//修改
    }
}
allprojects {
    repositories {
        jcenter()
        mavenCentral()
        maven{
            url "https://dl.bintray.com/bugtags/maven"//新增
        }
    }
}
```

* 在 module 的 build.gradle 更改 dependency

```
apply plugin: 'com.bugtags.library.plugin'

dependencies {
      compile 'com.bugtags.library-canary:bugtags-lib:latest.integration'//修改
}
```

# Change log
### 2016.03.31 v1.2.2
- 移除一个未使用的远程包依赖

### 2016.03.30 v1.2.1
- 增加对 `okhttp3` 的网络请求跟踪的支持
- 增加对 `loopj/android-async-http` 的网络请求跟踪的支持
- 增加 `uploadDataOnlyViaWiFi` 启动选项，允许只在 WiFi 网络条件下上传问题
- 增加 `currentInvocationEvent` api，用于获取当前呼出方式
- 其他优化


### 2016.03.12 v1.2.0
- 新增网络请求跟踪功能（支持 HTTP / HTTPS），默认禁用，在插件配置中设置 `trackingNetworkEnabled true` 开启；
- 新增在 `BTGInvocationEventBubble` 模式下，可通过 Bugtags 后台动态改变集成模式；
- 修复登陆后指派人获取失败的问题；
- 修复横竖屏切换小球消失的问题；
- 修复优先级切换 ui 显示的问题；
- 优化一些细节；

### 2016.03.09 v1.1.2

- 修复某些情况下小球可能会消失的问题
- 修复 v1.1.1 中 sslv3 解决方案未生效的问题
- 细节修复

### 2016.02.20 v1.1.1

- 兼容 Java 1.6
- 移除电话权限
- 修复可能存在的 sslv3 协议问题
- 细节修复

### 2016.01.06 v1.1.0

- 增加对 __`cocos2d-x 游戏`__的截屏支持(`仅支持以 gradle 打包`)
- 新增设置问题`提交之前和之后的回调 API`
- 新增`手动调用截屏界面` API
- 修复问题重传时可能产生的多线程竞争问题
- 其他 bug 修复

### 2015.12.05 v1.0.9

- 修复用户步骤时间记录的 bug，修改显示样式使得更易读
- 修复某些安卓 ROM 的 sdcard 路径不规范可能引起的 bug
- 修改对于 activity 的引用为软引用，`防止可能存在的内存泄漏`

### 2015.11.19 v1.0.8

- 截图改进：包括 __`Toast 和 Dialog`__
- 性能优化

### 2015.11.06 v1.0.7

- `自定义` version name 与 version code
- bug fix

### 2015.10.24 v1.0.6

- 支持 __`targetSdkVersion 23(Android M, 6.0)`__；
- 新增长按截图按钮重新开始记录数据;
- 支持后台高级设置的匿名提交选项；
- 优化闪退捕捉逻辑，Debugger Connected状态下__`默认不上报闪退`__；
- 设备信息增加 CPU 构架信息；
- 修正 console log 获取逻辑；
- __`权限可裁剪`__，裁剪方法见帮助文档；
- 启动选项可选 crash 截屏。

### 2015.09.29 v1.0.5

- `崩溃截图`
- 更多启动选项
- bug 修复
- 性能优化

### 2015.09.03 v1.0.4

- 性能优化

### 2015.08.26 v1.0.3

- 传输反馈
- 精简依赖
- 改善集成方式

### 2015.08.20 v1.0.2

- 性能优化

### 2015.08.15 v1.0.1

- 小问题修改

### 2015.08.07 v1.0.0

- 正式版发布

### 2015.08.01 v0.9.0

- Pre-release 发布

# License
This demo is [BSD-licensed](LICENSE).

[SDK for Eclipse]:https://github.com/bugtags/Bugtags-Android-Eclipse
[Bugtags]:http://bugtags.com
[Android Developer Site]:http://developer.android.com/tools/studio/index.html
