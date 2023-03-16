package com.bebooo43.countriesapp.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bebooo43.countriesapp.models.CountryUiModel
import com.bebooo43.countriesapp.repo.CountriesRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainViewModel(
    private val repo: CountriesRepo,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default
) : ViewModel() {

    val countriesUiList = MutableLiveData<List<CountryUiModel>>()
    val errorMessage = MutableLiveData<String>()
    val isErrorVisible = MediatorLiveData<Boolean>().apply {
        addSource(errorMessage) {
            value = it?.isNotEmpty()
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        val message = when (throwable) {
            is IOException -> "Network error"
            is HttpException -> {
                "${throwable.code()}, ${throwable.message()}"
            }
            else -> {
                throwable.message?: "Something went wrong"
            }
        }
        showError(message)
    }

    init {
        fetchData()
    }


    private fun fetchData() {
            viewModelScope.launch(coroutineDispatcher + exceptionHandler) {
                val result = repo.getAllCountries()
                if (result.isSuccessful) {
                    val uiModels = result.body()?.map { it.toUiModel() }?: arrayListOf()
                    countriesUiList.postValue(uiModels)
                } else {
                    showError("${result.errorBody()?.string()}")
                }
            }
    }

    private fun showError(message: String) {
        errorMessage.postValue(message)
        countriesUiList.postValue(arrayListOf())
    }

    fun onRetryClick(){
        errorMessage.postValue("")
        fetchData()
    }
}