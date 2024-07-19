package ru.icecreamru.debtminder.presentation.ui.debtlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.icecreamru.debtminder.domain.model.Debt
import ru.icecreamru.debtminder.domain.usecase.AddDebtUseCase
import ru.icecreamru.debtminder.domain.usecase.DeleteDebtUseCase
import ru.icecreamru.debtminder.domain.usecase.GetDebtsUseCase
import ru.icecreamru.debtminder.presentation.ui.components.AddDebtDialog
import ru.icecreamru.debtminder.presentation.ui.components.DebtItem
import ru.icecreamru.debtminder.presentation.ui.theme.DebtMinderTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DebtListScreen(
    viewModel: DebtListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Долги") },
                actions = {
                    IconButton(onClick = { showAddDialog = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Добавить долг")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn {
                items(state.debts) { debt ->
                    DebtItem(
                        debt = debt,
                        onItemClick = { /* Можно добавить обработку нажатия */ },
                        onDeleteClick = { viewModel.handleEvent(DebtListContract.Event.DeleteDebt(debt)) }

                    )
                }
            }

            if (showAddDialog) {
                AddDebtDialog(
                    onDismissRequest = { showAddDialog = false },
                    onConfirm = { name, amount, isOwedToUser ->
                        viewModel.handleEvent(
                            DebtListContract.Event.AddDebt(
                                name,
                                amount,
                                isOwedToUser
                            )
                        )
                        showAddDialog = false
                    }
                )
            }

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            state.error?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}