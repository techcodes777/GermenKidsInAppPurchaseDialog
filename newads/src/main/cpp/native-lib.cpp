#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_adsnativetamplete_retrofit_ApiClient_stringFromJNI(JNIEnv *env, jobject clazz) {
    std::string api = "http://wallpaper.visioninfotech.net/api/";
    return env->NewStringUTF(api.c_str());
}
