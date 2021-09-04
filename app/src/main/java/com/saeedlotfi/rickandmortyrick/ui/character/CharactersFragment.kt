package com.saeedlotfi.rickandmortyrick.ui.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.saeedlotfi.rickandmortyrick.base.BaseFragment
import com.saeedlotfi.rickandmortyrick.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment : BaseFragment<MainViewModel, FragmentCharactersBinding>() {

    @Inject
    lateinit var charactersAdapter: CharactersAdapter

    override fun initialize() {

        initRecyclerView()

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.characters.observe(viewLifecycleOwner, {
            charactersAdapter.submitList(it.characterResponseModels)
        })
    }

    private fun initRecyclerView() {
        binding.rcvCharacters.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = charactersAdapter
            hasFixedSize()
        }
    }

    override fun manageProgressBar(isVisible: Boolean) {
        binding.root.setProgressVisibility(isVisible)
    }

    override val viewModel: MainViewModel by activityViewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCharactersBinding =
        FragmentCharactersBinding::inflate

}