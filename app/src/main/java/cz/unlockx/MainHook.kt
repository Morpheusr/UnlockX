package cz.unlockx

import android.app.Activity
import android.widget.Toast
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam
import com.github.kyuubiran.ezxhelper.init.EzXHelperInit
import com.github.kyuubiran.ezxhelper.utils.Log
import com.github.kyuubiran.ezxhelper.utils.Log.logexIfThrow
import de.robv.android.xposed.callbacks.XC_LoadPackage

class MainHook : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: LoadPackageParam) {
        if (lpparam.packageName == "info.muge.appshare") {
            hookappfx(lpparam)
        } else if (lpparam.packageName == "com.miui.powerkeeper") {
            hookmiui(lpparam)
        } else if(lpparam.packageName == "io.legado.app.release") {
            hooklegado(lpparam)
        } else if(lpparam.packageName == "io.legado.play.release") {
            hooklegado(lpparam)
        }
    }

    private fun hookappfx(lpparam: LoadPackageParam) {
        var appfx = XposedHelpers.findClass(
            "info.muge.appshare.view.main.MainActivity",
            lpparam.classLoader
        )
        XposedHelpers.findAndHookMethod(appfx, "onResume", object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                Toast.makeText(
                    param.thisObject as Activity,
                    "APP分享可能会倒闭,但绝不会变质!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


    private fun hookmiui(lpparam: LoadPackageParam) {
        var miui = XposedHelpers.findClass(
            "com.miui.powerkeeper.ui.ThermalConfigActivity",
            lpparam.classLoader
        )
        XposedHelpers.findAndHookMethod(miui, "IsInnerNet", object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                param?.result = true
            }
        })
    }

    private fun hookmomo(lpparam: LoadPackageParam) {
        var momo = XposedHelpers.findClass(
            "defpackage.C0672rf",
            lpparam.classLoader
        )
        XposedHelpers.findAndHookMethod(momo,"c",object : XC_MethodHook(){
            override fun beforeHookedMethod(param: MethodHookParam?) {


            }
        })
    }

    private fun hooklegado(lpparam: LoadPackageParam) {
        var legado1 = XposedHelpers.findClass(
            "io.legado.app.help.SourceHelp",
            lpparam.classLoader
        )
        var legado2 = XposedHelpers.findClass(
            "io.legado.play.help.SourceHelp",
            lpparam.classLoader
        )
        XposedHelpers.findAndHookMethod(legado1, "is18Plus", object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                param.result = false
            }
        })
        XposedHelpers.findAndHookMethod(legado2, "is18Plus", object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                param.result = false
            }
        })
    }
}