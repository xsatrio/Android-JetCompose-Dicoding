package com.xsat.bocchitherock.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xsat.bocchitherock.data.BocchiRepository
import com.xsat.bocchitherock.ui.screen.detail.DetailViewModel
import com.xsat.bocchitherock.ui.screen.favorite.FavoriteViewModel
import com.xsat.bocchitherock.ui.screen.home.HomeViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: BocchiRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository)
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(repository)
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel(repository)
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        } as T
    }
}