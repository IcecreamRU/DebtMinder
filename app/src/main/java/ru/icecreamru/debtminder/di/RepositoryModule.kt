package ru.icecreamru.debtminder.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.icecreamru.debtminder.data.repository.DebtRepositoryImpl
import ru.icecreamru.debtminder.domain.repository.DebtRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindDebtRepository(
        debtRepositoryImpl: DebtRepositoryImpl
    ): DebtRepository
}