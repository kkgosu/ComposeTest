package com.kvlg.recipe.di

import com.google.gson.Gson
import com.kvlg.recipe.data.RecipeRepository
import com.kvlg.recipe.data.RecipeRepositoryImpl
import com.kvlg.recipe.data.RecipeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author Konstantin Koval
 * @since 22.06.2021
 */
@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    fun provideApiService(): RecipeService {
        return Retrofit.Builder()
            .baseUrl(RecipeService.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(RecipeService::class.java)
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(service: RecipeService): RecipeRepository {
        return RecipeRepositoryImpl(service)
    }
}