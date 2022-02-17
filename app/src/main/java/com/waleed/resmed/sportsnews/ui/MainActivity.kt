package com.waleed.resmed.sportsnews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.waleed.resmed.sportsnews.Network.News.NewsApi
import com.waleed.resmed.sportsnews.Network.News.NewsProvider
import com.waleed.resmed.sportsnews.R
import com.waleed.resmed.sportsnews.ui.Adapter.MainActivityNewsAdapter
import com.waleed.resmed.sportsnews.ui.ViewModelFactory.SportsViewModelFactory
import com.waleed.resmed.sportsnews.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var newsApi : NewsProvider

    lateinit var activityMainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsApi = NewsApi()
        val vm : MainActivityViewModel by viewModels{
            SportsViewModelFactory( MainActivityViewModel.Args(newsApi),MainActivityViewModel.Args::class.java )
        }


        val binding  = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)

        val recyclerView = binding.newsRecyclerViewActivityMain.apply {
            layoutManager= LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
        }
        binding.lifecycleOwner = this
        binding.viewModel = vm
        lifecycleScope.launchWhenStarted {
            launch {
                vm.news.collect{
                    if(it.isNotEmpty()){

                        recyclerView.adapter = MainActivityNewsAdapter(it)
                    }
                }
            }
        }

    }
}