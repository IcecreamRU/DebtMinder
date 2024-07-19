package ru.icecreamru.debtminder.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.icecreamru.debtminder.data.local.DebtDao
import ru.icecreamru.debtminder.data.mapper.DebtMapper
import ru.icecreamru.debtminder.domain.model.Debt
import ru.icecreamru.debtminder.domain.repository.DebtRepository
import javax.inject.Inject

class DebtRepositoryImpl @Inject constructor(
    private val debtDao: DebtDao,
    private val debtMapper: DebtMapper
) : DebtRepository {

    override fun getAllDebts(): Flow<List<Debt>> {
        return debtDao.getAllDebts().map { entities ->
            entities.map { debtMapper.mapToDomain(it) }
        }
    }

    override suspend fun addDebt(debt: Debt) {
        debtDao.insertDebt(debtMapper.mapToEntity(debt))
    }

    override suspend fun updateDebt(debt: Debt) {
        debtDao.updateDebt(debtMapper.mapToEntity(debt))
    }

    override suspend fun deleteDebt(debt: Debt) {
        debtDao.deleteDebt(debtMapper.mapToEntity(debt))
    }
}