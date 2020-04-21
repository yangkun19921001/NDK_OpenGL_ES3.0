package com.devyk.ndk_opengl_es3_0.sample

import android.app.NativeActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devyk.ndk_opengl_es3_0.R
import kotlinx.android.synthetic.main.sample_activity.*

/**
 * <pre>
 *     author  : devyk on 2020-04-21 14:41
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is OpenGL3_0_BookSample
 * </pre>
 */

public class OpenGL3_0_BookSample : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sample_activity)


        btn_sample_draw_triangle.setOnClickListener {
            startActivity(Intent(this, NativeActivity::class.java))
        }

    }


}