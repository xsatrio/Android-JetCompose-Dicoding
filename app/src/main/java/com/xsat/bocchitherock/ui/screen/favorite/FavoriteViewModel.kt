package com.xsat.bocchitherock.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xsat.bocchitherock.data.BocchiRepository
import com.xsat.bocchitherock.model.Bocchi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: BocchiRepository
) : ViewModel() {

    private val _favoriteBocchi = MutableStateFlow<List<Bocchi>>(emptyList())
    val favoriteBocchi: StateFlow<List<Bocchi>> = _favoriteBocchi

    init {
        viewModelScope.launch {
            repository.getFavoriteBocchi().collectLatest { favorites ->
                _favoriteBocchi.value = favorites.filter { it.isFavorite }  // Hanya yang favorit
            }
        }
    }
}
