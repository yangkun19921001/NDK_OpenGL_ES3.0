cmake_minimum_required(VERSION 3.4.1)


message(--->${CMAKE_SOURCE_DIR})
message(--->${CMAKE_SOURCE_DIR}/xlogger/include)

# native c++ 代码
FILE(GLOB NATIVE_OPENGL_ES_3.0 ${CMAKE_SOURCE_DIR}/*.cpp)

FILE(GLOB OPEGLES_SAMPLE ${CMAKE_SOURCE_DIR}/opengl/*.cpp)

#include xlog
set(XLOG_HEADER ${CMAKE_SOURCE_DIR}/xlogger/include)
include_directories(${XLOG_HEADER})
# load xlog so
set(XLOG_LIB ${CMAKE_SOURCE_DIR}/../jniLibs/${CMAKE_ANDROID_ARCH_ABI}/libmarsxlog.so)


add_library(
        xlogger
        SHARED
        IMPORTED)
set_target_properties(xlogger PROPERTIES IMPORTED_LOCATION ${XLOG_LIB})


add_library(
        native_gles
        SHARED
        ${NATIVE_OPENGL_ES_3.0}
        ${OPEGLES_SAMPLE}
)

target_link_libraries(
        native_gles
        EGL
        GLESv3
        android
        log
        xlogger
)