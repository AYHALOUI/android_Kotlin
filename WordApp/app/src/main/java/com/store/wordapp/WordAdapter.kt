package com.store.wordapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.UserDictionary
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale.filter

class WordAdapter(val latterId : String, val context : Context) : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    private val filterWords : List<String>

    init {
        val words = context.resources.getStringArray(R.array.words).toList()
        filterWords = words.filter { it.startsWith(latterId, true) }
            .shuffled()
            .take(5)
            .sorted()
    }

    class WordViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val btn : Button = view.findViewById(R.id.btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val layoutAdapter = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item,parent,false)

        layoutAdapter.accessibilityDelegate = Accessibility
        return WordViewHolder(layoutAdapter)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val item = filterWords[position]

        val context1 = holder.itemView.context

        holder.btn.text = item

        holder.btn.setOnClickListener {
            val query : Uri = Uri.parse("${DetailActivity.SEARCH_PREFIX}${item}")
            val intent : Intent = Intent(Intent.ACTION_VIEW,query)
            context1.startActivity(intent)
        }
    }

    override fun getItemCount() = filterWords.size

    companion object Accessibility : View.AccessibilityDelegate(){
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onInitializeAccessibilityNodeInfo(
            host: View?,
            info: AccessibilityNodeInfo?) {
            super.onInitializeAccessibilityNodeInfo(host, info)

            val customString = host?.context?.getString(R.string.look_up_word)
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info?.addAction(customClick)

        }


    }
}