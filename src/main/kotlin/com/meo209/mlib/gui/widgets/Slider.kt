package com.meo209.mlib.gui.widgets

import com.meo209.mlib.gui.Widget
import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper
import org.joml.Vector2i
import java.math.BigDecimal
import java.math.RoundingMode

class Slider(id: String) : Widget(id) {

    var value: Double = 0.0
        set(newValue) {
            field = MathHelper.clamp(newValue, minRange, maxRange)
        }

    var active: Boolean = true

    private var decimalPlaces = 0
    private var minRange = 0.0
    private var maxRange = 1.0
    private var textSegments = emptyList<String>()

    private val textures = mapOf(
        "default" to Identifier.ofVanilla("widget/slider"),
        "highlighted" to Identifier.ofVanilla("widget/slider_highlighted"),
        "handle" to Identifier.ofVanilla("widget/slider_handle"),
        "handleHighlighted" to Identifier.ofVanilla("widget/slider_handle_highlighted")
    )

    init {
        value = minRange
    }

    fun activate() {
        active = true
    }

    fun deactivate() {
        active = false
    }

    fun numberSegment(places: Int, minValue: Double, maxValue: Double) {
        decimalPlaces = places
        minRange = minValue
        maxRange = maxValue
        textSegments = emptyList()
        value = minRange
    }

    fun textSegment(vararg labels: String) {
        textSegments = labels.toList()
        decimalPlaces = 0
        minRange = 0.0
        maxRange = (labels.size - 1).toDouble()
        value = minRange
    }

    override fun render(context: DrawContext, mouse: Vector2i) {
        RenderSystem.enableBlend()
        RenderSystem.defaultBlendFunc()
        RenderSystem.enableDepthTest()

        context.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)

        val highlight = isMouseOver(mouse)
        context.drawGuiTexture(
            textures[if (highlight) "highlighted" else "default"]!!,
            position.x, position.y, dimension.x, dimension.y
        )

        val handlePos = ((value - minRange) / (maxRange - minRange) * (dimension.x - 8)).toInt()
        context.drawGuiTexture(
            textures[if (highlight) "handleHighlighted" else "handle"]!!,
            position.x + handlePos, position.y, 8, dimension.y
        )

        val textRenderer = MinecraftClient.getInstance().textRenderer
        val displayText = textSegments.takeIf { it.isNotEmpty() }?.let { getTextSegmentLabel() }
            ?: roundToDecimalPlaces(value, decimalPlaces)
        context.drawCenteredTextWithShadow(
            textRenderer, displayText,
            position.x + dimension.x / 2,
            position.y + (dimension.y - textRenderer.fontHeight) / 2 + 1,
            if (active) 0xFFFFFF else 0xA0A0A0
        )
    }

    override fun handleMouseDrag(mouse: Vector2i, delta: Vector2i, button: Int): Boolean {
        val newValue = minRange + ((mouse.x - position.x).toDouble() / (dimension.x - 8)) * (maxRange - minRange)
        value = if (textSegments.isNotEmpty()) snapToNearestSegment(newValue) else newValue
        return true
    }

    override fun handleMouseClick(mouse: Vector2i, button: Int): Boolean {
        return handleMouseDrag(mouse, Vector2i(), button)
    }

    private fun getTextSegmentLabel() = textSegments[MathHelper.clamp(value.toInt(), 0, textSegments.size - 1)]

    private fun roundToDecimalPlaces(value: Double, places: Int) =
        BigDecimal(value).setScale(places, RoundingMode.HALF_UP).toPlainString()

    private fun snapToNearestSegment(newValue: Double) =
        MathHelper.clamp(Math.round(newValue).toDouble(), 0.0, (textSegments.size - 1).toDouble())

    override fun isMouseOver(mouse: Vector2i): Boolean =
        mouse.x >= position.x - 2 && mouse.x < position.x + dimension.x + 2 &&
                mouse.y >= position.y - 2 && mouse.y < position.y + dimension.y + 2

}
