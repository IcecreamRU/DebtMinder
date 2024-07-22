package ru.icecreamru.debtminder.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.icecreamru.debtminder.presentation.ui.theme.DebtMinderTheme

@Composable
fun AddDebtDialog(
    onDismissRequest: () -> Unit,
    onConfirm: (String, Double, Boolean) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var isOwedToUser by remember { mutableStateOf(true) }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text("Добавить долг") },
        text = {
            Column {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    label = { Text("Имя") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = amount,
                    onValueChange = { newValue ->
                        // Разрешаем вводить только числа и одну десятичную точку
                        if (newValue.isEmpty() || newValue.matches(Regex("^\\d*\\.?\\d*$"))) {
                            amount = newValue
                        }
                    },
                    label = { Text("Сумма") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                    Text("Тип долга:")
                    Spacer(modifier = Modifier.width(8.dp))
                    Switch(
                        checked = isOwedToUser,
                        onCheckedChange = { isOwedToUser = it }
                    )
                    Text(
                        if (isOwedToUser) "Мне должны" else "Я должен",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val amountDouble = amount.toDoubleOrNull()
                if (name.isNotBlank() && amountDouble != null && amountDouble > 0) {
                    onConfirm(name, amountDouble, isOwedToUser)
                }
            }) {
                Text("Добавить")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Отмена")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AddDebtDialogPreview() {
    DebtMinderTheme {
        AddDebtDialog(
            onDismissRequest = {},
            onConfirm = { _, _, _ -> }
        )
    }
}