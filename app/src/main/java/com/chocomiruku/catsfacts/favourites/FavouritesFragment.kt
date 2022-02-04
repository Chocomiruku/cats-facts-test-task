package com.chocomiruku.catsfacts.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chocomiruku.catsfacts.adapters.FactAdapter
import com.chocomiruku.catsfacts.databinding.FragmentFavouritesBinding

class FavouritesFragment : Fragment() {

    private val viewModel: FavouritesViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, FavouritesViewModelFactory(activity.application)).get(
            FavouritesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFavouritesBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.factsList.adapter = FactAdapter(FactAdapter.OnClickListener {
            viewModel.displayFactDetails(it)
        })

        viewModel.navigateToSelectedFact.observe(this, {
            if ( null != it ) {
                this.findNavController().navigate(FavouritesFragmentDirections.actionFavouritesFragmentToFactDetailsFragment(it))
                viewModel.displayFactDetailsComplete()
            }
        })

        return binding.root
    }
}