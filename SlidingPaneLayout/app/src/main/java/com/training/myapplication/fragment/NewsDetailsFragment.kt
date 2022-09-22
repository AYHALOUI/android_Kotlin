package com.training.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import com.training.myapplication.R
import com.training.myapplication.SportsViewModel
import com.training.myapplication.databinding.FragmentNewsDetailsBinding


class NewsDetailsFragment : Fragment() {

    var binding: FragmentNewsDetailsBinding? = null
    private val sportsViewModel: SportsViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentNewsDetails = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        binding = fragmentNewsDetails
        return fragmentNewsDetails.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sportsViewModel.currentSport.observe(this.viewLifecycleOwner){
            binding?.titleDetail?.text = getString(it.titleResourceId)
            binding?.sportsImageDetail?.load(it.imageResourceId)
            binding?.titleDetail?.text = getString(it.titleResourceId)
        }
    }

}