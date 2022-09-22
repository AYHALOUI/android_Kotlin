package com.training.cupcake.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.training.cupcake.R
import com.training.cupcake.databinding.FragmentPickupBinding
import com.training.cupcake.model.OrderViewModel


class PickupFragment : Fragment() {

    private var binding : FragmentPickupBinding? = null
    private val sharedViewModel : OrderViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentPickup = FragmentPickupBinding.inflate(inflater, container, false)
        binding = fragmentPickup
        return fragmentPickup.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            pickupFragment = this@PickupFragment
        }
    }

    fun goToNextScreen()
    {
        findNavController().navigate(R.id.action_pickupFragment_to_summaryFragment)
    }

    fun cancelOrder(){
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_pickupFragment_to_startFragment)
    }
}