package com.evajj.module_home.data.source.http

import android.util.Log
import com.evajj.ktbase.util.LogUtil
import com.evajj.ktnetwork.custom.HomeNetWorkApi
import com.evajj.module_home.data.entity.FrontPage
import com.evajj.module_home.data.entity.WanResponse
import com.evajj.module_home.data.source.HttpDataSource
import com.evajj.module_home.data.source.http.service.HomeServiceApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Author:wenjunjie
 * Date:2023/2/21
 * Time:下午5:55
 * Description:
 **/
class HttpDataSourceImpl @Inject constructor(private val homeServiceApi: HomeServiceApi) :
    HttpDataSource {
    override suspend fun loadNetWorkData(): Flow<WanResponse<FrontPage>> =
        flow {
            LogUtil.d("loadNetWorkData Thread${Thread.currentThread().name}")
            emit(homeServiceApi.loadFrontPage())
        }

}