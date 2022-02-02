package com.chocomiruku.catsfacts.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chocomiruku.catsfacts.adapters.FactAdapter
import com.chocomiruku.catsfacts.databinding.FragmentFactsOverviewBinding


class FactsOverviewFragment : Fragment() {

    private val viewModel: FactsOverviewViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, FactsOverviewViewModelFactory(activity.application)).get(FactsOverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFactsOverviewBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.factsList.adapter = FactAdapter(FactAdapter.OnClickListener {
//            viewModel.displayFactDetails(it)
        })

        viewModel.navigateToSelectedFact.observe(this, Observer {
            if ( null != it ) {
                this.findNavController().navigate(FactsOverviewFragmentDirections.actionFactsOverviewFragmentToFactDetailsFragment())
                viewModel.displayFactDetailsComplete()
            }
        })

        return binding.root
    }
}