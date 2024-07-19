package ru.icecreamru.debtminder.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.icecreamru.debtminder.domain.model.Debt
import ru.icecreamru.debtminder.domain.repository.DebtRepository
import javax.inject.Inject

class GetDebtsUseCase @Inject constructor(
    private val repository: DebtRepository
) {
    operator fun invoke(): Flow<List<Debt>> {
        return repository.getAllDebts()
    }
}