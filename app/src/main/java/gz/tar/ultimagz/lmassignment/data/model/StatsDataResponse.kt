package gz.tar.ultimagz.lmassignment.data.model

import com.google.gson.annotations.SerializedName
import gz.tar.ultimagz.domain.lmassignment.model.Stats

data class StatsDataResponse(
    @SerializedName("total")
    val total: Int,
    @SerializedName("total24hVolume")
    val total24hVolume: String,
    @SerializedName("totalCoins")
    val totalCoins: Int,
    @SerializedName("totalExchanges")
    val totalExchanges: Int,
    @SerializedName("totalMarketCap")
    val totalMarketCap: String,
    @SerializedName("totalMarkets")
    val totalMarkets: Int
)

fun StatsDataResponse.toDomainModel(): Stats {
    return Stats(
        total = total,
        total24hVolume = total24hVolume,
        totalCoins = totalCoins,
        totalExchanges = totalExchanges,
        totalMarketCap = totalMarketCap,
        totalMarkets = totalMarkets,
    )
}