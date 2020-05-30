#include <jni.h>
#include <string>
#include <vector>
#include <iterator>
//#include <opencv2/core/core.hpp>
#include "kmeans.h"
#include <android/bitmap.h>
#include <android/log.h>



#define LOGV(TAG,...) __android_log_print(ANDROID_LOG_VERBOSE, TAG,__VA_ARGS__)
#define LOGD(TAG,...) __android_log_print(ANDROID_LOG_DEBUG  , TAG,__VA_ARGS__)
#define LOGI(TAG,...) __android_log_print(ANDROID_LOG_INFO   , TAG,__VA_ARGS__)
#define LOGW(TAG,...) __android_log_print(ANDROID_LOG_WARN   , TAG,__VA_ARGS__)
#define LOGE(TAG,...) __android_log_print(ANDROID_LOG_ERROR  , TAG,__VA_ARGS__)

using namespace std;
extern "C" JNIEXPORT jstring JNICALL
Java_ravid7_github_loco_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {

    std::string hello = "This is shit";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jint JNICALL
Java_ravid7_github_loco_MainActivity_intFromJNI(
        JNIEnv* env,
    jobject /* this */) {
        int x = 7;
        return x;
}

extern "C" JNIEXPORT jintArray JNICALL
Java_ravid7_github_loco_Utils_DownloadHelper_colorFromJNI(
        JNIEnv* env,
        jobject,
        jobject bitmap
        ) {
    int ret = -1;
    AndroidBitmapInfo info;
    void* holder = 0;
    if((ret = AndroidBitmap_getInfo(env, bitmap, &info)) < 0){
        LOGE("INVALID IMAGE", "Cannot fetch data, either image is invalid or not supported type.");
        return NULL;
    }
    if (info.format != ANDROID_BITMAP_FORMAT_RGBA_8888 )
    {
        LOGE("COLOR ENCODING INCOMPATIBLE","Bitmap format is not RGBA_8888!");
        return NULL;
    }
    if ((ret = AndroidBitmap_lockPixels(env, bitmap, &holder)) < 0) {
        LOGE("LOCKING FAILED","AndroidBitmap_lockPixels() failed ! error=%d", ret);
        return NULL;
    }
    cv::Mat mat(info.height, info.width, CV_32F, holder);
    jintArray arr = env->NewIntArray(2);
    jint *ptr = env->GetIntArrayElements(arr, NULL);
    LOGE("Hey", "Not an error");
    ptr[0] = info.width;
    ptr[1] = info.height;
    env->ReleaseIntArrayElements(arr, ptr, NULL);
    return arr;
}


