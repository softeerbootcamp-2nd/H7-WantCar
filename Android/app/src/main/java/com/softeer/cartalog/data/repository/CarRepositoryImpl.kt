package com.softeer.cartalog.data.repository

import android.util.Log
import com.softeer.cartalog.data.model.Trim
import com.softeer.cartalog.data.model.TrimDetail
import com.softeer.cartalog.data.model.Type
import com.softeer.cartalog.data.enums.PriceDataType
import com.softeer.cartalog.data.model.db.MyCar
import com.softeer.cartalog.data.model.db.PriceData
import com.softeer.cartalog.data.model.Trims
import com.softeer.cartalog.data.repository.local.CarLocalDataSource
import com.softeer.cartalog.data.repository.remote.CarRemoteDataSource

class CarRepositoryImpl(
    private val carLocalDataSource: CarLocalDataSource,
    private val carRemoteDataSource: CarRemoteDataSource
) : CarRepository {

        override suspend fun getTrims(): Trims {
        val response = carRemoteDataSource.getTrims()
        return if (response.isSuccessful) response.body()!! else Trims("", arrayListOf())
    }

    override suspend fun setInitialMyCarData(carName: String, trim: Trim) {

        val myCar =
            MyCar(carName, trim.name, trim.exteriorImageUrl, trim.interiorImageUrl, null, null)
        val carId = carLocalDataSource.setInitialMyCar(myCar)

        if (carLocalDataSource.isEmpty(carId)) {
            val priceDataList = mutableListOf<PriceData>()
            trim.defaultInfo?.run {
                modelTypes.forEach {
                    priceDataList.add(
                        PriceData(
                            carId,
                            PriceDataType.fromTypeName(it.type),
                            it.option.id,
                            it.option.name,
                            it.option.price,
                            null,
                            null
                        )
                    )
                }

                exteriorColor.run {
                    priceDataList.add(
                        PriceData(
                            carId,
                            PriceDataType.EXTERIOR_COLOR,
                            null,
                            name,
                            price,
                            null,
                            code
                        )
                    )
                }

                interiorColor.run {
                    priceDataList.add(
                        PriceData(
                            carId,
                            PriceDataType.INTERIOR_COLOR,
                            null,
                            name,
                            price,
                            null,
                            code
                        )
                    )
                }

            }
            carLocalDataSource.setInitialPriceData(priceDataList.toList())
        }
    }

    override suspend fun getTypes(): List<Type> {
        val response = carRemoteDataSource.getTypes()
        return if (response.isSuccessful) response.body()!!.modelTypes else arrayListOf()
    }

    override suspend fun getTrimDetail(modelTypeIds: String, trimId: Int): TrimDetail {
        val response = carRemoteDataSource.getTrimsDetail(modelTypeIds, trimId)
        return if (response.isSuccessful) response.body()!! else TrimDetail(0, 0, 0f)
    }
}