package gz.tar.ultimagz.lmassignment.data.model

import com.google.gson.annotations.SerializedName
import gz.tar.ultimagz.domain.lmassignment.model.Coin

data class CoinData(
    @SerializedName("btcPrice")
    val btcPrice: String?,
    @SerializedName("change")
    val change: String?,
    @SerializedName("coinrankingUrl")
    val coinRankingUrl: String?,
    @SerializedName("color")
    val color: String?,
    @SerializedName("24hVolume")
    val hVolume: String?,
    @SerializedName("iconUrl")
    val iconUrl: String?,
    @SerializedName("listedAt")
    val listedAt: Int,
    @SerializedName("marketCap")
    val marketCap: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String?,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("sparkline")
    val sparkline: List<String?>,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("uuid")
    val uuid: String
)

fun CoinData.toDomainModel(): Coin {
    return Coin(
        btcPrice = btcPrice ?: "0",
        change = change ?: "0",
        coinRankingUrl = coinRankingUrl ?: "",
        color = color ?: "",
        hVolume = hVolume ?: "0",
        iconUrl = iconUrl ?: "",
        listedAt = listedAt,
        marketCap = marketCap ?: "0",
        name = name,
        price = price ?: "0",
        rank = rank,
        sparkline = sparkline.filterNotNull(),
        symbol = symbol ?: "",
        uuid = uuid,
    )
}