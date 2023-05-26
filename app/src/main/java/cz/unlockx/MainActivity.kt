package cz.unlockx

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import cn.fkj233.ui.activity.MIUIActivity
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import cn.fkj233.ui.activity.view.TextV

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