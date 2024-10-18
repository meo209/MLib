package com.meo209.mlib.util

import java.util.*
import java.util.concurrent.TimeUnit

object TimeUtil {

    fun runAfter(task: () -> Unit, delay: Long, timeUnit: TimeUnit = TimeUnit.MILLISECONDS) {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                task()
            }
        }, timeUnit.toMillis(delay))
    }

}