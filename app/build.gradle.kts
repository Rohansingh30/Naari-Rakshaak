plugins {

    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("kotlin-android")
}

android {
    namespace = "com.blogspot.softwareengineerrohan.naarirakshak"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.blogspot.softwareengineerrohan.naarirakshak"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
buildFeatures{
    viewBinding = true

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    //noinspection GradleCompatible
    implementation("com.android.support:cardview-v7:28.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // bottom navigation bar
    implementation ("nl.joery.animatedbottombar:library:1.1.0")

    // google servive
    implementation ("com.google.android.gms:play-services-location:21.0.1")
    // google maps
 //   implementation 'com.google.maps.android:android-maps-utils:3.4.0'

//    implementation("androidx.appcompat:design:1.6.1")
    //noinspection GradleCompatible,GradleCompatible
    implementation ("com.android.support:design:28.0.0")


    // circularimageview
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    // expandable textview
    implementation("at.blogc:expandabletextview:1.0.5")

    // room depencency
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")

    //lifecycle dependency
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.room:room-runtime:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.2")





}