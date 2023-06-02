package com.earl.chatprojectboilerplate.presentation.utils

import android.widget.EditText
import java.time.LocalDate
import java.time.format.DateTimeFormatter

interface DateValidator {

    fun validate(): Boolean

    class Base(
        private val date: EditText
    ): DateValidator {

        override fun validate(): Boolean {
            var res = false
            res = try {
                LocalDate.parse(date.text.toString(), DateTimeFormatter.ISO_DATE).toString()
                true
            } catch (e: Exception) {
                date.error = "Invalid date, use like 1900-01-01"
                false
            }
            return res
        }
    }
}