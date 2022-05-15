package cz.unlockx.hook

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import de.robv.android.xposed.XC_MethodReplacement

object HookLegado : Hook1() {

    override fun init() {
        findMethod("io.legado.app.help.SourceHelp") {
            name == "is18Plus"
        }.hookAfter{
            it.setResult(false)
        }
        findMethod("io.legado.play.help.SourceHelp") {
            name == "is18Plus"
        }.hookAfter{
            it.setResult(false)
        }

    }
}