package com.training.cupcake.fragment

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.training.cupcake.R
import com.training.cupcake.databinding.FragmentSummaryBinding
import com.training.cupcake.model.OrderViewModel

class SummaryFragment : Fragment() {

    private var binding : FragmentSummaryBinding? = null
    private val sharedViewModel : OrderViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val framentSummaty = FragmentSummaryBinding.inflate(inflater, container, false)
        binding = framentSummaty
        return framentSummaty.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            summaryFragment = this@SummaryFragment
        }
    }

    fun sendOrder()
    {
        val numberOfCupCakes = sharedViewModel.quantity.value ?: 0
        val orderSummary = getString(
            R.string.order_details,
            resources.getQuantityString(R.plurals.cupcakes, numberOfCupCakes, numberOfCupCakes),
            sharedViewModel.flavor.value.toString(),
            sharedViewModel.date.toString(),
            sharedViewModel.price.value.toString()
        )

        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.new_cupcake_order))
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary)
        if(activity?.packageManager?.resolveActivity(intent, 0) != null){
            startActivity(intent)
        }
    }
    fun cancelOrder(){
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_summaryFragment_to_startFragment)
    }
}