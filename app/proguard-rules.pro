-printmapping mapping.txt
-verbose
-dontoptimize
-dontpreverify
-dontshrink
-dontskipnonpubliclibraryclassmembers
-dontusemixedcaseclassnames
-keepparameternames
-renamesourcefileattribute SourceFile
-keepattributes *Annotation*
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod
-keep public class com.android.installreferrer.** { *; }
-keep class com.anjlab.android.iab.v3.** {
    public static <methods>;
}
-keep class com.anjlab.android.iab.v3.** {
    public static <methods>;
}
-keep class com.anjlab.android.iab.v3.PurchaseState {
    *;
}
-keep class * extends android.app.Activity
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
}
### RxJava, RxAndroid (https://gist.github.com/kosiara/487868792fbd3214f9c9)
-keep class rx.schedulers.Schedulers {
    public static <methods>;
}
-keep class rx.schedulers.ImmediateScheduler {
    public <methods>;
}
-keep class rx.schedulers.TestScheduler {
    public <methods>;
}
-keep class rx.schedulers.Schedulers {
    public static ** test();
}
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    long producerNode;
    long consumerNode;
}
-dontwarn sun.misc.Unsafe

