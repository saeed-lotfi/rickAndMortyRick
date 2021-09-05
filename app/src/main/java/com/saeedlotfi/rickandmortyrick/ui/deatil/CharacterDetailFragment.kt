package com.saeedlotfi.rickandmortyrick.ui.deatil

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.saeedlotfi.rickandmortyrick.base.BaseFragment
import com.saeedlotfi.rickandmortyrick.data.local.model.CharacterDetailModel
import com.saeedlotfi.rickandmortyrick.databinding.FragmentCharacterDetailBinding
import com.saeedlotfi.rickandmortyrick.ui.character.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment<MainViewModel, FragmentCharacterDetailBinding>() {

    @Inject
    lateinit var episodeAdapter: EpisodeAdapter

    private lateinit var character: CharacterDetailModel

    val args: CharacterDetailFragmentArgs by navArgs()

    override fun initialize() {

        manageRecyclerView()

        manageClick()

        setData()

    }

    private fun manageRecyclerView() {
        binding.rcvEpisodes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = episodeAdapter
            hasFixedSize()
        }
    }

    private fun setData() {

        character = args.characterDetailModel

        Glide.with(requireContext()).load(character.imgProfile).into(binding.imgProfile)

        binding.tvGender.text = character.gender
        binding.tvName.text = character.name
        binding.tvStatus.text = character.status
        binding.tvType.text = character.species
        episodeAdapter.submitList(character.episode)
    }

    private fun manageClick() {
        binding.imgClose.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.cons.setOnClickListener {
            if (binding.rcvEpisodes.isVisible) {
                TransitionManager.beginDelayedTransition(
                    binding.cons,
                    AutoTransition()
                )
                binding.rcvEpisodes.visibility = View.GONE

            } else {
                TransitionManager.beginDelayedTransition(
                    binding.cons,
                    AutoTransition()
                )
                binding.rcvEpisodes.visibility = View.VISIBLE
            }
        }

    }

    override val viewModel: MainViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCharacterDetailBinding
        get() = FragmentCharacterDetailBinding::inflate

}