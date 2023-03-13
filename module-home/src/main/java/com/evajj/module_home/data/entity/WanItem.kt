package com.evajj.module_home.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "Wan")
data class WanItem(@PrimaryKey(autoGenerate = true) val id :Int?, val host :String?)
