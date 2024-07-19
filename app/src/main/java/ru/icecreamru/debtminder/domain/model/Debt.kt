package ru.icecreamru.debtminder.domain.model

data class Debt(
    val id: Int = 0,
    val personName: String,
    val amount: Double,
    val isOwedToUser: Boolean,
    val date: Long
)