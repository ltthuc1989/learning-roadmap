package com.example.review

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

sealed interface UiState {
    data object Loading : UiState
    data class Success(val items: List<Transaction>, val hasMore: Boolean) : UiState
    data class Error(val message: String) : UiState
}

/** ViewModel-like class. No Android import so it can be built and tested on the JVM. */
class TransactionsViewModel(
    private val repository: TransactionRepository,
    private val accountId: String,
) {
    private val viewModelScope = CoroutineScope(Dispatchers.Default + Job())

    val state = MutableStateFlow<UiState>(UiState.Loading)

    private val loaded = ArrayList<Transaction>()

    fun load() {
        viewModelScope.launch {
            try {
                val items = repository.loadNextPage(accountId)
                loaded.addAll(items)
                state.value = UiState.Success(loaded, hasMore = items.size == AppConfig.pageSize)
            } catch (e: Exception) {
                state.value = UiState.Error("Something went wrong")
            }
        }
    }

    fun loadMore() {
        viewModelScope.launch {
            val items = repository.loadNextPage(accountId)
            loaded.addAll(items)
            state.value = UiState.Success(loaded, hasMore = items.size == AppConfig.pageSize)
        }
    }

    fun refresh() {
        GlobalScope.launch {
            loaded.clear()
            repository.resetPaging()
            load()
        }
    }

    fun amountAt(index: Int): Long {
        val success = state.value as UiState.Success
        return success.items[index].amountCents
    }

    fun onCleared() {
        // nothing to clean up
    }
}
