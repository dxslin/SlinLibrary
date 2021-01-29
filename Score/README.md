
 # SCore JetPack封装核心库

 Hilt + DataStore + okhttp + retrofit + gilde + timber

 使用步骤：
 ### 1. 添加Hilt生成插件
 根目录build.gradle添加仓库https://jitpack.io，添加hilt插件依赖：
 ```groovy
    repositories {
        maven { url 'https://jitpack.io' }
    }
    dependencies {
        classpath 'com.google.dagger:hilt-android-gradle-plugin:2.28-alpha'
    }
 ```

 module目录build.gradle添加hilt插件：
 ```groovy
 plugins {
      id 'kotlin-kapt'
      id 'dagger.hilt.android.plugin'
 }
 ```
 ```groovy
 //添加hilt依赖，因为下面用到了hilt生成配置项，所以必须配置kapt
 implementation "com.google.dagger:hilt-android:2.28-alpha"
 kapt "com.google.dagger:hilt-android-compiler:2.28-alpha"
 kapt "androidx.hilt:hilt-compiler:1.0.0-alpha02"

 //添加Score依赖项
 implementation "com.github.dxslin.SlinLibrary:Score:1.0.0"
 ```

 ### 2. 新建ConfigModule.kt通过依赖注入配置项目
 ```kotlin
 @Module
 @InstallIn(ApplicationComponent::class)
 object ConfigModule {

      @Provides
      fun provideAppConfig(
              @ApplicationContext context: Context,
      ): CoreConfig {
              return CoreConfig(
                  application = context as Application,
                  baseUrl = DefaultConfig.BASE_URL,
                  httpLogLevel = DefaultConfig.HTTP_LOG_LEVEL,
                  ...
              )
      }

 }
 ```

 ### 3. 在创建Application添加HiltAndroidApp(hilt要求)注解，在OnCreate里面初始化Score
 ```kotlin
 @HiltAndroidApp
 class XXXApplication :Application() {

      override fun onCreate() {
          super.onCreate()

          SCore.init(this)

      }
 }

 ```







