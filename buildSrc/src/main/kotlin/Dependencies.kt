object Versions {
    const val toolsBuild = "8.1.0"
    const val toolsKotlin = "1.9.21"
    const val dagger = "2.42"
    const val material = "1.10.0"
    const val kotlin = "1.8.0-RC"
    const val core = "1.2.0"
}

object BuildPlugins {
    const val TOOLS_BUILD_GRADLE = "com.android.tools.build:gradle:${Versions.toolsBuild}"
    const val TOOLS_KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.toolsKotlin}"
    const val TOOLS_DAGGER_HILT = "com.google.dagger:hilt-android-gradle-plugin:${Versions.dagger}"
    const val ANDROID_APPLICATION_PLUGIN = "com.android.application"
    const val KOTLIN_ANDROID_PLUGIN = "kotlin-android"
    const val KOTLIN_KAPT = "kotlin-kapt"
    const val DAGGER_HILT = "dagger.hilt.android.plugin"
}

object Di {
    const val hilt = "com.google.dagger:hilt-android:${Versions.dagger}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.dagger}"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:1.1.0"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:1.1.0"
}

object Google {
    const val MATERIAL_DESIGN = "com.google.android.material:material:${Versions.material}"
    val list = listOf(MATERIAL_DESIGN,)
}

object Kotlin {
    private const val KT_STD = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    private const val KTX_CORE = "androidx.core:core-ktx:${Versions.core}"
    private const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlin}"
    private const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin}"

    val list = listOf(KT_STD, KTX_CORE, COROUTINES, COROUTINES_ANDROID)
}