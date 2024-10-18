package com.meo209.mlib.gui

import net.minecraft.client.gui.DrawContext
import org.joml.Vector2i

abstract class Widget(val id: String) {

    var position: Vector2i = Vector2i()
    var dimension: Vector2i = Vector2i()

    var visible: Boolean = true

    protected var parent: Widget? = null
    protected val children = mutableListOf<Widget>()

    fun position(x: Int, y: Int) {
        this.position = Vector2i(
            if (parent == null) x else parent!!.position.x + x,
            if (parent == null) y else parent!!.position.y + y
        )
    }

    fun dimension(width: Int, height: Int) {
        this.dimension = Vector2i(
            if (parent == null) width else parent!!.dimension.x + width,
            if (parent == null) height else parent!!.dimension.y + height
        )
    }

    fun <T : Widget> child(vararg children: T) =
        this.children.addAll(children)
            .also { children.forEach { child -> child.parent = this } }
            .let { children }

    fun <T : Widget> child(children: T) =
        this.children.add(children)
            .let { children }

    @Suppress("UNCHECKED_CAST")
    fun <T : Widget> getCastedParent() =
        parent as T

    abstract fun render(context: DrawContext, mouse: Vector2i)

    open fun isMouseOver(mouse: Vector2i) =
        mouse.x >= position.x && mouse.x < position.x + dimension.x &&
                mouse.y >= position.y && mouse.y < position.y + dimension.y

    open fun handleMouseClick(mouse: Vector2i, button: Int): Boolean {
        return false
    }
}