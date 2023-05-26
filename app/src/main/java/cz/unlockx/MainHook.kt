package cz.unlockx

import com.github.kyuubiran.ezxhelper.init.EzXHelperInit
import com.github.kyuubiran.ezxhelper.utils.Log
import com.github.kyuubiran.ezxhelper.utils.Log.logexIfThrow
import cz.unlockx.hook.*
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XSharedPreferences
import de.robv.android.xposed.callbacks.XC_LoadPackage


private const val TAG = "xposed-template"
val targets = arrayOf("io.legado.app.release", "io.legado.play.release")

class MainHook : IXposedHookLoadPackage {

    private var cz = XSharedPreferences("cz.unlockx", "MainActivity");

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {

        if (targets.contains(lpparam.packageName)) {
            // Init EzXHelper
            if (cz.getBoolean("legado_on",true)) {
                EzXHelperInit.initHandleLoadPackage(lpparam)
                EzXHelperInit.setLogTag(TAG)
                EzXHelperInit.setToastTag(TAG)
                // Init hooks
                initHooks(HookLegado)
            }
        } else if (lpparam.packageName == "info.muge.appshare") {
            // Init EzXHelper
            if (cz.getBoolean("appfx_on",true)) {
                EzXHelperInit.initHandleLoadPackage(lpparam)
                EzXHelperInit.setLogTag(TAG)
                EzXHelperInit.setToastTag(TAG)
                // Init hooks
                initHooks(HookAppfx)
            }
        } else if (lpparam.packageName == "com.miui.powerkeeper") {
            // Init EzXHelper
            if (cz.getBoolean("Thermal_on",true)) {
                EzXHelperInit.initHandleLoadPackage(lpparam)
                EzXHelperInit.setLogTag(TAG)
                EzXHelperInit.setToastTag(TAG)
                // Init hooks
                initHooks(HookMiui)
            }
        }
    }
}


private fun initHooks(vararg hook: Hook1) {
    hook.forEach {
        runCatching {
            if (it.isInit) return@forEach
            it.init()
            it.isInit = true
            Log.i("Inited hook: ${it.javaClass.simpleName}")
        }.logexIfThrow("Failed init hook: ${it.javaClass.simpleName}")
    }
}