package com.meo209.mlib.gui.widgets

import com.meo209.mlib.gui.Widget
import net.minecraft.client.gui.DrawContext
import org.joml.Vector2i

class Checkbox(id: String) : Widget(id) {

    var isChecked: Boolean = false
    private var onToggleAction: (() -> Unit)? = null

    fun onToggle(action: () -> Unit) {
        onToggleAction = action
    }

    fun toggle() {
        isChecked = !isChecked
        onToggleAction?.invoke()
    }

    override fun render(context: DrawContext, mouse: Vector2i) {
        println("Rendering Checkbox. Checked: $isChecked at position: $position")
    }

}