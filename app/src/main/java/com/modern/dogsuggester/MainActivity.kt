package com.modern.dogsuggester

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabasewithpaging3.Adapter.DogsAdapter
import com.example.roomdatabasewithpaging3.ui.MainViewModel
import com.modern.dogsuggester.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    //private val mainViewModel:MainViewModel  by viewModels()
    @Inject
    lateinit var recyclerViewAdapter: DogsAdapter
    @ExperimentalPagingApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        createdata()
    }

    @ExperimentalPagingApi
    private fun createdata() {
        val mainviewmodal = ViewModelProvider(this).get(MainViewModel::class.java)
        lifecycleScope.launchWhenStarted {
            mainviewmodal.getAllDogs().collectLatest { response ->
                binding.apply {
                    recyclerView.isVisible = true
                    //binding.progressBar.visibility= View.GONE
                }
                //Log.d("main", "onCreate: $response")
                recyclerViewAdapter.submitData(response)
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration =
                DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = DogsAdapter()
            adapter = recyclerViewAdapter

        }
    }
}