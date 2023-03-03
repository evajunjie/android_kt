package com.evajj.ktbase.binding.viewadapter.image

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation

/**
 * Author:wenjunjie
 * Date:2023/2/23
 * Time:下午4:27
 * Description:
 **/
@BindingAdapter(
    value = ["url", "placeholderRes", "radius", "backgroundDrawable"],
    requireAll = false
)
fun setImageUri(
    imageView: ImageView,
    url: String,
    placeholderRes: Int,
    radius: Float,
    drawable: Drawable
) {

    if (!url.isNullOrEmpty()) {
        //使用Glide框架加载图片
        imageView.load(url){
            placeholder(placeholderRes)
        }
    }

    if (drawable != null) {
        imageView.load(drawable){
            transformations(RoundedCornersTransformation(radius))
        }
    }

}