package gz.tar.ultimagz.domain.lmassignment.model

data class Coin(
    val btcPrice: String,
    val change: String,
    val coinRankingUrl: String,
    val color: String,
    val hVolume: String,
    val iconUrl: String,
    val listedAt: Int,
    val marketCap: String,
    val name: String,
    val price: String,
    val rank: Int,
    val sparkline: List<String>,
    val symbol: String,
    val uuid: String
)
