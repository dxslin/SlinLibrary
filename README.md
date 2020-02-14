# slin提供的library

使用前请在你的build.gradle添加 `maven { url 'https://jitpack.io' }`，比如
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
然后根据需要添加依赖，比如
```
implementation 'com.github.dxslin:SlinDialog:v0.1'
```

| library  |  引用  |  简介  |
|---|---|---|
|  [SlinDialog](./SlinDialog/README.md) |  com.github.dxslin.SlinLibrary:SlinDialog:0.3.1  |  一个封装了的DialogFragment  |
|  [ViewPagerIndicator](ViewPagerIndicator/README.md) |  com.github.dxslin.SlinLibrary:ViewPagerIndicator:0.3.1  |  仿BiliBili的一个tab指示器  |

