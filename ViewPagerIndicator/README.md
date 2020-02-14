# ViewPagerIndicator

仿BiliBili的一个tab指示器

效果图：
![ViewPagerIndicator](../img/ViewPagerIndicator.gif "ViewPagerIndicator")

使用方式：

在你的build.gradle添加"**maven { url 'https://jitpack.io' }**"

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

添加依赖

```
implementation 'com.github.dxslin.SlinLibrary:SlinDialog:0.3.1'
```

布局文件：
```
    <com.slin.indicator.ViewPagerIndicator
        android:id="@+id/viewPagerIndicator"
        android:layout_width="match_parent"
        android:layout_height="48dp"
        app:vpi_autoArrange="true"
        app:vpi_cursorColor="@color/highlight_color"
        app:vpi_cursorHeight="4dp"
        app:vpi_cursorPadding="10dp"
        app:vpi_isShowCursor="true"
        app:vpi_selectTextColor="@color/highlight_color"
        app:vpi_tabSpacing="20dp"
        app:vpi_textColor="@color/gray_333333"
        app:vpi_textSize="14sp" />
```
代码使用：
```
val title = listOf("销售", "php", "汽车电子开发工程师", "Android", "汽车项目管理", "厂长", "建筑设计师", "服务员")
//设置tab标题
viewPagerIndicator.setTabTitles(title)
//绑定ViewPager
viewPagerIndicator.bindViewPager(viewPager)
```

注意：目前只提供了AndroidX版本，如果不是使用AndroidX请勿使用
