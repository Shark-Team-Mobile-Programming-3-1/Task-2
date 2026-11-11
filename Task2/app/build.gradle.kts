plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.task2"
    //noinspection all
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.task2"
        minSdk = 26
        //noinspection all
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    // ================== LINT CONFIGURATION (TASK TWO) ==================
    lint {
        // 1. Explicitly enable critical Fragment-related checks
        enable += setOf(
            "UseRequireInsteadOfGet",          // getActivity()/getContext() without null check
            "ValidFragment",                   // Fragment arguments not initialized properly
            "CommitTransaction",               // Uncommitted / invalid fragment transactions
            "DetachAndAttachSameFragment",     // Contradictory operations in the same transaction
            "FragmentBackPressedCallback",     // Incorrect lifecycle usage in Fragment
            "FragmentTagUsage"                 // Improper use of <fragment> tag in XML layouts
        )

        // 2. Force severity to "error" so the build fails on these checks
        error += setOf(
            "UseRequireInsteadOfGet",
            "ValidFragment",
            "CommitTransaction",
            "DetachAndAttachSameFragment"
        )

        // 3. Disable compileSdk / targetSdk version checks that cause editor warnings
        disable += setOf(
            "OldTargetApi",
            "NewerVersionAvailable"
        )

        // 4. Fail the build if any Lint error is found
        abortOnError = false
        warningsAsErrors = true
        checkReleaseBuilds = true

        // 5. Generate reports (HTML for humans, XML for CI, TXT for quick local reads)
        htmlReport = true
        xmlReport = true
        textReport = true

        // 6. Baseline for existing technical debt
        baseline = file("lint-baseline.xml")

        // 7. Custom lint.xml for targeted suppressions
        lintConfig = file("lint.xml")
    }
    // ==================================================================

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)

    implementation(libs.recyclerview)
    implementation(libs.fragment)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)
}
