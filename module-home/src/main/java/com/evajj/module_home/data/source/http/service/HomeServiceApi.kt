package com.evajj.module_home.data.source.http.service

import com.evajj.module_home.data.entity.FrontPage
import com.evajj.module_home.data.entity.WanResponse
import retrofit2.http.GET

/**
 * Author:wenjunjie
 * Date:2023/3/3
 * Time:下午2:43
 * Description:
 **/
interface HomeServiceApi {
      @GET("article/list/1/json")
      suspend fun loadFrontPage() : WanResponse<FrontPage>
}