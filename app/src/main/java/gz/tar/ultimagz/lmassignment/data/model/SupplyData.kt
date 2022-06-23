package gz.tar.ultimagz.lmassignment.data.model

import com.google.gson.annotations.SerializedName
import gz.tar.ultimagz.domain.lmassignment.model.Supply

data class SupplyData(
    @SerializedName("circulating")
    val circulating: String,
    @SerializedName("confirmed")
    val confirmed: Boolean,
    @SerializedName("total")
    val total: String?
)

fun SupplyData.toDomainModel(): Supply {
    return Supply(
        circulating = circulating,
        confirmed = confirmed,
        total = total ?: "",
    )
}