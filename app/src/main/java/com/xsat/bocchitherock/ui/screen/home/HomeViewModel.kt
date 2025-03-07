package com.xsat.bocchitherock.ui.screen.home

import androidx.lifecycle.ViewModel
import com.xsat.bocchitherock.data.BocchiRepository
import com.xsat.bocchitherock.model.Bocchi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(private val repository: BocchiRepository) : ViewModel() {
    private val _bocchis = MutableStateFlow(
        repository.getBocchi()
    )
    val bocchis: MutableStateFlow<List<Bocchi>> get() = _bocchis

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _bocchis.value = repository.searchBocchi(_query.value)
    }
}