package gz.tar.ultimagz.lmassignment.data.model


import com.google.gson.annotations.SerializedName
import gz.tar.ultimagz.domain.lmassignment.model.Coins

data class CoinsDataResponse(
    @SerializedName("coins")
    val coins: List<CoinData>,
    @SerializedName("stats")
    val stats: StatsDataResponse
)

fun CoinsDataResponse.toDomainModel(): Coins {
    return Coins(
        coins = coins.map { it.toDomainModel() },
        stats = stats.toDomainModel()
    )
}