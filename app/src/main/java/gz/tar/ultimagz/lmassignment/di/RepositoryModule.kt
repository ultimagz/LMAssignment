package gz.tar.ultimagz.lmassignment.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import gz.tar.ultimagz.lmassignment.data.CoinRepositoryImpl
import gz.tar.ultimagz.lmassignment.data.api.CoinRankingApi
import gz.tar.ultimagz.domain.lmassignment.repository.CoinRepository

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideCoinRepository(api: CoinRankingApi): CoinRepository {
        return CoinRepositoryImpl(api = api)
    }
 }