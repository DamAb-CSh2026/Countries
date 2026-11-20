package com.example.countries

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment

class CountryListFragment : Fragment(R.layout.fragment_country_list) {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>

    // текущий отфильтрованный список
    private var shown: List<Country> = CountryRepository.countries

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listView = view.findViewById(R.id.listCountries)
        val editSearch = view.findViewById<EditText>(R.id.editSearch)

        adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_activated_1,
            shown.map { it.name }
        )
        listView.adapter = adapter

        // ОСОБОЕ ТРЕБОВАНИЕ: фильтрация по названию
        editSearch.doAfterTextChanged { text ->
            val query = text?.toString()?.trim()?.lowercase().orEmpty()
            shown = if (query.isEmpty()) {
                CountryRepository.countries
            } else {
                CountryRepository.countries.filter {
                    it.name.lowercase().contains(query)
                }
            }
            adapter.clear()
            adapter.addAll(shown.map { it.name })
            adapter.notifyDataSetChanged()
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val id = shown[position].id
            parentFragmentManager.setFragmentResult(
                REQUEST_KEY, bundleOf(KEY_COUNTRY to id)
            )
        }
    }

    companion object {
        const val REQUEST_KEY = "country_selected"
        const val KEY_COUNTRY = "country_id"
    }
}