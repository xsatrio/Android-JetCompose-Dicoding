package com.xsat.bocchitherock.data

import com.xsat.bocchitherock.model.Bocchi
import com.xsat.bocchitherock.model.BocchiData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class BocchiRepository {

    private val bocchiList = BocchiData.Bocchi.toMutableList()
    private val favoriteFlow = MutableStateFlow(bocchiList)

    fun getBocchi(): List<Bocchi> {
        return bocchiList
    }

    fun searchBocchi(query: String): List<Bocchi>{
        return BocchiData.Bocchi.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun getBocchiById(id: String): Flow<Bocchi?> = flow {
        emit(bocchiList.find { it.id == id })
    }.flowOn(Dispatchers.IO)

    fun getFavoriteBocchi(): Flow<List<Bocchi>> = favoriteFlow

    suspend fun updateFavorite(id: String, isFavorite: Boolean) {
        withContext(Dispatchers.IO) {
            bocchiList.find { it.id == id }?.let {
                it.isFavorite = isFavorite
                favoriteFlow.value = bocchiList
            }
        }
    }
}