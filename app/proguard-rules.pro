# Uncomment this to preserve the line number information for debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to hide the original source file name.
-renamesourcefileattribute SourceFile

# Repackage classes into the top-level.
-repackageclasses

# Remove logs
-assumenosideeffects class android.util.Log {
   public static boolean isLoggable(java.lang.String, int);
   public static int v(...);
   public static int i(...);
   public static int w(...);
   public static int d(...);
   public static int e(...);
}

# Kotlin: cleaning Java bytecode before release
-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    public static void checkExpressionValueIsNotNull(...);
    public static void checkNotNullExpressionValue(...);
    public static void checkReturnedValueIsNotNull(...);
    public static void checkFieldIsNotNull(...);
    public static void checkParameterIsNotNull(...);
}

# Gson
-dontnote com.google.gson.**
 # GSON TypeAdapters are only referenced in annotations so ProGuard doesn't find their method usage
 -keepclassmembers,allowobfuscation,includedescriptorclasses class * extends com.google.gson.TypeAdapter {
     public <methods>;
 }
 # GSON TypeAdapterFactory is an interface, we need to keep the entire class, not just its members
 -keep,allowobfuscation,includedescriptorclasses class * implements com.google.gson.TypeAdapterFactory
 # GSON JsonDeserializer and JsonSerializer are interfaces, we need to keep the entire class, not just its members
 -keep,allowobfuscation,includedescriptorclasses class * implements com.google.gson.JsonDeserializer
 -keep,allowobfuscation,includedescriptorclasses class * implements com.google.gson.JsonSerializer
 # Ensure that all fields annotated with SerializedName and Expose will be kept
-keepclasseswithmembers,allowobfuscation,includedescriptorclasses class * {
    @com.google.gson.annotations.Expose <fields>;
}
-keepclasseswithmembers,allowobfuscation,includedescriptorclasses class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Retrofit
-dontwarn okio.**
-dontwarn okhttp3.**
-dontwarn org.conscrypt.**
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions
# Retain service method parameters.
-keepclassmembernames,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
# Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response
# With R8 full mode generic signatures are stripped for classes that are not kept.
#Suspend functions are wrapped in continuations where the type argument is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# FirebaseCrashlytics
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception