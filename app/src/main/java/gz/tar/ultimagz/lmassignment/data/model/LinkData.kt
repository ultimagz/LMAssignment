package gz.tar.ultimagz.lmassignment.data.model

import com.google.gson.annotations.SerializedName
import gz.tar.ultimagz.domain.lmassignment.model.Link

data class LinkData(
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)

fun LinkData.toDomainModel(): Link {
    return Link(
        name = name,
        type = type,
        url = url,
    )
}