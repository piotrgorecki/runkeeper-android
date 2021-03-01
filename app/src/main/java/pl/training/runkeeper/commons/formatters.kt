package pl.training.runkeeper.commons

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(date: Date, format: String = "dd/MM"): String = SimpleDateFormat(format, Locale.getDefault()).format(date)

fun formatDegrees(value: Int): String = "$value °"