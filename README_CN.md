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

## 第一步：
* 在项目的 build.gradle（项目最外层的 build.gradle 文件，所谓的 Top-level build file）设置 repositories：

```
buildscript {
    repositories {
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:1.3.0'
        
        //**重要**
        classpath 'com.bugtags.library:bugtags-gradle:1.1.0'
    }
}
allprojects {
    repositories {
        jcenter() //注：repository 1
        mavenCentral()  //注：repository 2
    }
}
```

* 在模块的 build.gradle **应用插件**和**添加依赖**：

```
//应用插件
apply plugin: 'com.bugtags.library.plugin'

dependencies {
    //添加依赖
    compile 'com.bugtags.library:bugtags-lib:latest.integration'
}
```

> 最新版本是: **1.1.0**，你也可以在添加依赖时使用明确的版本，

> compile 'com.bugtags.library:bugtags-lib:1.1.0'

## 第二步：
* 在你的 Activity 基类中添加3个回调：

```
    package your.package.name;

    import android.app.Activity;
    import android.os.Bundle;
    import android.view.MotionEvent;

    import com.bugtags.library.Bugtags;

    public class CustomActivity extends Activity{
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

## 第三步：

* 继承 Application，在 onCreate() 方法中初始化 Bugtags：

```
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //在这里初始化
        Bugtags.start("YOUR APPKEY", this, Bugtags.BTGInvocationEventBubble);
    }
}
```

* 修改 AndroidManifest.xml，使用 MyApplication 类,例如：

```
<application
    android:name=".MyApplication"
    android:label="@string/app_name"
    android:theme="@style/AppTheme" >
    ....
</application>
```

关于如何使用 Android Studio 以及 gradle，请参考：[Android Developer Site].

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
        classpath 'com.bugtags.library-canary:bugtags-gradle:1.1.0'//修改
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
2016.01.06    1.1.0
- 增加对 cocos2d-x 游戏的截屏支持(仅支持以 gradle 打包)
- 新增设置问题提交之前和之后的回调 API
- 新增手动调用截屏界面的 API
- 修复问题重传时可能产生的多线程竞争问题
- 其他 bug 修复

2015.12.05    1.0.9
- 修复用户步骤时间记录的 bug，修改显示样式使得更易读
- 修复某些安卓 ROM 的 sdcard 路径不规范可能引起的 bug
- 修改对于 activity 的引用为软引用，防止可能存在的内存泄漏

2015.11.19    1.0.8
- 截图改进：包括 Toast 和 Dialog
- 性能优化

2015.11.06    1.0.7
- 自定义version name 与 version code
- bug fix

2015.10.24    1.0.6     
- 支持targetSdkVersion 23(Android M, 6.0)；
- 新增长按截图按钮重新开始记录数据;
- 支持后台高级设置的匿名提交选项；
- 优化闪退捕捉逻辑，Debugger Connected状态下默认不上报闪退；
- 设备信息增加CPU构架信息；
- 修正console log获取逻辑；
- 权限可裁剪，裁剪方法见帮助文档；
- 启动选项可选crash截屏。

2015.09.29    1.0.5    崩溃截图、更多启动选项、bug修复、性能优化

2015.09.03    1.0.4    性能优化

2015.08.26    1.0.3    传输反馈、精简依赖、改善集成方式

2015.08.20    1.0.2    性能优化

2015.08.15    1.0.1    小问题修改

2015.08.07    1.0.0    正式版发布

2015.08.01    0.9.0    Pre-release发布

# License
This demo is [BSD-licensed](LICENSE).

[SDK for Eclipse]:https://github.com/bugtags/Bugtags-Android-Eclipse
[Bugtags]:http://bugtags.com
[Android Developer Site]:http://developer.android.com/tools/studio/index.html
