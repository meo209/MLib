package com.meo209.mlib.gui

import com.meo209.mlib.gui.widgets.Button
import com.meo209.mlib.gui.widgets.Checkbox
import com.meo209.mlib.gui.widgets.Label
import com.meo209.mlib.gui.widgets.Slider

class WidgetsBuilder {

    private val widgets = mutableListOf<Widget>()

    fun button(id: String, initializer: Button.() -> Unit): Button {
        val button = Button(id)
        button.initializer()
        widgets.add(button)
        return button
    }

    fun label(id: String, initializer: Label.() -> Unit): Label {
        val label = Label(id)
        label.initializer()
        widgets.add(label)
        return label
    }

    fun checkbox(id: String, initializer: Checkbox.() -> Unit): Checkbox {
        val checkbox = Checkbox(id)
        checkbox.initializer()
        widgets.add(checkbox)
        return checkbox
    }

    fun slider(id: String, initializer: Slider.() -> Unit): Slider {
        val slider = Slider(id)
        slider.initializer()
        widgets.add(slider)
        return slider
    }

    fun build(): List<Widget> = widgets
}