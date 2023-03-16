package com.bebooo43.countriesapp.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bebooo43.countriesapp.models.Country
import com.bebooo43.countriesapp.models.CountryUiModel
import com.bebooo43.countriesapp.repo.CountriesRepo
import com.bebooo43.countriesapp.repo.remote.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

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

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(coroutineDispatcher) {
            when (val result = repo.getAllCountries()) {
                is ResultWrapper.NetworkError -> showError("Network error")
                is ResultWrapper.GenericError -> showError("${result.code}, ${result.errorMessage}")
                is ResultWrapper.Success -> handleSuccess(result.value)
            }
        }
    }

    private fun handleSuccess(response: Response<List<Country>>) {
        if (response.isSuccessful) {
            val uiModels = response.body()?.map { it.toUiModel() } ?: arrayListOf()
            countriesUiList.postValue(uiModels)
        } else {
            showError("${response.errorBody()?.string()}")
        }
    }

    private fun showError(message: String) {
        errorMessage.postValue(message)
        countriesUiList.postValue(arrayListOf())
    }

    fun onRetryClick() {
        errorMessage.postValue("")
        fetchData()
    }
}