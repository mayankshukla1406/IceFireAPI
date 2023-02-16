package com.example.jumpingminds.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.jumpingminds.Constants
import com.example.jumpingminds.data.local.Book.BookDao
import com.example.jumpingminds.data.local.Book.IceFireBookDatabase
import com.example.jumpingminds.data.local.Character.CharacterDao
import com.example.jumpingminds.data.local.Character.IceFireCharacterDatabase
import com.example.jumpingminds.data.local.House.HouseDao
import com.example.jumpingminds.data.local.House.IceFireHouseDatabase
import com.example.jumpingminds.data.remote.IceFireAPI
import com.example.jumpingminds.data.repository.IceFireRepositoryImpl
import com.example.jumpingminds.domain.repository.IceFireRepository
import com.example.jumpingminds.domain.use_cases.BookUseCase
import com.example.jumpingminds.domain.use_cases.CharacterUseCase
import com.example.jumpingminds.domain.use_cases.HouseUseCase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object JumpingMindsModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .build()
    }

    @Singleton
    @Provides
    fun provideIceFireAPI(retrofit: Retrofit): IceFireAPI {
        return retrofit.create(IceFireAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideBookDao(db: IceFireBookDatabase): BookDao {
        return db.booksDao()
    }

    @Singleton
    @Provides
    fun provideCharacterDao(db: IceFireCharacterDatabase): CharacterDao {
        return db.charactersDao()
    }

    @Singleton
    @Provides
    fun provideHouseDao(db: IceFireHouseDatabase): HouseDao {
        return db.housesDao()
    }

    @Singleton
    @Provides
    fun provideIceFireBookDatabase(@ApplicationContext context: Context): IceFireBookDatabase {
        return Room.databaseBuilder(context, IceFireBookDatabase::class.java, "icefire_book_db")
            .build()
    }

    @Singleton
    @Provides
    fun provideIceFireCharacterDatabase(@ApplicationContext context: Context): IceFireCharacterDatabase {
        return Room.databaseBuilder(context, IceFireCharacterDatabase::class.java, "icefire_character_db")
            .build()
    }

    @Singleton
    @Provides
    fun provideIceFireHouseDatabase(@ApplicationContext context: Context): IceFireHouseDatabase {
        return Room.databaseBuilder(context, IceFireHouseDatabase::class.java, "icefire_house_db")
            .build()
    }

    @Provides
    fun provideIceFireRepository(iceFireAPI: IceFireAPI): IceFireRepository {
        return IceFireRepositoryImpl(iceFireAPI)
    }

    @Provides
    fun provideBookUseCase(iceFireRepository: IceFireRepository, bookDao: BookDao): BookUseCase {
        return BookUseCase(iceFireRepository, bookDao)
    }

    @Provides
    fun provideHouseUseCase(iceFireRepository: IceFireRepository, houseDao: HouseDao): HouseUseCase {
        return HouseUseCase(iceFireRepository, houseDao)
    }
    @Provides
    fun provideCharacterUseCase(iceFireRepository: IceFireRepository, characterDao: CharacterDao): CharacterUseCase {
        return CharacterUseCase(iceFireRepository, characterDao)
    }

}
