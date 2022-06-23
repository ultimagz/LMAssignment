package gz.tar.ultimagz.lmassignment.data.model

import com.google.gson.annotations.SerializedName

data class CoinRankingResponse<T>(
    @SerializedName("data")
    val `data`: T,
    @SerializedName("status")
    val status: String
)