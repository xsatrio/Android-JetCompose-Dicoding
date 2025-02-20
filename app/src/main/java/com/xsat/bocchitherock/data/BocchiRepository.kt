package com.xsat.bocchitherock.data

import com.xsat.bocchitherock.model.Bocchi
import com.xsat.bocchitherock.model.BocchiData

class BocchiRepository {
    fun getBocchi(): List<Bocchi> {
        return BocchiData.Bocchi
    }

    fun searchBocchi(query: String): List<Bocchi>{
        return BocchiData.Bocchi.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
}