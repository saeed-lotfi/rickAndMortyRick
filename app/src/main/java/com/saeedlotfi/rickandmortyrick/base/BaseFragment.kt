package com.saeedlotfi.rickandmortyrick.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.saeedlotfi.rickandmortyrick.R
import com.saeedlotfi.rickandmortyrick.data.local.Failure


abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding> : Fragment() {

    protected abstract val viewModel: VM


    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    val binding get() = _binding!!

    private var _binding: VB? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeErrorLiveData()

        observeLoadingLiveData()

        initialize()

    }

    private fun observeLoadingLiveData() {
        viewModel.isLoading.observe(viewLifecycleOwner)
        {
            manageProgressBar(it)
        }
    }

    abstract fun manageProgressBar(isVisible: Boolean)

    private fun observeErrorLiveData() {
        viewModel.handleFailure.observe(viewLifecycleOwner) {
            when (it) {
                Failure.EmptyResponse -> {
                    showToast(getString(R.string.error))
                }
                is Failure.GeneralError -> {
                    showToast(getString(R.string.error))
                }
                is Failure.HttpError -> {
                    showToast(it.message)
                }
                Failure.NoConnectivityError -> {
                    showToast(getString(R.string.no_intenet))
                }
                is Failure.UnknownHostError -> {
                    showToast(getString(R.string.error))
                }
            }
        }
    }

    private fun showToast(text: String = "") {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    abstract fun initialize()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}