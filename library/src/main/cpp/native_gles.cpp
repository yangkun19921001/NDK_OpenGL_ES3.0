//
// Created by 阳坤 on 2020-04-19.
//

#include <jni.h>
#include <android_xlog.h>
#include <GLES3/gl3.h>


/**
 * Kotlin 层定义的 native 函数
 */
#define JNI_NAME "com/devyk/gleslib/JNIUtils"


#define LENGTH(x) ((int) (sizeof(x) / sizeof((x)[0])))


static void Android_JNI_JNIUtils_surfaceCreate(JNIEnv *env, jobject object, jobject surface) {
    LOGI("----surfaceCreate----");
}


static void Android_JNI_JNIUtils_surfaceChange(JNIEnv *env, jobject object, jint width, jint height) {
    LOGI("----surfaceChange----");
}


static void Android_JNI_JNIUtils_surfaceDestory(JNIEnv *env, jobject object) {
    LOGI("----surfaceDestory----");
}

static void
Android_JNI_JNIUtils_drawBitmapByte(JNIEnv *env, jobject object, jint width, jint height, jbyteArray bitmapArray) {
    LOGI("----drawBitmapByte----");
}

static void Android_JNI_JNIUtils_filter(JNIEnv *env, jobject object, jint type) {
    LOGI("----filter----");
}

static void Android_JNI_JNIUtils_drawYUVData(JNIEnv *env, jobject object, jint width, jint height, jint type,
                                             jbyteArray bitmapArray) {
    LOGI("----drawYUVData----");
}


/**
 * 动态 register
 */
static JNINativeMethod jniUtilNativeMethods[] = {
        {"surfaceCreate",  "(Landroid/view/Surface;)V", (void *) Android_JNI_JNIUtils_surfaceCreate},
        {"surfaceChange",  "(II)V",                     (void *) Android_JNI_JNIUtils_surfaceChange},
        {"surfaceDestory", "()V",                       (void *) Android_JNI_JNIUtils_surfaceDestory},
        {"drawBitmapByte", "(II[B)V",                   (void *) Android_JNI_JNIUtils_drawBitmapByte},
        {"filter",         "(I)V",                      (void *) Android_JNI_JNIUtils_filter},
        {"drawYUVData",    "(III[B)V",                  (void *) Android_JNI_JNIUtils_drawYUVData},
};

static void UnregisterNativeMethods(JNIEnv *env, const char *className)
{
    LOGI("UnregisterNativeMethods");
    jclass clazz = env->FindClass(className);
    if (clazz == NULL)
    {
        LOGI("UnregisterNativeMethods fail. clazz == NULL");
        return;
    }
    if (env != NULL)
    {
        env->UnregisterNatives(clazz);
    }
}


/**
 * 加载 JNI 的时候执行，用于动态注解 JNI 函数
 * @return
 */
JNIEXPORT jint JNICALL
JNI_OnLoad(JavaVM *javaVm, void *pVoid) {
    //通过虚拟机 创建全新的 jniEnv
    JNIEnv *jniEnv = NULL;
    jint result = javaVm->GetEnv(reinterpret_cast<void **>(&jniEnv), JNI_VERSION_1_6);
    if (result != JNI_OK) {
        return JNI_ERR; // 主动报错
    }
    jclass jniUtils = jniEnv->FindClass(JNI_NAME);
    jniEnv->RegisterNatives(jniUtils, jniUtilNativeMethods, LENGTH(jniUtilNativeMethods));
    jniEnv->DeleteLocalRef(jniUtils);

    return JNI_VERSION_1_6;
}

/**
 * 释放
 */
extern "C" void JNI_OnUnload(JavaVM *jvm, void *p)
{
    JNIEnv *env = NULL;
    if (jvm->GetEnv((void **) (&env), JNI_VERSION_1_6) != JNI_OK)
    {
        return;
    }

    UnregisterNativeMethods(env, JNI_NAME);
}