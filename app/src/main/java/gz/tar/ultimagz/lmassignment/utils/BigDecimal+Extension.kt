package gz.tar.ultimagz.lmassignment.utils

import java.math.BigDecimal

fun BigDecimal.formatToShortScale(): String {
    val trillion = BigDecimal(1_000_000_000_000.0)
    val billion = BigDecimal(1_000_000_000.0)
    val million = BigDecimal(1_000_000.0)
    return when {
        this >= trillion -> String.format("$%.2f trillion", this / trillion)
        this >= billion -> String.format("$%.2f billion", this / billion)
        this >= million -> String.format("$%.2f million", this / million)
        else -> String.format("%.2f", this)
    }
}