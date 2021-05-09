package com.trendyol.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment <VM: BaseViewModel, DB : ViewDataBinding> : Fragment() {
    lateinit var binding: DB
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, provideLayoutResId(), container, false)
        val viewModel = bindViewModel(binding)
        observeProgressLiveData(viewModel)
        return binding.root
    }

    open fun observeProgressLiveData(viewModel: VM) {
        viewModel.progressLiveData.observe(viewLifecycleOwner) {
            if(it == true) {
                showLoading()
            } else {
                hideLoading()
            }
        }
    }

    abstract fun showLoading()

    abstract fun hideLoading()

    abstract fun provideLayoutResId(): Int

    abstract fun bindViewModel(db: DB): VM

}