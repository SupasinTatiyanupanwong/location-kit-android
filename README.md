# Location Kit

[![license](https://img.shields.io/github/license/SupasinTatiyanupanwong/location-kit-android.svg)](https://www.apache.org/licenses/LICENSE-2.0)

Location Kit is an abstraction wrapper that encapsulates Google Location Services (GLS), HUAWEI Location Kit and [Lost](https://github.com/lostzen/lost) (open-source alternative to GLS).

## Architecture

The library consists of 4 artifacts; `location-core`, `location-google`, `location-huawei` and `location-lost`.

* `location-core` artifact provides an abstraction interface to interact with Location APIs.
* `location-google` artifact provides the Google Location Services (GLS) integration to Location Kit.
* `location-huawei` artifact provides the HUAWEI Location Kit integration to Location Kit.
* `location-lost` artifact provides the Lost integration to Location Kit.

Each artifact transitively depended on its corresponding [base](https://github.com/Tavorcl/android-kits-base) artifacts.

## Current SDK versions

Currently, each artifact implements the following SDKs (for each one):

* `location-google`: `play-services-location:17.0.0`
* `location-huawei`: `location:5.0.0.301`
* `location-lost`: `lost:3.0.4`

## Declaring dependencies

Add the dependencies for the artifacts you need in the `build.gradle` file for your app or module:

```groovy
dependencies {
    // To use the Google Location Services (GLS) via Location Kit
    implementation 'com.tavorlabs.android.libraries.kits.location:location-google:1.0.0'

    // To use the HUAWEI Location Kit via Location Kit
    implementation 'com.tavorlabs.android.libraries.kits.location:location-huawei:1.0.0'

    // To use Lost via Location Kit
    implementation 'com.tavorlabs.android.libraries.kits.location:location-lost:1.0.0'

}
```

If more than one integration artifacts are included in your final build, the implementation will be selected based on API availability upon application startup.

In that case, the Google artifact will have the first priority, followed by huawei, and finally lost will have the lowest priority.

However, it is recommended to separate builds between them as next:

```groovy
android {
    ...
    flavorDimensions 'vendor'
    productFlavors {
        google
        huawei { applicationIdSuffix '.huawei' }
        lost { applicationIdSuffix '.lost' }
    }
}

configurations {
    google
    huawei
    lost

    googleImplementation.extendsFrom(google)
    googleCompileOnly.extendsFrom(huawei)

    lostImplementation.extendsFrom(lost)
    lostCompileOnly.extendsFrom(google)

    huaweiImplementation.extendsFrom(huawei)
    huaweiCompileOnly.extendsFrom(google)
}

dependencies {
    google 'com.tavorlabs.android.libraries.kits.location.location:location-google:1.0.0'
    huawei 'com.tavorlabs.android.libraries.kits.location:location-huawei:1.0.0'
    lost 'com.tavorlabs.android.libraries.kits.location:location-lost:1.0.0'
}
```

But, make sure to have one of the integration artifacts included in your final build, otherwise an exception will be thrown at runtime.

For more information about dependencies, see [Add build dependencies](https://developer.android.com/studio/build/dependencies).

## Additional documentation

* [Build location-aware apps - Android Developer](https://developer.android.com/training/location/index.html)
* [Location Kit - HMS Core - HUAWEI Developer](https://developer.huawei.com/consumer/en/hms/huawei-locationkit/)
* [Lost Location Framework](https://github.com/lostzen/lost)

The Lost artifact is really useful in devices where there isn't GMS or HMS installed. But that cases should be very few, like
AOSP emulators or devices with some kind of AOSP custom ROM.
Please note that the Lost artifact has some code implementation differences with the Google and Huawei artifacts. Also, it seems that Lost has
minor delays while getting GPS fixes, because it depends only on the Android LocationManager, which is from the AOSP core.
So, it is a bit experimental. Anyway, several tests have been done with AOSP emulators (and real devices) and it seems to be working ok.

Finally, the sample application is just for testing purposes. If you really want to implement this, you should wrap the implementation
some how (ex. RxJava + other design patterns), especially on complex projects.

The main differences with the original repo are the Lost artifact and the SettingsClient abstraction.

## Contribution guide

Any help to improve this is welcome. Please create a PR with: Description, Changes, and Screenshots (optional) if you want to :)

## License

Please note that this repo has been forked from [this one](https://github.com/SupasinTatiyanupanwong/location-kit-android). 

```
Copyright 2020 Supasin Tatiyanupanwong
Copyright 2021 Tavorlabs

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
