package ru.icecreamru.debtminder.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.icecreamru.debtminder.domain.model.Debt

interface DebtRepository {
    fun getAllDebts(): Flow<List<Debt>>
    suspend fun addDebt(debt: Debt)
    suspend fun updateDebt(debt: Debt)
    suspend fun deleteDebt(debt: Debt)
}