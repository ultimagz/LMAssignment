package gz.tar.ultimagz.lmassignment.presentation.coinlist.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import gz.tar.ultimagz.domain.lmassignment.model.CoinInfo
import gz.tar.ultimagz.lmassignment.theme.Roboto

data class CoinInfoViewData(
    val allTimeHigh: AllTimeHighViewData,
    val btcPrice: String,
    val change: String,
    val coinrankingUrl: String,
    val color: String?,
    val description: String,
    val hVolume: String,
    val iconUrl: String,
    val links: List<LinkViewData>,
    val listedAt: Int,
    val lowVolume: Boolean,
    val marketCap: String,
    val name: AnnotatedString,
    val numberOfExchanges: Int,
    val numberOfMarkets: Int,
    val price: String,
    val priceAt: Int,
    val rank: Int,
    val sparkline: List<String>,
    val supply: SupplyViewData,
    val symbol: String,
    val tier: Int,
    val uuid: String,
    val websiteUrl: String
) {
    companion object {
        fun from(
            coinInfo: CoinInfo,
            defaultNameColor: Color,
            symbolColor: Color
        ): CoinInfoViewData {

            return coinInfo.run {
                val annotatedName = makeAnnotatedName(
                    name = name,
                    color = color?.let { Color(it.toColorInt()) } ?: defaultNameColor,
                    symbol = symbol,
                    symbolColor = symbolColor
                )

                CoinInfoViewData(
                    allTimeHigh = AllTimeHighViewData.from(allTimeHigh),
                    btcPrice = btcPrice,
                    change = change,
                    coinrankingUrl = coinrankingUrl,
                    color = color,
                    description = description,
                    hVolume = hVolume,
                    iconUrl = iconUrl,
                    links = links.map { LinkViewData.from(it) },
                    listedAt = listedAt,
                    lowVolume = lowVolume,
                    marketCap = marketCap,
                    name = annotatedName,
                    numberOfExchanges = numberOfExchanges,
                    numberOfMarkets = numberOfMarkets,
                    price = price,
                    priceAt = priceAt,
                    rank = rank,
                    sparkline = ArrayList(sparkline),
                    supply = SupplyViewData.from(supply),
                    symbol = symbol,
                    tier = tier,
                    uuid = uuid,
                    websiteUrl = websiteUrl,
                )
            }
        }

        private fun makeAnnotatedName(
            name: String,
            color: Color,
            symbol: String,
            symbolColor: Color
        ): AnnotatedString {
            return buildAnnotatedString {
                append(
                    AnnotatedString(
                        name,
                        spanStyle = SpanStyle(
                            color = color,
                            fontSize = 18.sp,
                            fontFamily = Roboto,
                            fontWeight = FontWeight.Bold
                        )
                    )
                )
                append(" ")
                append(
                    AnnotatedString(
                        "($symbol)",
                        spanStyle = SpanStyle(
                            color = symbolColor,
                            fontSize = 16.sp,
                            fontFamily = Roboto,
                            fontWeight = FontWeight.Normal
                        )
                    )
                )
            }
        }
    }
}


