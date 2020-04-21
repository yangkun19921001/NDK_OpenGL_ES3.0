package com.devyk.ndk_opengl_es3_0

import android.annotation.SuppressLint
import android.app.NativeActivity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.devyk.gleslib.JNIUtils
import com.devyk.ndk_opengl_es3_0.sample.OpenGL3_0_BookSample
import com.tbruyelle.rxpermissions2.RxPermissions
import com.tencent.mars.xlog.Log
import com.tencent.mars.xlog.Xlog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var TAG = javaClass.simpleName;

    companion object{
        init {
//            System.loadLibrary("opengles_3.0_demo")
            System.loadLibrary("Hello_Triangle")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermission();
        initViewListener();


    }

    private fun initViewListener() {
        btn_to_sample.setOnClickListener {
            /**
             * 跳转 书中的例子
             */
            startActivity(Intent(this, OpenGL3_0_BookSample::class.java));
        }

        btn_local_draw_triangle.setOnClickListener {
            /**
             * 绘制三角形
             */
            startActivity(Intent(this, NativeActivity::class.java));
        }
    }

    /**
     * 检查权限
     */
    @SuppressLint("CheckResult")
    private fun checkPermission() {
        var rxPermissions = RxPermissions(this);
        rxPermissions.requestEach(
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).subscribe {
            if (it.granted) {
                Log.i(TAG, "权限获取成功");
            } else if (it.shouldShowRequestPermissionRationale) {
                Log.i(TAG, "权限获取失败");
            }
        }
    }
}
