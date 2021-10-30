repositories {
    google()
    mavenCentral()
}

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

dependencies {

    implementation("com.android.tools.build:gradle:7.0.3")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20")

    implementation("org.eclipse.jgit:org.eclipse.jgit:5.4.0.201906121030-r")
}