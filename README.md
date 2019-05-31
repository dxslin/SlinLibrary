# SlinDialog

封装了一个通用的DialogFragment。

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
implementation 'com.github.dxslin:SlinDialog:v0.1'
```

弹出消息确认框：
```java
ConfirmDialogViewHolder viewHolder = new ConfirmDialogViewHolder.Builder()
        .msg("这是消息")
        .title("标题")
        .confirmText("确认")
        .cancelText("取消")
        .icon(getDrawable(R.drawable.slin))
        .confirmListener(((view1, dialog) -> {
            Toast.makeText(this, "确认", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }))
        .build();
SlinDialog.showDialog(getSupportFragmentManager(), viewHolder);
```
效果图：
![消息确认框](img/消息确认框.png "消息确认框")

如果你想使用其他界面，只需要继承DialogViewHolder设置新的界面
