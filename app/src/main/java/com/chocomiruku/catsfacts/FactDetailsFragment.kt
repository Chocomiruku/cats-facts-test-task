package com.chocomiruku.catsfacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chocomiruku.catsfacts.databinding.FragmentFactDetailsBinding

class FactDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFactDetailsBinding.inflate(inflater)
        val view = binding.root

        return view
    }
}