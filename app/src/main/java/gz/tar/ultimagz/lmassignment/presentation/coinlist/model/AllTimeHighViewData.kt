package gz.tar.ultimagz.lmassignment.presentation.coinlist.model

import gz.tar.ultimagz.domain.lmassignment.model.AllTimeHigh

data class AllTimeHighViewData(
    val price: String,
    val timestamp: Int
) {
    companion object {
        fun from(allTimeHigh: AllTimeHigh): AllTimeHighViewData {
            return allTimeHigh.run {
                AllTimeHighViewData(
                    price = price,
                    timestamp = timestamp
                )
            }
        }
    }
}
