package gz.tar.ultimagz.lmassignment.presentation.coinlist.model

import gz.tar.ultimagz.domain.lmassignment.model.Coin
import gz.tar.ultimagz.domain.lmassignment.model.Coins
import gz.tar.ultimagz.domain.lmassignment.model.Stats

data class CoinModel(
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
    val uuid: String,
) {
    companion object {
        fun from(coin: Coin): CoinModel {
            return CoinModel(
                btcPrice = coin.btcPrice,
                change = coin.change,
                coinRankingUrl = coin.coinRankingUrl,
                color = coin.color,
                hVolume = coin.hVolume,
                iconUrl = coin.iconUrl,
                listedAt = coin.listedAt,
                marketCap = coin.marketCap,
                name = coin.name,
                price = coin.price,
                rank = coin.rank,
                sparkline = coin.sparkline,
                symbol = coin.symbol,
                uuid = coin.uuid,
            )
        }
    }
}

data class StatsModel(
    val total: Int,
    val total24hVolume: String,
    val totalCoins: Int,
    val totalExchanges: Int,
    val totalMarketCap: String,
    val totalMarkets: Int
) {
    companion object {
        fun from(stats: Stats): StatsModel {
            return StatsModel(
                total = stats.total,
                total24hVolume = stats.total24hVolume,
                totalCoins = stats.totalCoins,
                totalExchanges = stats.totalExchanges,
                totalMarketCap = stats.totalMarketCap,
                totalMarkets = stats.totalMarkets,
            )
        }
    }
}


data class CoinViewData(
    val coins: List<CoinModel>,
    val stats: StatsModel,
) {
    companion object {
        fun from(data: Coins): CoinViewData {
            return CoinViewData(
                coins = data.coins.map { CoinModel.from(it) },
                stats = StatsModel.from(data.stats),
            )
        }
    }
}
