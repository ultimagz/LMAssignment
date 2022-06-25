package gz.tar.ultimagz.lmassignment.data

import gz.tar.ultimagz.domain.lmassignment.model.CoinInfo
import gz.tar.ultimagz.domain.lmassignment.model.Coins
import gz.tar.ultimagz.domain.lmassignment.repository.CoinRepository
import gz.tar.ultimagz.lmassignment.data.api.CoinRankingApi
import gz.tar.ultimagz.lmassignment.data.model.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinRankingApi
): CoinRepository {
    override suspend fun getCoins(name: String, offset: Int, limit: Int): Flow<Coins> {
        return flow { emit(api.getCoins(name = name, offset = offset, limit = limit)) }
            .map {
                it.data.toDomainModel()
            }
    }

    override suspend fun getCoinInfo(uuid: String): Flow<CoinInfo> {
        return flow { emit(api.getCoinInfo(uuid = uuid)) }
            .map { it.data.coin.toDomainModel() }
    }
}