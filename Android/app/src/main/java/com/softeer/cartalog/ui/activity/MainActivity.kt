package com.softeer.cartalog.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.softeer.cartalog.R
import com.softeer.cartalog.data.remote.api.RetrofitClient
import com.softeer.cartalog.databinding.ActivityMainBinding
import com.softeer.cartalog.ui.dialog.PriceSummaryBottomSheetFragment
import com.softeer.cartalog.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navController = binding.fcContainer.getFragment<NavHostFragment>().navController
        binding.navController = navController
        binding.activity = this@MainActivity
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this
    }

    fun changeTab(idx: Int){
        binding.tlStep.selectTab(binding.tlStep.getTabAt(idx))
    }

    fun openSummaryPage(fragment: PriceSummaryBottomSheetFragment){
        fragment.show(supportFragmentManager,fragment.tag)
    }
}