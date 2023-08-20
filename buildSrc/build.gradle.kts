repositories {
    maven { url = uri("https://maven.aliyun.com/repository/public") }
    maven { url = uri("https://maven.aliyun.com/repository/google") }
    maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
    mavenCentral()
    google()
}

plugins {
    `kotlin-dsl`
}

dependencies {
    implementation("com.android.tools.build:gradle-api:8.1.0")
}
