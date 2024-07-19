package ru.icecreamru.debtminder.presentation.utils

fun Double.formatAmount(): String {
    return if (this % 1.0 == 0.0) {
        "%.0f".format(this)
    } else {
        "%.2f".format(this)
    }
}