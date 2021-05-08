#
# Copyright 2020 Supasin Tatiyanupanwong
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

-keep class dev.supasintatiyanupanwong.libraries.android.kits.location.internal.huawei.HuaweiLocationFactory {
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
