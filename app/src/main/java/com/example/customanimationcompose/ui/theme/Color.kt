package com.example.customanimationcompose.ui.theme

import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val Blue589FFF = Color(0xFF589FFF)
val WhiteFFFFFF = Color(0xFFFFFFFF)
val WhiteAlpha20 = Color(0x33FFFFFF)
val WhiteAlpha30 = Color(0x4DFFFFFF)

data class CustomViewColors(
    val backgroundColor: Color,
    val textPercentColor: Color,
    val strokeColor: Color,
    val shieldBackgroundColor: Color,
    val scanColor: Color
)

val CustomColors = CustomViewColors(
    backgroundColor = Blue589FFF,
    textPercentColor = WhiteFFFFFF,
    strokeColor = WhiteFFFFFF,
    shieldBackgroundColor = WhiteAlpha20,
    scanColor = WhiteAlpha30
)