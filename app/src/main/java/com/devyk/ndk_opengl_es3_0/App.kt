package com.devyk.ndk_opengl_es3_0

import android.app.Activity
import android.app.Application
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.bugly.crashreport.CrashReport.UserStrategy
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Process
import androidx.annotation.RequiresApi
import android.text.TextUtils
import com.tencent.mars.xlog.Log
import com.tencent.mars.xlog.Xlog
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException


/**
 * <pre>
 *     author  : devyk on 2020-04-20 00:28
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is App
 * </pre>
 */
class App : Application(), Application.ActivityLifecycleCallbacks {

    private val TAG = this.javaClass.simpleName

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
        initXLog()
        initBugly()
    }

    private fun initXLog() {
        val logPath = Environment.getExternalStorageDirectory().absolutePath + "/devyk/xlog"
        val cachePath = cacheDir.absolutePath + ""
        if (!File(logPath).exists())
            File(logPath).mkdirs()
        if (!File(cachePath).exists())
            File(cachePath).mkdirs()
        if (BuildConfig.DEBUG) {
            Xlog.open(BuildConfig.DEBUG, Xlog.LEVEL_DEBUG, Xlog.AppednerModeAsync, cachePath, logPath, "ndk_gles", "")
            Xlog.setConsoleLogOpen(true)
        } else {
            Xlog.open(BuildConfig.DEBUG, Xlog.LEVEL_DEBUG, Xlog.AppednerModeAsync, cachePath, logPath, "ndk_gles", "")
            Xlog.setConsoleLogOpen(false)
        }
        Log.setLogImp(Xlog())
        Log.e(TAG, "xlog-init")
    }

    private fun initBugly() {
        val context = applicationContext
        // 获取当前包名
        val packageName = context.packageName
        // 获取当前进程名
        val processName = getProcessName(Process.myPid())
        // 设置是否为上报进程
        val strategy = UserStrategy(context)
        strategy.isUploadProcess = processName == null || processName == packageName
        // 初始化Bugly
        CrashReport.initCrashReport(context, "1aa7ace7b0", BuildConfig.DEBUG, strategy)
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private fun getProcessName(pid: Int): String? {
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(FileReader("/proc/$pid/cmdline"))
            var processName = reader.readLine()
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim({ it <= ' ' })
            }
            return processName
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        } finally {
            try {
                if (reader != null) {
                    reader!!.close()
                }
            } catch (exception: IOException) {
                exception.printStackTrace()
            }

        }
        return null
    }


    override fun onActivityPaused(activity: Activity) {
        Log.e(TAG, "onActivityPaused--->" + activity.toString())

    }

    override fun onActivityStarted(activity: Activity) {
        Log.e(TAG, "onActivityStarted--->" + activity.toString())
    }

    override fun onActivityDestroyed(activity: Activity) {
        Log.e(TAG, "onActivityDestroyed--->" + activity.toString())
        if (activity is MainActivity) {
//            Log.appenderClose()
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        Log.e(TAG, "onActivitySaveInstanceState--->" + activity.toString())
    }

    override fun onActivityStopped(activity: Activity) {
        Log.e(TAG, "onActivityStopped--->" + activity.toString())
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Log.e(TAG, "onActivityCreated--->" + activity.toString())
    }

    override fun onActivityResumed(activity: Activity) {
        Log.e(TAG, "onActivityResumed--->" + activity.toString())
    }

}