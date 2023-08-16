package com.softeer.cartalog.data.model

import java.io.Serializable

data class TypeHmgData(
    val name: String,
    val value: Float,
    val rpm: String,
    val measure: String
) : Serializable