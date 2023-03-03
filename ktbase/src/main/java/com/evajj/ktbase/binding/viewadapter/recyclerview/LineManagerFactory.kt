package com.evajj.ktbase.binding.viewadapter.recyclerview

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * Author:wenjunjie
 * Date:2023/2/27
 * Time:下午5:31
 * Description:
 **/
interface LineManagerFactory {
    fun create(recyclerView: RecyclerView): ItemDecoration

}