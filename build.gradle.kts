buildscript {
    repositories {
        google()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath(BuildPlugins.TOOLS_BUILD_GRADLE)
        classpath(BuildPlugins.TOOLS_KOTLIN)
        classpath(BuildPlugins.TOOLS_DAGGER_HILT)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}