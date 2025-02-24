package com.xsat.bocchitherock.data

import com.xsat.bocchitherock.model.Bocchi
import com.xsat.bocchitherock.model.BocchiData
import com.xsat.bocchitherock.model.BocchiFavorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class BocchiRepository {

    private val bocchiFavorites = mutableListOf<BocchiFavorite>()

    init {
        if (bocchiFavorites.isEmpty()) {
            BocchiData.Bocchi.forEach {
                bocchiFavorites.add(BocchiFavorite(it.id, it.name))
            }
        }
    }

    fun getBocchi(): List<Bocchi> {
        return BocchiData.Bocchi
    }

    fun searchBocchi(query: String): List<Bocchi>{
        return BocchiData.Bocchi.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun getBocchiById(id: String): Flow<Bocchi?> = flow {
        emit(BocchiData.Bocchi.find { it.id == id })
    }.flowOn(Dispatchers.IO)

    fun getFavoriteBocchi(): Flow<List<BocchiFavorite>> {
        return flowOf(bocchiFavorites)
    }

    fun updateBocchiFavorite(id: String, name: String): Flow<Boolean>  {
        val index = bocchiFavorites.indexOfFirst { it.id == id }
        val result = if (index >= 0) {
            val bocchiFavorite = bocchiFavorites[index]
            bocchiFavorites[index] =
                bocchiFavorite.copy(id = bocchiFavorite.id, name = bocchiFavorite.name)
            true
        } else {
            bocchiFavorites.add(BocchiData.Bocchi.find { it.id == id }?.let { BocchiFavorite(it.id, it.name) }!!)
            false
        }
        return flowOf(result)
    }

    fun getBocchiFavorite(): Flow<List<BocchiFavorite>> {
        return getFavoriteBocchi()
            .map { favorite ->
                favorite.filter { favorite ->
                    BocchiData.Bocchi.find { it.id == favorite.id } != null
                }
            }
    }
}