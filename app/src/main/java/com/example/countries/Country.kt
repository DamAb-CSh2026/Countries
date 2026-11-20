package com.example.countries

data class Country(
    val id: Int,
    val name: String,
    val capital: String,
    val partOfWorld: String,
    val language: String
)

object CountryRepository {
    val countries = listOf(
        Country(1, "Россия", "Москва", "Европа/Азия", "русский"),
        Country(2, "Франция", "Париж", "Европа", "французский"),
        Country(3, "Германия", "Берлин", "Европа", "немецкий"),
        Country(4, "Италия", "Рим", "Европа", "итальянский"),
        Country(5, "Япония", "Токио", "Азия", "японский"),
        Country(6, "Китай", "Пекин", "Азия", "китайский"),
        Country(7, "Египет", "Каир", "Африка", "арабский"),
        Country(8, "Бразилия", "Бразилиа", "Южная Америка", "португальский")
    )

    fun find(id: Int): Country? = countries.find { it.id == id }
}