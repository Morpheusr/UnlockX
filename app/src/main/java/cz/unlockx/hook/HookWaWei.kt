package cz.unlockx.hook

import android.app.Activity
import android.app.AndroidAppHelper
import android.content.Context
import android.widget.Toast
import com.github.kyuubiran.ezxhelper.utils.*
import de.robv.android.xposed.*


object HookWaWei : Hook1() {


    override fun init() {
        findMethod("com.plagh.heartstudy.view.activity.SplashActivity") {
            name == "b"
        }.hookAfter{
            Toast.makeText(it.thisObject as Activity, "华为,柠檬什么时候酸啊", Toast.LENGTH_LONG).show()
            it.setResult(true)
            val context: Context = AndroidAppHelper.currentApplication()
        }

    }
}