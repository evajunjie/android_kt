package com.evajj.module_home.viewmodel

import android.app.Application
import com.evajj.ktbase.base.BaseViewModel
import com.evajj.ktbase.util.LogUtil
import com.evajj.ktnetwork.custom.HomeNetWorkApi.getAppErrorHandler
import com.evajj.ktnetwork.viewmode.request
import com.evajj.module_home.data.entity.FrontPage
import com.evajj.module_home.data.entity.WanResponse
import com.evajj.module_home.model.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Author:wenjunjie
 * Date:2023/2/22
 * Time:上午11:33
 * Description:
 **/
@HiltViewModel
class HomeViewModel @Inject constructor(application: Application ,private val homeRepository: HomeRepository) :BaseViewModel<HomeRepository>(application ,homeRepository) {

   fun loadData(){
      request {
          onRequest {
              LogUtil.d("onRequest ${Thread.currentThread().name}")
              homeRepository.loadNetWorkData()
          }
          onStart {
              LogUtil.d("onStart ${Thread.currentThread().name}")
              false
          }
          onResponse {
              LogUtil.d("onResponse ${Thread.currentThread().name}")
              LogUtil.d("onResponse data = $this")

          }
          onError {
              LogUtil.d("onError ${Thread.currentThread().name}")
              LogUtil.d("onError message ${throwable?.message}")

              false
          }
          onTransform {
              LogUtil.d("onTransform ${Thread.currentThread().name}")

              getAppErrorHandler()
          }
          onFinally {
              LogUtil.d("onFinally ${Thread.currentThread().name}")

              false
          }
      }
  }
}