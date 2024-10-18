package com.meo209.mlib

import com.meo209.mlib.gui.MScreen

object MLib {

    internal lateinit var errorOutput: (String) -> Unit

    fun screen(initializer: MScreen.() -> Unit): MScreen {
        val screen = MScreen()
        screen.initializer()
        return screen
    }

}

enum class Surface {
    TRANSPARENT, OPAQUE
}