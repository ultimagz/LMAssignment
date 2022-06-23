package gz.tar.ultimagz.lmassignment.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gz.tar.ultimagz.lmassignment.data.api.CoinRankingApi
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideCoinRankingApi(retrofit: Retrofit): CoinRankingApi {
        return retrofit.create(CoinRankingApi::class.java)
    }
}