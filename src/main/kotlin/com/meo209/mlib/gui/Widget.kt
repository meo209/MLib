package com.meo209.mlib.gui

import net.minecraft.client.gui.DrawContext
import org.joml.Vector2i

abstract class Widget(val id: String) {

    var position: Vector2i = Vector2i()
    var dimension: Vector2i = Vector2i()

    var visible: Boolean = true

    fun position(x: Int, y: Int) {
        this.position = Vector2i(x, y)
    }

    fun dimension(width: Int, height: Int) {
        this.dimension = Vector2i(width, height)
    }

    abstract fun render(context: DrawContext, mouse: Vector2i)

    open fun isMouseOver(mouse: Vector2i) =
        mouse.x >= position.x && mouse.x < position.x + dimension.x &&
                mouse.y >= position.y && mouse.y < position.y + dimension.y

    open fun handleMouseClick(mouse: Vector2i, button: Int): Boolean {
        return false
    }

    open fun handleMouseDrag(mouse: Vector2i, delta: Vector2i, button: Int): Boolean {
        return false
    }
}