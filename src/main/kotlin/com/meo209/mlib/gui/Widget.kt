package com.meo209.mlib.gui

import net.minecraft.client.gui.DrawContext
import org.joml.Vector2i
import java.awt.Dimension

abstract class Widget(val id: String) {

    var position: Vector2i = Vector2i()
    var dimension: Dimension = Dimension()

    var visible: Boolean = true

    private val children = mutableListOf<Widget>()

    fun position(x: Int, y: Int) {
        this.position = Vector2i(x, y)
    }

    fun dimension(width: Int, height: Int) {
        this.dimension.setSize(width, height)
    }

    fun addChild(widget: Widget) {
        children.add(widget)
    }

    abstract fun render(context: DrawContext, mouse: Vector2i)

    open fun isMouseOver(mouse: Vector2i) =
        mouse.x >= position.x && mouse.x < position.x + dimension.width &&
                mouse.y >= position.y && mouse.y < position.y + dimension.height

    open fun handleMouseClick(mouse: Vector2i, button: Int): Boolean {
        for (child in children) {
            if (child.visible && child.isMouseOver(mouse)) {
                if (child.handleMouseClick(mouse, button)) {
                    return true
                }
            }
        }
        return false
    }

    open fun handleMouseDrag(mouse: Vector2i, delta: Vector2i, button: Int): Boolean {
        for (child in children) {
            if (child.visible && child.isMouseOver(mouse)) {
                if (child.handleMouseDrag(mouse, delta, button)) {
                    return true
                }
            }
        }
        return false
    }

    fun renderChildren(context: DrawContext, mouse: Vector2i) {
        for (child in children) {
            if (child.visible) {
                child.render(context, mouse)
            }
        }
    }
}