package com.meo209.mlib.client

import com.meo209.mlib.Surface
import com.meo209.mlib.gui.MScreen
import com.meo209.mlib.gui.widgets.Label
import com.meo209.mlib.util.TimeUtil
import net.fabricmc.api.ClientModInitializer
import java.util.Timer
import java.util.TimerTask

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

                onPress {
                    println("Button pressed")

                    modify("pressedLabel", Label::class.java) { label ->
                        label.text = "Pressed!"

                        TimeUtil.runAfter({
                            label.text = "Not Pressed."
                        }, 1250)
                    }
                }
            }

            label("pressedLabel") {
                text = "Not pressed."
                position(120, 50)
            }

            checkbox("testCheckbox") {
                position(10, 80)

                onToggle {
                    modify("checkedLabel", Label::class.java) { label ->
                        if (checked)
                            label.text = "Checked."
                        else
                            label.text = "Not Checked."
                    }
                }
            }

            label("checkedLabel") {
                text = "Not checked."
                position(10, 100)
            }
        }
    }
}
