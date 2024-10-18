package com.meo209.mlib.gui.widgets

import com.meo209.mlib.gui.Widget
import net.minecraft.client.gui.DrawContext
import net.minecraft.util.Identifier
import org.joml.Vector2i

class Checkbox(id: String) : Widget(id) {

    var checked: Boolean = false
    private var onToggleAction: (() -> Unit)? = null

    private
    val selectedHighlightedTexture: Identifier = Identifier.ofVanilla("widget/checkbox_selected_highlighted")

    private
    val selectedTexture: Identifier = Identifier.ofVanilla("widget/checkbox_selected")

    private
    val highlightedTexture: Identifier = Identifier.ofVanilla("widget/checkbox_highlighted")

    private
    val texture: Identifier = Identifier.ofVanilla("widget/checkbox")

    fun onToggle(action: () -> Unit) {
        onToggleAction = action
    }

    fun toggle() {
        checked = !checked
        onToggleAction?.invoke()
    }

    init {
        dimension(17, 17)
    }

    override fun render(context: DrawContext, mouse: Vector2i) {
        val identifier: Identifier = if (this.checked)
            if (isMouseOver(mouse)) selectedHighlightedTexture else selectedTexture
        else
            if (isMouseOver(mouse)) highlightedTexture else texture

        context.drawGuiTexture(identifier, position.x, position.y, 17, 17)
    }

    override fun handleMouseClick(mouse: Vector2i, button: Int): Boolean {
        toggle()
        return true
    }

}