package com.slin.test.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.slin.test.R

class SingleTestActivity : AppCompatActivity() {

    companion object {
        const val KEY_FRAGMENT_NAME_CLASS = "key_fragment_class"

        fun Context.startSingleTestActivity(clazz: Class<out Fragment>) {
            val intent = Intent(this, SingleTestActivity::class.java)
            intent.putExtra(KEY_FRAGMENT_NAME_CLASS, clazz.name)
            startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_test)

        val fragmentName = intent.getStringExtra(KEY_FRAGMENT_NAME_CLASS)
        if (!fragmentName.isNullOrBlank()) {
            val clazz = classLoader.loadClass(fragmentName)
            if (Fragment::class.java.isAssignableFrom(clazz)) {
                val fragment: Fragment = clazz.newInstance() as Fragment
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_content, fragment)
                    .commit()
            }

        }
    }
}