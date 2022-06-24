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
import cz.unlockx.hook.*
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.callbacks.XC_LoadPackage

private const val TAG = "xposed-template"
val targets = arrayOf("io.legado.app.release","io.legado.play.release")

class MainHook : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (targets.contains(lpparam.packageName)) {
            // Init EzXHelper
            EzXHelperInit.initHandleLoadPackage(lpparam)
            EzXHelperInit.setLogTag(TAG)
            EzXHelperInit.setToastTag(TAG)
            // Init hooks
            initHooks(HookLegado)
        }else if (lpparam.packageName == "info.muge.appshare") {
            // Init EzXHelper
            EzXHelperInit.initHandleLoadPackage(lpparam)
            EzXHelperInit.setLogTag(TAG)
            EzXHelperInit.setToastTag(TAG)
            // Init hooks
            initHooks(HookAppfx)
        }else if (lpparam.packageName == "com.miui.powerkeeper") {
            // Init EzXHelper
            EzXHelperInit.initHandleLoadPackage(lpparam)
            EzXHelperInit.setLogTag(TAG)
            EzXHelperInit.setToastTag(TAG)
            // Init hooks
            initHooks(HookMiui)
        }else if (lpparam.packageName == "com.plagh.heartstudy") {
            // Init EzXHelper
            EzXHelperInit.initHandleLoadPackage(lpparam)
            EzXHelperInit.setLogTag(TAG)
            EzXHelperInit.setToastTag(TAG)
            // Init hooks
            initHooks(HookWaWei)
        }else if (lpparam.packageName == "com.miui.securitycenter") {
            // Init EzXHelper
            EzXHelperInit.initHandleLoadPackage(lpparam)
            EzXHelperInit.setLogTag(TAG)
            EzXHelperInit.setToastTag(TAG)
            // Init hooks
            initHooks(HookMiui1)
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
}