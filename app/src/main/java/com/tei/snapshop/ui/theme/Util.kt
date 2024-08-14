package com.tei.snapshop.ui.theme

import android.annotation.SuppressLint
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.SimpleDateFormat
import java.util.Date
import java.util.regex.Pattern


/**
 * Class Description
 * Created by Eniola Ipoola on 22/05/2024.
 * Copyright (c). All rights reserved
 */


fun isValidEmail(email: CharSequence): Boolean {
    var isValid = true
    val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(email)
    if (!matcher.matches()) {
        isValid = false
    }
    return isValid
}

fun String.isValidPhoneNumber() : Boolean {
    val patterns =  "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$"
    return Pattern.compile(patterns).matcher(this).matches()
}

fun removeLeadingZeros(num: String): String {
    //traverse the entire string
    for (i in 0 until num.length) {

        //check for the first non-zero character
        if (num[i] != '0') {
            //return the remaining string
            return num.substring(i)
        }
    }

    //If the entire string is traversed
    //that means it didn't have a single
    //non-zero character, hence return "0"
    return "0"
}

fun dateFilter(text: AnnotatedString): TransformedText {

    val trimmed = if (text.text.length >= 8) text.text.substring(0..7) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 2 == 1 && i < 4) out += "/"
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 3) return offset +1
            if (offset <= 8) return offset +2
            return 10
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <=2) return offset
            if (offset <=5) return offset -1
            if (offset <=10) return offset -2
            return 8
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}

fun formatDateString(dateString: String): String {
    // Check if the date string is in the expected format and length
    if (dateString.length != 8 || !dateString.all { it.isDigit() }) {
        throw IllegalArgumentException("Date string must be in the format DDMMYYYY and contain only digits")
    }

    val day = dateString.substring(0, 2).toInt()
    val month = dateString.substring(2, 4).toInt()
    val year = dateString.substring(4, 8).toInt()

    // Basic validation for day and month ranges
    if (day !in 1..31 || month !in 1..12) {
        throw IllegalArgumentException("Invalid day or month in date string")
    }

    return String.format("%02d-%02d-%d", year, month, day)
}


class DateTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return dateFilter(text)
    }
}
@SuppressLint("SimpleDateFormat")
fun convertStringToDateObject(dateString: String): Date? {
    // Define the date format
    val dateFormat = SimpleDateFormat("dd-MM-yyyy")
    dateFormat.isLenient = false  // Ensures strict date parsing

    // Parse the date string into a Date object
    return try {
        dateFormat.parse(dateString)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}