package com.evajj.ktbase.util

import android.content.res.Resources
import android.util.TypedValue
import com.evajj.ktbase.util.Utils.Companion.context

/**
 * Author:wenjunjie
 * Date:2023/2/27
 * Time:下午8:54
 * Description:
 **/

inline val Float.dp
    get() =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            Resources.getSystem().displayMetrics
        )
inline val Int.dp
    get() = toFloat().dp

inline val Double.dp
    get() = toFloat().dp