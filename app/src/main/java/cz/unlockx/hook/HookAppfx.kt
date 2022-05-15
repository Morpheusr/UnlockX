package cz.unlockx.hook

import android.app.Activity
import android.widget.Toast
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import de.robv.android.xposed.XposedBridge

object HookAppfx : Hook1() {
    override fun init() {
        findMethod("info.muge.appshare.view.main.MainActivity") {
            name == "onResume"
        }.hookAfter {
            XposedBridge.log("APP分享可能会倒闭,但绝不会变质!")
            Toast.makeText(it.thisObject as Activity, "APP分享可能会倒闭,但绝不会变质!", Toast.LENGTH_LONG).show()
        }

    }

}