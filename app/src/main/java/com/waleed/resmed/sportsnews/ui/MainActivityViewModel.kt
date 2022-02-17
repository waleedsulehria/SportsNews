package com.waleed.resmed.sportsnews.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.waleed.resmed.sportsnews.Network.News.NewsProvider
import com.waleed.resmed.sportsnews.models.NewsResult
import com.waleed.resmed.sportsnews.models.humanReadableString
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import kotlin.coroutines.coroutineContext

class MainActivityViewModel(val args : Args) : ViewModel() {
    data class Args( val newsProvider: NewsProvider )


    private val _news : MutableStateFlow<List<NewsResult>> = MutableStateFlow(emptyList())
    val news : StateFlow<List<NewsResult>> = _news.asStateFlow()

    private val _latestDate : MutableStateFlow<String> = MutableStateFlow("")
    val latestDate : StateFlow<String> = _latestDate.asStateFlow()


    private val _isDataAvailable : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isDataAvailable : StateFlow<Boolean> = _isDataAvailable.asStateFlow()

    private val _isLoading : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading : StateFlow<Boolean> = _isLoading.asStateFlow()

    init {


    }


    fun buttonOnClick(){
        viewModelScope.launch {

            getData()
        }
    }

    suspend fun getData(){
            _isLoading.value = true
            args.newsProvider.getNews().let {
                delay(2000)
                _isLoading.value = false
                it.data?.let {
                    _isDataAvailable.emit(true)
                    val newsCombine = flowOf(it.Tennis.asFlow(),it.nbaResults.asFlow(),it.f1Results.asFlow())
                                        .flattenMerge()
                                        .map { it }
                                        .toList()

                    val latestDate = getlatestDate(newsCombine)
                    _latestDate.value  = latestDate.humanReadableString
                    _news.value = getNewsForDate(newsCombine,latestDate)
                }
            }
    }

    private fun getlatestDate(result :  List<NewsResult>) : LocalDateTime{
        return result.maxOf { it.publicationDate }
    }

    private fun getNewsForDate(result : List<NewsResult> , date : LocalDateTime) : List<NewsResult>{
        return result.filter { it -> it.publicationDate.toLocalDate().isEqual(date.toLocalDate()) }
            .sortedByDescending { it.publicationDate }
    }
}