package com.chocomiruku.catsfacts.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.chocomiruku.catsfacts.databinding.FragmentFactDetailsBinding

class FactDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(activity).application
        val binding = FragmentFactDetailsBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        val fact = FactDetailsFragmentArgs.fromBundle(arguments!!).selectedFact

        val viewModelFactory = FactDetailsViewModelFactory(fact, application)
        val factDetailsViewModel = ViewModelProvider(
            this, viewModelFactory)[FactDetailsViewModel::class.java]
        binding.viewModel = factDetailsViewModel

        binding.favouritesButton.setOnClickListener {
            factDetailsViewModel.updateFavourites()
        }

        return binding.root
    }
}