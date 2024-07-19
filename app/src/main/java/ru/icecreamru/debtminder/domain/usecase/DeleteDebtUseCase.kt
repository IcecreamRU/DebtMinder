package ru.icecreamru.debtminder.domain.usecase

import ru.icecreamru.debtminder.domain.model.Debt
import ru.icecreamru.debtminder.domain.repository.DebtRepository
import javax.inject.Inject

class DeleteDebtUseCase @Inject constructor(
    private val repository: DebtRepository
) {
    suspend operator fun invoke(debt: Debt) {
        repository.deleteDebt(debt)
    }
}