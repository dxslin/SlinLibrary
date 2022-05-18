# <img src="https://raw.githubusercontent.com/dxslin/SlinLibrary/master/img/slin.png" align="center" width="24" height="24"  />  slin提供的library

最新版本：[![ ](https://jitpack.io/v/dxslin/SlinLibrary.svg)](https://jitpack.io/#dxslin/SlinLibrary)


| library  |  引用  |  简介  |
|---|---|---|
|  [Score](./Score) |  com.github.dxslin.SlinLibrary:Score:1.0.0  |  封装的Android开发核心库（jetpack套件） |
|  [ViewBindingExt](./ViewBindingExt) |  com.github.dxslin.SlinLibrary:ViewBindingExt:1.0.0   |  ViewBinding扩展方法，快速创建绑定  |
|  [SlinDialog](./SlinDialog) |  com.github.dxslin.SlinLibrary:SlinDialog:1.0.0   |  一个封装了的DialogFragment  |
|  [ViewPagerIndicator(已废弃)](ViewPagerIndicator) |  com.github.dxslin.SlinLibrary:ViewPagerIndicator:1.0.0   |  仿BiliBili的一个tab指示器  |

### 使用方法：

1. 使用前请在你的build.gradle添加 `maven { url 'https://jitpack.io' }`，比如
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

2. 然后根据需要添加依赖，比如
```
implementation 'com.github.dxslin:SlinDialog:1.0.0 '
```
