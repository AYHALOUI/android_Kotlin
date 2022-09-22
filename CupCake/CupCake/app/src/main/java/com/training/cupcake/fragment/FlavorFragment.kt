package com.training.cupcake.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.training.cupcake.R
import com.training.cupcake.databinding.FragmentFlavorBinding
import com.training.cupcake.model.OrderViewModel


class FlavorFragment : Fragment() {

    private var binding : FragmentFlavorBinding? = null
    private val sharedViewModel : OrderViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val florFragment = FragmentFlavorBinding.inflate(inflater, container, false)
        binding = florFragment
        return florFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            flavorFragment = this@FlavorFragment
        }
    }

    fun goToNextScreen()
    {
        sharedViewModel.setDate("Wed Sep 21")
        findNavController().navigate(R.id.action_flavorFragment_to_pickupFragment)
    }
    fun cancelOrder(){
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_flavorFragment_to_startFragment)
    }
}