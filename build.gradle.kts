// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

task("runAffectedModulesTest") {

    Library.getAffectedModules(rootProject.projectDir).forEach { affectedModuleName ->

        val affectedProject = rootProject.subprojects.find { it.name == affectedModuleName }

        val hashAndroidPlugin = affectedProject!!.plugins.hasPlugin("com.android.application")
                || affectedProject.plugins.hasPlugin("com.android.library")

        when (hashAndroidPlugin) {

            true -> dependsOn("$affectedModuleName:testDebugUnitTest")
            else -> dependsOn("$affectedModuleName:test")
        }
    }
}