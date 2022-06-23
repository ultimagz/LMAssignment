package gz.tar.ultimagz.lmassignment.presentation.coinlist.model

import gz.tar.ultimagz.domain.lmassignment.model.Supply

data class SupplyViewData(
    val circulating: String,
    val confirmed: Boolean,
    val total: String
) {
    companion object {
        fun from(supply: Supply): SupplyViewData {
            return supply.run {
                SupplyViewData(
                    circulating = circulating,
                    confirmed = confirmed,
                    total = total,
                )
            }
        }
    }
}

