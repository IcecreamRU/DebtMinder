package ru.icecreamru.debtminder.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.icecreamru.debtminder.domain.model.Debt
import ru.icecreamru.debtminder.presentation.ui.theme.DebtMinderTheme
import ru.icecreamru.debtminder.presentation.utils.formatAmount
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DebtItem(
    debt: Debt,
    onItemClick: (Debt) -> Unit,
    onDeleteClick: (Debt) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(debt) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = debt.personName,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(debt.date)),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Text(
                text = "${if (debt.isOwedToUser) "+" else "-"}${debt.amount.formatAmount()}",
                style = MaterialTheme.typography.titleLarge,
                color = if (debt.isOwedToUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(onClick = { onDeleteClick(debt) }) {
                Icon(Icons.Default.Delete, contentDescription = "Удалить долг")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DebtItemPreview() {
    DebtMinderTheme {
        DebtItem(
            debt = Debt(
                id = 1,
                personName = "Иван Иванов",
                amount = 1000.0,
                isOwedToUser = true,
                date = System.currentTimeMillis()
            ),
            onItemClick = {},
            onDeleteClick = {}
        )
    }
}