package com.nadhifhayazee.testing.commons

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.loadImage(url:String?) {
    Glide.with(context)
        .load(url)
        .into(this)
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

