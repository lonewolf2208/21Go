package com.example.a21go.model

data class JournalsDataClass(
    val id: Int,
    val entry: String,
    val time_created: String,
    val user: Int,
    var title:String="",
    var date:String="",
    var month:String="",
    var year:String=""
)
