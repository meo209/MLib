package com.meo209.mlib.gui.widgets

import com.meo209.mlib.gui.Widget
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import org.joml.Vector2i

class Label(id: String) : Widget(id) {

    var text: String = ""

    private var centered: Boolean = false

    fun centered() {
        centered = true
    }

    fun uncentered() {
        centered = false
    }

    override fun render(context: DrawContext, mouse: Vector2i) {
        val textRenderer = MinecraftClient.getInstance().textRenderer

        if (centered) {
            val textY = position.y + (dimension.height - textRenderer.fontHeight) / 2

            context.drawCenteredTextWithShadow(
                textRenderer,
                text,
                position.x + dimension.width / 2,
                textY,
                16777215
            )
        } else {
            context.drawText(
                textRenderer,
                text,
                position.x,
                position.y,
                16777215,
                true
            )
        }
    }

}