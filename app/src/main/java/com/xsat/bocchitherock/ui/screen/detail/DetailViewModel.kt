package com.xsat.bocchitherock.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xsat.bocchitherock.data.BocchiRepository

class DetailViewModel(private val repository: BocchiRepository) : ViewModel() {
    fun getBocchiById(id: String) = repository.getBocchiById(id)
}



class ViewModelFactory(private val repository: BocchiRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}