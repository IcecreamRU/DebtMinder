package ru.icecreamru.debtminder.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.icecreamru.debtminder.data.local.DebtDao
import ru.icecreamru.debtminder.data.local.DebtDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDebtDatabase(@ApplicationContext context: Context): DebtDatabase {
        return Room.databaseBuilder(
            context,
            DebtDatabase::class.java,
            "debt_database"
        ).build()
    }

    @Provides
    fun provideDebtDao(database: DebtDatabase): DebtDao {
        return database.debtDao()
    }
}