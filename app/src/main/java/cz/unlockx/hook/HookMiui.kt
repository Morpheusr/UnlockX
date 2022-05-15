package cz.unlockx.hook

import android.app.Activity
import android.widget.Toast
import com.github.kyuubiran.ezxhelper.init.EzXHelperInit
import com.github.kyuubiran.ezxhelper.utils.*
import de.robv.android.xposed.*
import de.robv.android.xposed.XC_MethodReplacement.returnConstant
import de.robv.android.xposed.callbacks.XC_LoadPackage

object HookMiui : Hook1() {


    override fun init() {
        findMethod("com.miui.powerkeeper.ui.ThermalConfigActivity") {
            name == "IsInnerNet"
        }.hookAfter{
            it.setResult(true)
            XposedBridge.log("破烂miui温控")
        }

    }
}