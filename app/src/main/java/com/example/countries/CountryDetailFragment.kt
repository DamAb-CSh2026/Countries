package com.example.countries

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class CountryDetailFragment : Fragment(R.layout.fragment_country_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textName = view.findViewById<TextView>(R.id.textName)
        val textCapital = view.findViewById<TextView>(R.id.textCapital)
        val textPart = view.findViewById<TextView>(R.id.textPart)
        val textLanguage = view.findViewById<TextView>(R.id.textLanguage)

        val id = arguments?.getInt(ARG_ID, -1) ?: -1
        val country = CountryRepository.find(id)

        if (country == null) {
            textName.text = "Страна не выбрана"
            textCapital.text = "Выберите страну в списке слева."
            return
        }

        textName.text = country.name
        textCapital.text = "Столица: ${country.capital}"
        textPart.text = "Часть света: ${country.partOfWorld}"
        textLanguage.text = "Язык: ${country.language}"
    }

    companion object {
        private const val ARG_ID = "country_id"

        fun newInstance(id: Int) = CountryDetailFragment().apply {
            arguments = bundleOf(ARG_ID to id)
        }
    }
}