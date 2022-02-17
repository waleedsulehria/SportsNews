package com.waleed.resmed.sportsnews.ui.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SportsViewModelFactory<T>(val args : T,private val argsClass: Class<T>,) : ViewModelProvider.Factory {
    override fun <Y : ViewModel> create(modelClass: Class<Y>): Y = modelClass.getConstructor(argsClass).newInstance(args)
}