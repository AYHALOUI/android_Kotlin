package com.store.wordapp

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.store.wordapp.databinding.FragmentWordListBinding

class WordListFragment : Fragment() {

    private var _binding : FragmentWordListBinding? = null
    private val binding get() = _binding!!
    lateinit var recyclerView: RecyclerView
    private var isLinearLayoutManager = true

    lateinit var letterId : String


    companion object{
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        arguments?.let {
            letterId = it.getString(LETTER).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWordListBinding
            .inflate(inflater,container,false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = binding.recycleView
        chooseLayout()

        recyclerView.addItemDecoration(
            DividerItemDecoration(context,DividerItemDecoration.VERTICAL)
        )

    }


    private fun chooseLayout() {
        if (isLinearLayoutManager)
        {
            recyclerView.layoutManager = LinearLayoutManager(context)
        }else{
            recyclerView.layoutManager = GridLayoutManager(context,4)
        }
        recyclerView.adapter = WordAdapter(letterId,this.requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater
            .inflate(R.menu.layout_menu,menu)
        val layoutButton = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
    }

    private fun setIcon(layoutButton: MenuItem?) {
        if (layoutButton == null)
            return
        layoutButton.icon =
            if (isLinearLayoutManager) ContextCompat.getDrawable(this.requireContext(),R.drawable.ic_linear_layout)
            else ContextCompat.getDrawable(this.requireContext(),R.drawable.ic_grid_layout)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId)
        {
            R.id.action_switch_layout -> {
             isLinearLayoutManager =! isLinearLayoutManager
             chooseLayout()
             setIcon(item)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}