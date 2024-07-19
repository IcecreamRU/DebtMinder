package ru.icecreamru.debtminder.presentation.ui.debtlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import ru.icecreamru.debtminder.domain.model.Debt
import ru.icecreamru.debtminder.domain.usecase.AddDebtUseCase
import ru.icecreamru.debtminder.domain.usecase.DeleteDebtUseCase
import ru.icecreamru.debtminder.domain.usecase.GetDebtsUseCase
import javax.inject.Inject

@HiltViewModel
class DebtListViewModel @Inject constructor(
    private val getDebtsUseCase: GetDebtsUseCase,
    private val addDebtUseCase: AddDebtUseCase,
    private val deleteDebtUseCase: DeleteDebtUseCase
) : ViewModel(), DebtListContract.ViewModel {

    private val _state = MutableStateFlow(DebtListContract.State())
    override val state: StateFlow<DebtListContract.State> = _state

    init {
        handleEvent(DebtListContract.Event.LoadDebts)
    }

    override fun handleEvent(event: DebtListContract.Event) {
        when (event) {
            is DebtListContract.Event.LoadDebts -> loadDebts()
            is DebtListContract.Event.AddDebt -> addDebt(event.name, event.amount, event.isOwedToUser)
            is DebtListContract.Event.DeleteDebt -> deleteDebt(event.debt)
        }
    }

    private fun loadDebts() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            getDebtsUseCase()
                .catch { e ->
                    _state.value = _state.value.copy(isLoading = false, error = e.message)
                }
                .collect { debts ->
                    _state.value = _state.value.copy(isLoading = false, debts = debts)
                }
        }
    }

    private fun addDebt(name: String, amount: Double, isOwedToUser: Boolean) {
        viewModelScope.launch {
            val newDebt = Debt(personName = name, amount = amount, isOwedToUser = isOwedToUser, date = System.currentTimeMillis())
            addDebtUseCase(newDebt)
        }
    }

    private fun deleteDebt(debt: Debt) {
        viewModelScope.launch {
            deleteDebtUseCase(debt)
        }
    }
}