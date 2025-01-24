package com.houvven.setaria.hook

import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit
import com.houvven.setaria.hook.hooker.GamesHooker


@InjectYukiHookWithXposed(modulePackageName = "com.houvven.setaria")
object HookEntry : IYukiHookXposedInit {

    init {
        System.loadLibrary("dexkit")
    }

    override fun onInit() = configs {
        debugLog {
            tag = "Setaria"
            isDebug = BuildConfig.DEBUG
        }
    }

    override fun onHook() = encase {
        loadApp("com.oplus.games", GamesHooker())
    }
}