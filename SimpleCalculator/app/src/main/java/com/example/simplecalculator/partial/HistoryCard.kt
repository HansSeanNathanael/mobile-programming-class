package com.example.simplecalculator.partial

import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.simplecalculator.Render
import com.example.simplecalculator.ui.theme.SimpleCalculatorTheme

@Composable
fun HistoryCard(history : String) {
    Text(text = history, color = Color.Gray, fontSize = 24.sp, modifier = Modifier.background(Color.Transparent))
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleCalculatorTheme {
        HistoryCard("1+2/3=1.6666667")
    }
}
