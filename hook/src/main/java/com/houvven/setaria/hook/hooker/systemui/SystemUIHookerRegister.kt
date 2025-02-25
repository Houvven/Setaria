package com.houvven.setaria.hook.hooker.systemui

import com.houvven.setaria.hook.SetariaBaseHooker

class SystemUIHookerRegister : SetariaBaseHooker() {

    override fun onHook() {
        loadHooker(StatusBarHooker())
    }
}