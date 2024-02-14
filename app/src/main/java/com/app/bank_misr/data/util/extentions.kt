package com.app.bank_misr.data.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp

/**
 * Ahmed Elmokadim
 * elmokadim@gmail.com
 * 15/07/2023
 */

fun Context.findActivity(): Activity {
  var context = this
  while (context is ContextWrapper) {
    if (context is Activity) return context
    context = context.baseContext
  }
  throw IllegalStateException("no activity")
}

inline fun <reified T : Any> Any.cast() = this as T

@Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
inline fun <C, R> C.ifNotEmpty(defaultValue: () -> R): R where C : CharSequence, C : R =
  if (isNotEmpty()) defaultValue() else this

fun <E> MutableCollection<E>.replaceAll(elements: Collection<E>): Boolean {
  clear()
  return addAll(elements)
}

fun Modifier.ignoreHorizontalParentPadding(horizontal: Dp): Modifier {
  return this.layout { measurable, constraints ->
    val overriddenWidth = constraints.maxWidth + 2 * horizontal.roundToPx()
    val placeable = measurable.measure(constraints.copy(maxWidth = overriddenWidth))
    layout(placeable.width, placeable.height) {
      placeable.place(0, 0)
    }
  }
}