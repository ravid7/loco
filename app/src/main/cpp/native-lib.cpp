#include <jni.h>
#include <string>
#include <stack>
#include <iterator>
#include <opencv2/core/core.hpp>
#include "lib/include/utils.h"
#include <android/bitmap.h>
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