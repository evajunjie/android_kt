package com.evajj.module_home.data.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.evajj.ktbase.util.timeStamp
import java.sql.Timestamp

@Entity(tableName = "Wan")
data class WanItem(@PrimaryKey(autoGenerate = true)
              var id :Int,var host :String = "",var timeStamp :Long = 0L){

    constructor(host: String,timeStamp: Long):this(0,host,timeStamp)

}
