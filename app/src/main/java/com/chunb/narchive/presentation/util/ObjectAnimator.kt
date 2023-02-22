package com.chunb.narchive.presentation.util

import android.animation.ObjectAnimator
import android.view.View


fun getCloseAnim(view : View) : ObjectAnimator {
    return ObjectAnimator.ofFloat(view, "translationX", 0F)
        .setDuration(1000)
}

fun getOpenAnim(view : View) : ObjectAnimator {
    return ObjectAnimator.ofFloat(view, "translationX", view.width.toFloat())
        .setDuration(1000)
}
