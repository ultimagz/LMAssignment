package gz.tar.ultimagz.domain.lmassignment.repository

import gz.tar.ultimagz.domain.lmassignment.model.CoinInfo
import gz.tar.ultimagz.domain.lmassignment.model.Coins
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    suspend fun getCoins(name: String, offset: Int, limit: Int): Flow<Coins>
    suspend fun getCoinInfo(uuid: String): Flow<CoinInfo>
}
