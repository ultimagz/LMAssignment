package gz.tar.ultimagz.lmassignment.presentation.coinlist.model

import gz.tar.ultimagz.domain.lmassignment.model.Link

data class LinkViewData(
    val name: String,
    val type: String,
    val url: String
) {
    companion object {
        fun from(link: Link): LinkViewData {
            return link.run {
                LinkViewData(
                    name = name,
                    type = type,
                    url = url,
                )
            }
        }
    }
}
