package com.evajj.ktbase.event

import androidx.lifecycle.LifecycleOwner

/**
 * Author:wenjunjie
 * Date:2023/2/9
 * Time:下午5:31
 * Description:
 **/
class SnackbarMessage: SingleLiveEvent<Int>() {

    fun observe(owner: LifecycleOwner,onNewMessage: (Int) ->Unit){
        super.observe(owner){
           it?.run{
               onNewMessage(this)
           }
        }
    }

}