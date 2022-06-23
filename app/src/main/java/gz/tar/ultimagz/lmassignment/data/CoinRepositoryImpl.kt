package gz.tar.ultimagz.lmassignment.data

import gz.tar.ultimagz.lmassignment.data.api.CoinRankingApi
import gz.tar.ultimagz.lmassignment.data.model.toDomainModel
import gz.tar.ultimagz.domain.lmassignment.model.CoinInfo
import gz.tar.ultimagz.domain.lmassignment.model.Coins
import gz.tar.ultimagz.domain.lmassignment.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinRankingApi
): CoinRepository {
    private var getCoins = -1
    private var getCoinInfo = -1
    override suspend fun getCoins(offset: Int, limit: Int): Flow<Coins> {
        getCoins++
        return if (getCoins % 2 == 0) {
            flow { error("Shi ne!!!") }
        } else {
            flow { emit(api.getCoins(offset = offset, limit = limit)) }
                .map { it.data.toDomainModel() }
        }

    }

    override suspend fun getCoinInfo(uuid: String): Flow<CoinInfo> {
        getCoinInfo++
        return if (getCoinInfo % 2 == 0) {
            flow { error("Shi ne!!!") }
        } else {
            flow { emit(api.getCoinInfo(uuid = uuid)) }
                .map { it.data.coin.toDomainModel() }
        }
    }
}