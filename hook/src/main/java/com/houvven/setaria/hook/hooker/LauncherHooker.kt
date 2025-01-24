package com.houvven.setaria.hook.hooker

import android.view.View
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.constructor
import com.highcapable.yukihookapi.hook.factory.field
import com.houvven.setaria.hook.utils.RClassSigned

class LauncherHooker : YukiBaseHooker() {


    private val rIdClass by lazy { "com.android.launcher3.${RClassSigned.ID}".toClass() }
    private val pressFeedbackButtonClass by lazy { "com.android.launcher.views.PressFeedbackButton".toClass() }

    override fun onHook() {
        hideClearBtn()
    }

    private fun hideClearBtn() {
        val viewId = rIdClass.field { name = "btn_clear" }.get(null).int()
        pressFeedbackButtonClass.constructor().hookAll().after {
            val view = instance<View>()
            if (view.id == viewId) {
                view.visibility = View.GONE
            }
        }
    }
}