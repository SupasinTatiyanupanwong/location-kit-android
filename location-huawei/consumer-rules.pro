-keep class me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.huawei.HuaweiLocationFactory {
    public static *** buildIfSupported(android.content.Context);
}

# https://developer.huawei.com/consumer/en/doc/development/HMSCore-Guides/config-obfuscation-scripts-0000001050185371
-ignorewarnings
-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
-keep class com.huawei.hianalytics.**{*;}
-keep class com.huawei.updatesdk.**{*;}
-keep class com.huawei.hms.**{*;}
