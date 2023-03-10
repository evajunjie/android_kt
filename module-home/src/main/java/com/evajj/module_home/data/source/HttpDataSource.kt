package com.evajj.module_home.data.source

import com.evajj.module_home.data.entity.FrontPage
import com.evajj.module_home.data.entity.WanResponse
import kotlinx.coroutines.flow.Flow

/**
 * Author:wenjunjie
 * Date:2023/2/21
 * Time:下午5:47
 * Description:
 **/
interface HttpDataSource {
    suspend fun loadNetWorkData() : Flow<WanResponse<FrontPage>>

}