package com.devyk.gleslib

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import android.view.SurfaceHolder

/**
 * <pre>
 *     author  : devyk on 2020-04-20 19:42
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is YKGLSurfaceView
 * </pre>
 */

class YKGLSurfaceView : GLSurfaceView {
    /**
     * 声明一个 native 管理类
     */
    private val mOpenGLESUtils = JNIUtils();

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        mOpenGLESUtils.surfaceCreate(holder!!.surface)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, w: Int, h: Int) {
        mOpenGLESUtils.surfaceChange(w, h)
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        mOpenGLESUtils.surfaceDestory()
    }


    private fun init(context: Context?) {
        holder.addCallback(this)
    }
}