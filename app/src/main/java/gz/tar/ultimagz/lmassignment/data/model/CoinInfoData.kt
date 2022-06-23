package gz.tar.ultimagz.lmassignment.data.model

import com.google.gson.annotations.SerializedName
import gz.tar.ultimagz.domain.lmassignment.model.CoinInfo

data class CoinInfoData(
    @SerializedName("allTimeHigh")
    val allTimeHigh: AllTimeHighData,
    @SerializedName("btcPrice")
    val btcPrice: String,
    @SerializedName("change")
    val change: String,
    @SerializedName("coinrankingUrl")
    val coinrankingUrl: String,
    @SerializedName("color")
    val color: String?,
    @SerializedName("description")
    val description: String,
    @SerializedName("24hVolume")
    val hVolume: String,
    @SerializedName("iconUrl")
    val iconUrl: String,
    @SerializedName("links")
    val links: List<LinkData>,
    @SerializedName("listedAt")
    val listedAt: Int,
    @SerializedName("lowVolume")
    val lowVolume: Boolean,
    @SerializedName("marketCap")
    val marketCap: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("numberOfExchanges")
    val numberOfExchanges: Int,
    @SerializedName("numberOfMarkets")
    val numberOfMarkets: Int,
    @SerializedName("price")
    val price: String,
    @SerializedName("priceAt")
    val priceAt: Int,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("sparkline")
    val sparkline: List<String>,
    @SerializedName("supply")
    val supply: SupplyData,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("tier")
    val tier: Int,
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("websiteUrl")
    val websiteUrl: String
)

fun CoinInfoData.toDomainModel(): CoinInfo {
    return CoinInfo(
        allTimeHigh = allTimeHigh.toDomainModel(),
        btcPrice = btcPrice,
        change = change,
        coinrankingUrl = coinrankingUrl,
        color = color,
        description = description,
        hVolume = hVolume,
        iconUrl = iconUrl,
        links = links.map { it.toDomainModel() },
        listedAt = listedAt,
        lowVolume = lowVolume,
        marketCap = marketCap,
        name = name,
        numberOfExchanges = numberOfExchanges,
        numberOfMarkets = numberOfMarkets,
        price = price,
        priceAt = priceAt,
        rank = rank,
        sparkline = ArrayList(sparkline),
        supply = supply.toDomainModel(),
        symbol = symbol,
        tier = tier,
        uuid = uuid,
        websiteUrl = websiteUrl,
    )
}
