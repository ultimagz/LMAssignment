package gz.tar.ultimagz.lmassignment.data.model

import com.google.gson.annotations.SerializedName
import gz.tar.ultimagz.domain.lmassignment.model.AllTimeHigh

data class AllTimeHighData(
    @SerializedName("price")
    val price: String,
    @SerializedName("timestamp")
    val timestamp: Int
)

fun AllTimeHighData.toDomainModel(): AllTimeHigh {
    return AllTimeHigh(
        price = price,
        timestamp = timestamp,
    )
}