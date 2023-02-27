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
    return ObjectAnimator.ofFloat(view, "translationX", 0F)
        .setDuration(1000)
}

fun getCloseDrawerAnim(view : View) : ObjectAnimator {
    Log.d("----", "getOpenDrawerAnim: CALLED")
    return ObjectAnimator.ofFloat(view, "translationX", view.width.toFloat())
        .setDuration(1000)
}

