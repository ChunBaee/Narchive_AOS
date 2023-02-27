package com.chunb.narchive.presentation.util

import android.animation.ObjectAnimator
import android.util.Log
import android.view.View


fun getCloseAnim(view : View) : ObjectAnimator {
    return ObjectAnimator.ofFloat(view, "translationX", 0F)
        .setDuration(1000)
}

fun getOpenAnim(view : View) : ObjectAnimator {
    return ObjectAnimator.ofFloat(view, "translationX", view.width.toFloat())
        .setDuration(1000)
}

fun changeYFromTouch(view : View, oldY : Float, newY : Float) : ObjectAnimator{
    return ObjectAnimator.ofFloat(view, "translationY", oldY, newY).setDuration(0)
}

fun getOpenDrawerAnim(view : View) : ObjectAnimator {
    return ObjectAnimator.ofFloat(view, "translationY", view.height.toFloat())
        .setDuration(500)
}

fun getOpenDrawerAlphaAnim(view : View) : ObjectAnimator {
    return ObjectAnimator.ofFloat(view, "alpha", 0F, 1F).setDuration(500)
}

fun getCloseDrawerAnim(view : View) : ObjectAnimator {
    return ObjectAnimator.ofFloat(view, "translationY", 0F)
        .setDuration(500)
}

fun getCloseDrawerAlphaAnim(view : View) : ObjectAnimator {
    return ObjectAnimator.ofFloat(view, "alpha", 1F, 0F).setDuration(500)
}

