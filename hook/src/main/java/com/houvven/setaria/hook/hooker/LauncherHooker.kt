package com.houvven.setaria.hook.hooker

import android.view.View
import android.widget.TextView
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.constructor
import com.highcapable.yukihookapi.hook.factory.field
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.type.android.TextViewClass
import com.houvven.setaria.hook.utils.RClassSigned

class LauncherHooker : YukiBaseHooker() {

    private val rIdClass by lazy { "com.android.launcher3.${RClassSigned.ID}".toClass() }
    private val pressFeedbackButtonClass by lazy { "com.android.launcher.views.PressFeedbackButton".toClass() }

    override fun onHook() {
        removeRecentViewClearBtn()
        removeAppIconBadge()
        removeCardName()
    }

    private fun removeRecentViewClearBtn() {
        val viewId = rIdClass.field { name = "btn_clear" }.get(null).int()
        pressFeedbackButtonClass.constructor().hookAll().after {
            val view = instance<View>()
            if (view.id == viewId) {
                view.visibility = View.GONE
            }
        }
    }

    private fun removeAppIconBadge() {
        "com.android.launcher3.icons.BitmapInfo".toClass().method {
            name = "applyFlags"
        }.hook().replaceTo(Unit)
    }

    private fun removeCardName() {
        val cardNameViewId = rIdClass.field { name = "card_name" }.get(null).int()
        TextViewClass.constructor().hookAll().after {
            val view = instance<TextView>()
            if (view.id == cardNameViewId) {
                view.visibility = View.GONE
            }
        }
    }
}