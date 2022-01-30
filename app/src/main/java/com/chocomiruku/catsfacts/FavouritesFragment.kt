package com.chocomiruku.catsfacts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.chocomiruku.catsfacts.databinding.FragmentFactsOverviewBinding
import com.chocomiruku.catsfacts.databinding.FragmentFavouritesBinding
import com.google.android.material.tabs.TabLayout

class FavouritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFavouritesBinding.inflate(inflater)
        val view = binding.root

        return view
    }
}