plugins {
    id(BuildPlugins.ANDROID_LIBRARY_PLUGIN)
    id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.DAGGER_HILT)
}

android {
    namespace = "com.multimodule.data"
    compileSdk = AppVersions.COMPILE_SDK

    defaultConfig {
        namespace = (AppVersions.APPLICATION_ID)
        minSdk = (AppVersions.MIN_SDK)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    api(project(":domain"))

    Google.list.forEach(::api)
    Kotlin.list.forEach(::api)

    /*DI*/
    with(Di){
        api(hilt)
        api(hiltNavigationCompose)
        kapt(hiltAndroidCompiler)
        kapt(hiltCompiler)
    }

    // Room
    with(Room){
        api(roomKtx)
        api(roomRuntime)
        kapt(roomCompiler)
    }

    api(ThirdParty.gson)


}