package com.houvven.setaria.hook.hooker

import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.type.android.IntentClass
import com.highcapable.yukihookapi.hook.type.java.BooleanType
import com.highcapable.yukihookapi.hook.type.java.StringClass
import com.houvven.setaria.hook.SetariaBaseHooker
import org.luckypray.dexkit.DexKitBridge


class GamesHooker : SetariaBaseHooker() {

    private val dexkit by lazy { DexKitBridge.create(appInfo.sourceDir) }

    override fun onHook() {
        disableSplashVideo()
        removeGameAssistKeyBlacklist()
    }

    private fun disableSplashVideo() {
        dexkit.findMethod {
            matcher {
                declaredClass("com.nearme.gamespace.desktopspace.splash.DesktopSpaceSplashController")
                paramTypes(IntentClass)
                returnType(BooleanType)
                usingStrings("checkShowSplash", "DesktopSpaceMainFragment")
            }
        }.single().getMethodInstance(requireNotNull(appClassLoader)).hook().replaceToFalse()
    }

    private fun removeGameAssistKeyBlacklist() {
        "business.module.assistkey.GameAssistKeyFeature".toClass()
            .method {
                name = "isFeatureEnabled"
                param(StringClass)
            }.hook().replaceToTrue()
    }
}