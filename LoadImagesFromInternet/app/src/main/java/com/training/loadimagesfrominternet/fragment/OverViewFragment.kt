package com.training.loadimagesfrominternet.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.training.loadimagesfrominternet.OverviewViewModel
import com.training.loadimagesfrominternet.PhotoGridAdapter
import com.training.loadimagesfrominternet.R
import com.training.loadimagesfrominternet.databinding.FragmentOverViewBinding
import com.training.loadimagesfrominternet.databinding.GridViewItemBinding


class OverViewFragment : Fragment() {

    private val viewModel : OverviewViewModel by viewModels()
    private val binding : FragmentOverViewBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val binding = FragmentOverViewBinding.inflate(inflater)
        val binding = FragmentOverViewBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.photosGrid.adapter = PhotoGridAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
