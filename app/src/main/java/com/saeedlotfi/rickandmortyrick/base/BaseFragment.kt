package com.saeedlotfi.rickandmortyrick.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.saeedlotfi.rickandmortyrick.data.remote.Failure


abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeErrorLiveData()

        initialize()

    }

    private fun observeErrorLiveData() {
        viewModel.handleFailure.observe(viewLifecycleOwner) {
            when (it) {
                Failure.EmptyResponse -> {
                    showToast()
                }
                is Failure.GeneralError -> {
                    showToast()
                }
                is Failure.HttpError -> {
                    showToast()
                }
                Failure.NoConnectivityError -> {
                    showToast()
                }
                is Failure.UnknownHostError -> {
                    showToast()
                }
            }
        }
    }

    private fun showToast(text: String = "") {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun initialize()
}