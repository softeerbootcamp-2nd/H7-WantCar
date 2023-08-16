package com.softeer.cartalog.data.model

import java.io.Serializable

data class Type(
    val type: String,
    val options: List<TypeOption>
) : Serializable
