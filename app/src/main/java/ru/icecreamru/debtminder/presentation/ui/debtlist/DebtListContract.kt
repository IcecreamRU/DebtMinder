package ru.icecreamru.debtminder.presentation.ui.debtlist

import kotlinx.coroutines.flow.StateFlow
import ru.icecreamru.debtminder.domain.model.Debt

interface DebtListContract {
    sealed class Event {
        object LoadDebts : Event()
        data class AddDebt(val name: String, val amount: Double, val isOwedToUser: Boolean) : Event()
        data class DeleteDebt(val debt: Debt) : Event()
    }

    data class State(
        val debts: List<Debt> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    )

    interface ViewModel {
        val state: StateFlow<State>
        fun handleEvent(event: Event)
    }
}