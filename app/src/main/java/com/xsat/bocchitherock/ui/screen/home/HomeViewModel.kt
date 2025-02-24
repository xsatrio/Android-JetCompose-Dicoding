package com.xsat.bocchitherock.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xsat.bocchitherock.data.BocchiRepository
import com.xsat.bocchitherock.model.Bocchi
import com.xsat.bocchitherock.ui.screen.detail.DetailViewModel
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

class ViewModelFactory(private val repository: BocchiRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
