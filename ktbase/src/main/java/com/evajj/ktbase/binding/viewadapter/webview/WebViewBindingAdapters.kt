package com.evajj.ktbase.binding.viewadapter.webview

import android.text.TextUtils
import android.webkit.WebView
import androidx.databinding.BindingAdapter

/**
 * Author:wenjunjie
 * Date:2023/2/28
 * Time:下午5:51
 * Description:
 **/
@BindingAdapter("render")
fun loadHtml(webView: WebView, html: String?) {
    if (!TextUtils.isEmpty(html)) {
        webView.loadDataWithBaseURL(null, html!!, "text/html", "UTF-8", null)
    }
}