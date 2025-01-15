plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    jacoco
}

android {
    namespace = "com.vector.myapplication"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.vector.myapplication"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildToolsVersion = "35.0.0"
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_17
        sourceCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

// 注册一个生成覆盖率报告的任务
tasks.register<JacocoReport>("jacocoTestReport") {
    // 1. 先执行单元测试
    dependsOn("testDebugUnitTest")

    reports {
        // 生成 XML 报告
        xml.required.set(true)
        // 生成 HTML 报告
        html.required.set(true)
        csv.required.set(false)
    }

    // 排除不需要统计覆盖率的文件或目录
    val excludes = listOf(
        "**/R.class",
        "**/R\$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        // 测试类自身不计入覆盖率
        "**/*Test*.*",
        // 可以按需要排除一些第三方库或生成代码目录
    )

    // 要统计的 .class 文件所在目录 (Debug 构建产物)
    // 注意：在 Kotlin DSL 中不能直接用 buildDir，需要用 layout.buildDirectory
    val debugTree = fileTree(
        layout.buildDirectory.dir("tmp/kotlin-classes/debug").get().asFile
    ) {
        exclude(excludes)
    }

    // 源码目录（Kotlin/Java）
    // 以 main/java、main/kotlin 为主
    sourceDirectories.setFrom(
        files(
            "src/main/java",
            "src/main/kotlin"
        )
    )

    classDirectories.setFrom(debugTree)

    // 收集单元测试的执行数据 (executionData)
    executionData.setFrom(
        fileTree(
            layout.buildDirectory.dir("jacoco").get().asFile
        ) {
            include(
                "testDebugUnitTest.exec",
                // 部分AGP版本会输出到这里:
                "outputs/unit_test_code_coverage/debugUnitTest/testDebugUnitTest.exec"
            )
        }
    )
}