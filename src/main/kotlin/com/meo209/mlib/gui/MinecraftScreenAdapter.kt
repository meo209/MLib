package com.meo209.mlib.gui

import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text
import org.joml.Vector2i

class MinecraftScreenAdapter(private val screen: MScreen): Screen(Text.literal(screen.title)) {

    override fun init() {
        screen.build()
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        this.renderBackground(context, mouseX, mouseY, delta)

        screen.render(context, Vector2i(mouseX, mouseY))
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        screen.mouseClicked(Vector2i(mouseX.toInt(), mouseY.toInt()), button)
        return true
    }

    override fun mouseDragged(mouseX: Double, mouseY: Double, button: Int, deltaX: Double, deltaY: Double): Boolean {
        screen.mouseDragged(Vector2i(mouseX.toInt(), mouseY.toInt()), Vector2i(deltaX.toInt(), deltaY.toInt()), button)
        return true
    }

}