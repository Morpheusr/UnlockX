package cz.unlockx

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Switch
import android.widget.Toast
import cn.fkj233.ui.activity.MIUIActivity
import cn.fkj233.ui.activity.data.AsyncInit
import cn.fkj233.ui.activity.data.DefValue
import cn.fkj233.ui.activity.fragment.MIUIFragment
import cn.fkj233.ui.activity.view.SpinnerV
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import cn.fkj233.ui.activity.view.TextV
import cn.fkj233.ui.dialog.MIUIDialog
import cn.fkj233.ui.dialog.NewDialog

class MainActivity : MIUIActivity() {
    private val handler by lazy { Handler(Looper.getMainLooper()) }

    init {
        initView {
            registerMain("设置", false) {
                TextSummaryArrow(
                    TextSummaryV("功能",
                        onClickListener = { showFragment("功能") }
                    )
                )
                TextSummaryArrow(
                    TextSummaryV("MIUI功能",
                        onClickListener = { showFragment("MIUI功能") }
                    )
                )
                TextSummaryArrow(
                    TextSummaryV("大帝提供",
                        onClickListener = { showFragment("大帝提供") }
                    )
                )
            }

            register("功能","功能",false) {
                TitleText("功能开关")

                TextWithSwitch(
                    TextV("APP分享"), SwitchV("appfx_on")
                )
                TextWithSwitch(
                    TextV("阅读 R18"), SwitchV("legado_on")
                )

            }

            register("MIUI功能","MIUI功能" ,true) {
                TitleText("功能开关")

                TextSummaryWithSwitch(
                    TextSummaryV("去除内网检测", tips = "电量与性能温控配置去除内网检测"),
                    SwitchV("Thermal_on")
                )

            }

            register("大帝提供","大帝提供", false) {
                TitleText("让我们一起感谢大帝!")

                TextSummaryWithSwitch(
                    TextSummaryV("移除手机管家非官方来源警告", tips = "手机管家需为6.0.3"),
                    SwitchV("wushi1_on")
                )

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setSP(getPreferences(0))
        super.onCreate(savedInstanceState)
    }

    private fun showToast(string: String) {
        handler.post {
            Toast.makeText(this, string, Toast.LENGTH_LONG).show()
        }
    }
}