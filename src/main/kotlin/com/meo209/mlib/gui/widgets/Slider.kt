package com.meo209.mlib.gui.widgets

import com.meo209.mlib.gui.Widget
import net.minecraft.client.gui.DrawContext
import org.joml.Vector2i

class Slider(id: String) : Widget(id) {

    var value: Int = 0
    var minValue: Int = 0
    var maxValue: Int = 100

    override fun render(context: DrawContext, mouse: Vector2i) {

    }

}