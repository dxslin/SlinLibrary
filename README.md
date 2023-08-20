# <img src="https://raw.githubusercontent.com/dxslin/SlinLibrary/master/img/slin.png" align="center" width="24" height="24"  />  slin提供的library

最新版本：[![ ](https://jitpack.io/v/dxslin/SlinLibrary.svg)](https://jitpack.io/#dxslin/SlinLibrary)


| library  |  引用  |  简介  |
|---|---|---|
|  [Score](./Score) |  io.github.dxslin:Score:1.1.6  |  封装的Android开发核心库（jetpack套件） |
|  [ViewBindingExt](./ViewBindingExt) |  io.github.dxslin:ViewBindingExt:1.1.6   |  ViewBinding扩展方法，快速创建绑定  |
|  [SlinDialog](./SlinDialog) |  io.github.dxslin:SlinDialog:1.1.6   |  一个封装了的DialogFragment  |
|  [ViewPagerIndicator(已废弃)](ViewPagerIndicator) |  io.github.dxslin:ViewPagerIndicator:1.1.6   |  仿BiliBili的一个tab指示器  |

### 使用方法：

1. 使用前请在你的build.gradle添加 `mavenCentral()`，比如
```
allprojects {
    repositories {
        ...
        mavenCentral()
    }
}
```

2. 然后根据需要添加依赖，比如
```
implementation 'io.github.dxslin:Score:1.1.6 '
```
