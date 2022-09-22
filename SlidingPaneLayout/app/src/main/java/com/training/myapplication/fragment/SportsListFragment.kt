package com.training.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.training.myapplication.SportsAdapter
import com.training.myapplication.SportsViewModel
import com.training.myapplication.databinding.FragmentSportsListBinding


class SportsListFragment : Fragment() {

    var binding: FragmentSportsListBinding?= null
    private val sportsViewModel: SportsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentSport = FragmentSportsListBinding.inflate(inflater, container, false)
        binding = fragmentSport
        return fragmentSport.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val slidingPaneLayout = binding?.slidingPaneLayout
        slidingPaneLayout?.lockMode = SlidingPaneLayout.LOCK_MODE_LOCKED
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            SportsListOnBackPressedCallback(slidingPaneLayout!!)
        )

        val adapter = SportsAdapter{
            sportsViewModel.updateCurrentSport(it)

            val action = SportsListFragmentDirections.actionSportsListFragmentToNewsDetailsFragment()
            this.findNavController().navigate(action)

            binding?.slidingPaneLayout?.openPane()

        }
        binding?.recyclerView?.adapter = adapter
        adapter.submitList(sportsViewModel.sportsData)



    }


}
class SportsListOnBackPressedCallback(
    private val slidingPaneLayout: SlidingPaneLayout
) : OnBackPressedCallback(
    // Set the default 'enabled' state to true only if it is slidable (i.e., the panes
    // are overlapping) and open (i.e., the detail pane is visible).
    slidingPaneLayout.isSlideable && slidingPaneLayout.isOpen
), SlidingPaneLayout.PanelSlideListener {

    init {
        slidingPaneLayout.addPanelSlideListener(this)
    }

    override fun handleOnBackPressed() {
        // Return to the list pane when the system back button is pressed.
        slidingPaneLayout.closePane()
    }

    override fun onPanelSlide(panel: View, slideOffset: Float) {}

    override fun onPanelOpened(panel: View) {
        // Intercept the system back button when the detail pane becomes visible.
        isEnabled = true
    }

    override fun onPanelClosed(panel: View) {
        // Disable intercepting the system back button when the user returns to the
        // list pane.
        isEnabled = false
    }
}