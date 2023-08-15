package com.softeer.cartalog.data.repository

import com.softeer.cartalog.data.model.Trim
import com.softeer.cartalog.data.model.Type

interface CarRepository {

    suspend fun getTrims(): List<Trim>
    suspend fun getTypes(): List<Type>

}