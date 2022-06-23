package gz.tar.ultimagz.lmassignment.data.api

import gz.tar.ultimagz.lmassignment.data.model.CoinInfoDataResponse
import gz.tar.ultimagz.lmassignment.data.model.CoinsDataResponse
import gz.tar.ultimagz.lmassignment.data.model.CoinRankingResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinRankingApi {
    @GET("coins")
    suspend fun getCoins(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): CoinRankingResponse<CoinsDataResponse>

    @GET("coin/{uuid}")
    suspend fun getCoinInfo(
        @Path("uuid") uuid: String
    ): CoinRankingResponse<CoinInfoDataResponse>
}