package com.meo209.mlib.gui

import com.meo209.mlib.MLib
import com.meo209.mlib.Surface
import net.minecraft.client.gui.DrawContext
import org.joml.Vector2i

open class MScreen {

    var title: String = ""
    var surface: Surface = Surface.OPAQUE
    private val widgets = mutableListOf<Widget>()

    open fun build() {}

    fun widgets(initializer: WidgetsBuilder.() -> Unit) {
        val builder = WidgetsBuilder()
        builder.initializer()
        builder.build().forEach { widget ->
            widgets.add(widget)
        }
    }

    fun render(context: DrawContext, mouse: Vector2i) {
        widgets.forEach { widget ->
            if (widget.visible)
                widget.render(context, mouse)
        }
    }

    fun mouseClicked(mouse: Vector2i, button: Int) {
        widgets.forEach { widget ->
            if (widget.visible && widget.isMouseOver(mouse))
                widget.handleMouseClick(mouse, button)
        }
    }

    fun <T : Widget> modify(id: String, type: Class<T>, action: (T) -> Unit) {
        val widget = widgets.find { it.id == id }
        if (widget != null && type.isInstance(widget)) {
            @Suppress("UNCHECKED_CAST")
            action(widget as T)
        } else {
            MLib.errorOutput("Widget with ID $id not found or type mismatch.")
        }
    }
}
