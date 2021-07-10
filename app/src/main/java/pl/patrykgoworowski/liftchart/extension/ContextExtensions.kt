package pl.patrykgoworowski.liftchart.extension

import android.content.Context
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

inline fun Context.color(resIdBlock: () -> Int): Int =
    ContextCompat.getColor(this, resIdBlock())

inline fun Context.colors(resIdsBlock: () -> IntArray): List<Int> =
    resIdsBlock().map { ContextCompat.getColor(this, it) }

fun Context.getThemeColor(@AttrRes attr: Int, @ColorRes defValueRes: Int? = null): Int {
    val tempArray = IntArray(1)
    tempArray[0] = attr
    val a = obtainStyledAttributes(null, tempArray)
    return try {
        a.getColor(0, defValueRes?.let { ContextCompat.getColor(this, it) } ?: 0)
    } finally {
        a.recycle()
    }
}