# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/enoler/AndroidStudioProjects/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.kts
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-dontwarn okio.**
-keepattributes Signature
-keepattributes *Annotation*

-keep public class com.google.firebase.** {}
-keep class com.google.android.gms.internal.** {}

-keepattributes SourceFile,LineNumberTable
-keep class com.parse.*{ *; }
-dontwarn com.parse.**
-keepclasseswithmembernames class * {
    native <methods>;
}
-keepattributes Exceptions, Signature, InnerClasses, LineNumberTable

-keep class uikitview.bug.MR$images { *; }
-keep class uikitview.bug.MR$strings { *; }