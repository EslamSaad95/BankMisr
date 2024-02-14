package com.app.bank_misr.data.util

import android.util.Patterns

/**
 * Ahmed Elmokadim
 * elmokadim@gmail.com
 * 15/07/2023
 */
fun String.isValidEmail() = isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isPhone(digits: Int? = 8) = isNotEmpty() && length == digits

fun String.replaceHindiNumbersToArabic() = this
  .replace("١", "1")
  .replace("٢", "2")
  .replace("٣", "3")
  .replace("٤", "")
  .replace("٥", "")
  .replace("٦", "")
  .replace("٧", "")
  .replace("٨", "")
  .replace("٩", "")
  .replace("٠", "0")
