package com.evajj.module_home.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.evajj.ktbase.base.BaseViewModel
import com.evajj.ktbase.binding.command.BindingAction
import com.evajj.ktbase.binding.command.BindingCommand
import com.evajj.ktbase.util.LogUtil
import com.evajj.ktbase.util.timeStamp
import com.evajj.ktnetwork.custom.HomeNetWorkApi.getAppErrorHandler
import com.evajj.ktnetwork.viewmode.query
import com.evajj.ktnetwork.viewmode.request
import com.evajj.module_home.data.entity.WanItem
import com.evajj.module_home.model.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
//       loadNetWorkData()
//       loadLocalData()
  }

    private fun loadNetWorkData(){
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

    private fun loadLocalData(){
        query{
            onQuery {
                LogUtil.d("onQuery ${Thread.currentThread().name}")
                homeRepository.loadLocalData()
            }
            onStart {
                LogUtil.d("onStart ${Thread.currentThread().name}")
                false
            }
            onResult {
                LogUtil.d("onResult ${Thread.currentThread().name}")
                LogUtil.d("onResult data = $this")

            }
            onError {
                LogUtil.d("onError ${Thread.currentThread().name}")
                LogUtil.d("onError message $message")

                false
            }

            onFinally {
                LogUtil.d("onFinally ${Thread.currentThread().name}")
                false
            }
        }
    }

    private fun insertLocalData(){
          viewModelScope.launch {
              homeRepository.saveLocalData(WanItem("test",timeStamp))
          }
        }



     val loadNetWork  = BindingCommand<Any>(execute = {
         loadNetWorkData()
     })

    val loadLocalData  = BindingCommand<Any>(execute = {
        loadLocalData()
    })

    val insertLocalData  = BindingCommand<Any>(execute = {
        insertLocalData()
    })
}