package com.example.iss_tool

enum class Category(private val categoryName: String) {
    A("Category A"),
    B("Category B"),
    Exempt("Exempt Human or Animal Specimen"),
    Exception("Exception");

    override fun toString(): String {
        return categoryName
    }

    companion object {
        fun fromString(categoryName: String): Category? {
            return entries.find { it.categoryName == categoryName }
        }
    }
}

enum class UnSubstance(private val unName: String) {
    ISCategoryA("Infectious Substance Category A"),
    ISHumans("Infectious Substance Affecting Humans"),
    ISAnimalsOnly("Infectious Substance Affecting Animals Only"),
    Biological("Biological Substance"),
    IWaste("Infectious Waste"),
    Exmept("Exempt Human or Animal Specimen");

    override fun toString(): String {
        return unName
    }

    companion object {
        fun fromString(unName: String): UnSubstance? {
            return entries.find { it.unName == unName }
        }
    }
}

enum class ShippingMethod(private val method: String) {
    CargoOnly("CargoOnly"),
    Passenger("Passenger"),
    ByRoad("ByRoad");

    override fun toString(): String {
        return method
    }

    companion object {
        fun fromString(method: String): ShippingMethod? {
            return entries.find { it.method == method }
        }
    }
}