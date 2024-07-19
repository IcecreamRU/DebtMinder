package ru.icecreamru.debtminder.domain.usecase

import ru.icecreamru.debtminder.domain.model.Debt
import ru.icecreamru.debtminder.domain.repository.DebtRepository
import javax.inject.Inject

class AddDebtUseCase @Inject constructor(
    private val repository: DebtRepository
) {
    suspend operator fun invoke(debt: Debt) {
        repository.addDebt(debt)
    }
}