package com.saeedlotfi.rickandmortyrick.ui.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.saeedlotfi.rickandmortyrick.base.BaseFragment
import com.saeedlotfi.rickandmortyrick.data.local.model.CharacterDetailModel
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

        manageClick()
    }

    private fun manageClick() {
        charactersAdapter.click = object : (CharacterDetailModel) -> Unit {
            override fun invoke(model: CharacterDetailModel) {

                val action =
                    CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(
                        model
                    )

                findNavController().navigate(action)

            }

        }
    }

    private fun observeLiveData() {
        viewModel.characters.observe(viewLifecycleOwner, {
            charactersAdapter.submitList(it.characterResponseModels)
        })

        viewModel.isLoading.observe(viewLifecycleOwner)
        {
            manageProgressBar(it)
        }
    }

    private fun initRecyclerView() {
        binding.rcvCharacters.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = charactersAdapter
            hasFixedSize()
        }
    }

    private fun manageProgressBar(isVisible: Boolean) {
        binding.root.setProgressVisibility(isVisible)
    }

    override val viewModel: MainViewModel by activityViewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCharactersBinding =
        FragmentCharactersBinding::inflate

}