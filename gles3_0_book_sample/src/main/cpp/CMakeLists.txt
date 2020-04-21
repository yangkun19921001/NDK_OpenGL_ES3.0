cmake_minimum_required(VERSION 3.4.1)


message(--->${CMAKE_SOURCE_DIR})
message(--->${ANDROID_NDK})


set(opengl_home ${CMAKE_SOURCE_DIR})
set(opengl_common ${CMAKE_SOURCE_DIR}/common)

# native c++ 代码
FILE(GLOB OPENGL_ES_3.0_book ${opengl_home}/chapter_2/*.c)
FILE(GLOB OPENGL_COMMON_android ${opengl_common}/android/*.c)
FILE(GLOB OPENGL_COMMON ${opengl_common}/*.c)
FILE(GLOB main ${opengl_home}/*.cpp)

#include day2 所需要的 header
include_directories(${opengl_common}/include)



# Android NativeActivity
include_directories(${ANDROID_NDK}/sources/android/native_app_glue)
add_library(native_app_glue STATIC ${ANDROID_NDK}/sources/android/native_app_glue/android_native_app_glue.c)
set(CMAKE_SHARED_LINKER_FLAGS
        "${CMAKE_SHARED_LINKER_FLAGS} -u ANativeActivity_onCreate")


add_library(
        Hello_Triangle
#        opengles_3.0_demo
        SHARED
        ${OPENGL_ES_3.0_book}
        ${OPENGL_COMMON_android}
        ${OPENGL_COMMON}
)



target_link_libraries(
#        opengles_3.0_demo
        Hello_Triangle
        native_app_glue
        EGL
        GLESv3
        android
        log
)