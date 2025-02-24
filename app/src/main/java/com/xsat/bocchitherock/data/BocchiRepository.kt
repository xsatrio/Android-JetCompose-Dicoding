package com.xsat.bocchitherock.data

import com.xsat.bocchitherock.model.Bocchi
import com.xsat.bocchitherock.model.BocchiData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class BocchiRepository {
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
}