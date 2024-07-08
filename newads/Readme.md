Step-1 setting.gradle -> include 'nativetemplates'

Step-2 (App level)
apply plugin: 'com.android.application'

apply plugin: 'applovin-quality-service' applovin { apiKey "5B-rA-_
LdEIxAKbVI3xcRLamEeAXySIaOqHfIXJHmxfNWqr1rzXR2Ix5GCIsh9RbymdlztWSnM_MotLTpF-7Ig" //replace api key
from applovin console }

android { ..... } dependencies { implementation project(path: ':nativetemplates')
implementation 'com.applovin:applovin-sdk:10.3.5' implementation 'com.applovin.mediation:
facebook-adapter:6.7.0.0' }

Step-3 build.gradle (Project level) ->

buildscript { repositories { flatDir { dirs 'libs' } gradlePluginPortal()
// for ironsource maven { url 'https://android-sdk.is.com/'
} maven { url 'https://artifacts.applovin.com/android' } } dependencies { classpath "
com.applovin.quality:AppLovinQualityServiceGradlePlugin:+"
} }

allprojects { repositories { flatDir { dirs 'libs' } gradlePluginPortal()
// for ironsource maven { url 'https://android-sdk.is.com/'
} maven { url 'https://artifacts.applovin.com/android' } } }

Step-4 Use MyApp class as parent in your application class

Step-5 Add following code in launcher activity Handler handler = new Handler(Looper.getMainLooper())
{ @Override public void handleMessage(Message msg) { new Timer().schedule(new TimerTask() { public
void run() { startActivity(new Intent(SplashActivity.this, MainActivity.class)); finish(); } },
2000); } }; AdsIdLoader.apiCall(this, (applicationId/ads_loading_name), handler);

Step-6 Must be use parent activity NativeBaseActivity for MainActivity

Step-7 Replace applovin.sdk.key in Manifest.xml of Library
<meta-data android:name="applovin.sdk.key"
android:value="___key___"/>

Step-8 strings.xml Replace app_unit_id with live id