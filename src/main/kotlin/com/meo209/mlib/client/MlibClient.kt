package com.meo209.mlib.client

import com.meo209.mlib.Surface
import com.meo209.mlib.gui.MScreen
import com.meo209.mlib.gui.widgets.Label
import net.fabricmc.api.ClientModInitializer

class MlibClient : ClientModInitializer {

    override fun onInitializeClient() {

    }
}

class TestScreen : MScreen() {

    override fun build() {
        surface = Surface.TRANSPARENT

        widgets {

            button("disableButton") {
                text = "Disable Button"
                position(10, 20)
                dimension(100, 20)

                onPress {
                    println("Button pressed")

                    deactivate()
                }
            }

            button("labelToggleButton") {
                text = "Toggle Label"
                position(10, 50)
                dimension(100, 20)

                child(label("label") {
                    text = "Not pressed."
                    position(120, 50)
                })

                onPress {
                    println("Button pressed")

                    modify("label", Label::class.java) { label ->
                        label.text = "Pressed!"
                    }
                }
            }

        }
    }
}
