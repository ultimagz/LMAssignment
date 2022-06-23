package gz.tar.ultimagz.lmassignment.data.model


import com.google.gson.annotations.SerializedName

data class CoinInfoDataResponse(
    @SerializedName("coin")
    val coin: CoinInfoData
)