package ru.icecreamru.debtminder.data.mapper

import ru.icecreamru.debtminder.data.local.DebtEntity
import ru.icecreamru.debtminder.domain.model.Debt
import javax.inject.Inject

class DebtMapper @Inject constructor() {
    fun mapToDomain(entity: DebtEntity): Debt {
        return Debt(
            id = entity.id,
            personName = entity.personName,
            amount = entity.amount,
            isOwedToUser = entity.isOwedToUser,
            date = entity.date
        )
    }

    fun mapToEntity(domain: Debt): DebtEntity {
        return DebtEntity(
            id = domain.id,
            personName = domain.personName,
            amount = domain.amount,
            isOwedToUser = domain.isOwedToUser,
            date = domain.date
        )
    }
}