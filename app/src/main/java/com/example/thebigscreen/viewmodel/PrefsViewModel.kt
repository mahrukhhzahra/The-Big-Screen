package com.example.thebigscreen.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thebigscreen.data.repository.PrefsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrefsViewModel @Inject constructor(private val prefsRepository: PrefsRepository) :
    ViewModel() {

    fun updateIncludeAdult(includeAdult: Boolean) {
        viewModelScope.launch {
            prefsRepository.updateIncludeAdult(includeAdult = includeAdult)
        }
    }

    val includeAdult: State<Flow<Boolean?>> = mutableStateOf(prefsRepository.readIncludeAdult())
}