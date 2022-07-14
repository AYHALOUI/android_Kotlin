package com.store.wordapp

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.store.wordapp.databinding.FragmentLetterListBinding


class LetterListFragment : Fragment() {

    private var isLinearLayout = true
    private var _binding: FragmentLetterListBinding? = null
    private val binding get() = _binding!!
    lateinit var recycleView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLetterListBinding
            .inflate(inflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleView = binding.recycleView

        chooseLayout()
        recycleView.adapter = LatterAdapter(this.requireContext())

    }


    private fun chooseLayout() {
        if (isLinearLayout) recycleView.layoutManager = LinearLayoutManager(context)
        else recycleView.layoutManager = GridLayoutManager(context, 4)
    }

    private fun setIcon(menuItem: MenuItem) {
        if (menuItem == null)
            return
        menuItem.icon =
            if (isLinearLayout) ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_linear_layout
            )
            else ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid_layout)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater
            .inflate(R.menu.layout_menu, menu)
        val menuButton = menu.findItem(R.id.action_switch_layout)
        setIcon(menuButton)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                isLinearLayout = !isLinearLayout
                chooseLayout()
                setIcon(item)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


}