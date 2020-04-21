package com.devyk.gleslib

import android.view.Surface

/**
 * <pre>
 *     author  : devyk on 2020-04-19 21:37
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is JNIUtils
 * </pre>
 */

class JNIUtils {

    /**
     * 相当于 Java 静态代码块
     *
     *    static {
     *      System.loadLibrary("native_gles");
     *    }
     *
     */
    companion object {
        init {
            System.loadLibrary("native_gles")
        }
    }


    /**
     * surface create
     *
     * kotlin 中的 external 关键字相当于 Java 中 native
     *
     */
    public external fun surfaceCreate(surface: Surface);

    /**
     * 框口改变
     * @param width 改变的宽
     * @param height 改变的高
     *
     */
    public external fun surfaceChange(width: Int, height: Int);


    /**
     * surface destory
     */
    public external fun surfaceDestory();


    /**
     * 绘制 Bitmap 的 byte[] 数据
     */
    public external fun drawBitmapByte(width: Int, height: Int, byteArray: ByteArray);


    /**
     * 滤镜
     * @param filterType 滤镜类型
     *
     */
    public external fun filter(filterType: Int);


    /**
     * 绘制 YUV 数据
     * @param width     YUV 宽
     * @param height    YUV 高
     * @param yuvFormat YUV 格式
     * @param byteArray YUV byte[] 数据
     *
     */
    public external fun drawYUVData(width: Int, height: Int, yuvFormat: Int, byteArray: ByteArray);


}