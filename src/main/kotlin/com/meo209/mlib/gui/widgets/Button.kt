package com.meo209.mlib.gui.widgets

import com.meo209.mlib.gui.Widget
import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ButtonTextures
import net.minecraft.util.Identifier
import org.joml.Vector2i

class Button(id: String) : Widget(id) {

    var text: String = ""
    private var onPressAction: (() -> Unit)? = null

    var active: Boolean = true

    private val label: Label = Label("${id}_label").apply {
        centered()
    }

    init {
        addChild(label)
    }

    fun activate() {
        active = true
    }

    fun deactivate() {
        active = false
    }

    private val textures: ButtonTextures = ButtonTextures(
        Identifier.ofVanilla("widget/button"),
        Identifier.ofVanilla("widget/button_disabled"),
        Identifier.ofVanilla("widget/button_highlighted")
    )

    fun onPress(action: () -> Unit) {
        onPressAction = action
    }

    override fun render(context: DrawContext, mouse: Vector2i) {
        context.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
        RenderSystem.enableBlend()
        RenderSystem.enableDepthTest()
        context.drawGuiTexture(
            textures[this.active, isMouseOver(mouse)],
            position.x,
            position.y,
            dimension.width,
            dimension.height
        )

        label.position(position.x + (dimension.width - label.dimension.height) / 2, position.y + (dimension.height - label.dimension.height) / 2)
        label.text = text

        renderChildren(context, mouse)
    }

    override fun handleMouseClick(mouse: Vector2i, button: Int): Boolean {
        if (!active) return false
        onPressAction?.invoke()
        return true
    }
}