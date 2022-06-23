package gz.tar.ultimagz.domain.lmassignment.model

data class CoinInfo(
    val allTimeHigh: AllTimeHigh,
    val btcPrice: String,
    val change: String,
    val coinrankingUrl: String,
    val color: String?,
    val description: String,
    val hVolume: String,
    val iconUrl: String,
    val links: List<Link>,
    val listedAt: Int,
    val lowVolume: Boolean,
    val marketCap: String,
    val name: String,
    val numberOfExchanges: Int,
    val numberOfMarkets: Int,
    val price: String,
    val priceAt: Int,
    val rank: Int,
    val sparkline: List<String>,
    val supply: Supply,
    val symbol: String,
    val tier: Int,
    val uuid: String,
    val websiteUrl: String
)
