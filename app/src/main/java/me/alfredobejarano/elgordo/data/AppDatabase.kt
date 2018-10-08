package me.alfredobejarano.elgordo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.alfredobejarano.elgordo.BuildConfig

/**
 * Documentation goes here.
 *
 * @author Alfredo Bejarano
 * @version 1.0
 * @since 08/10/2018 - 04:43 PM
 */
@Database(entities = [DogPost::class], version = BuildConfig.VERSION_CODE)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Allows access to the DogPostDao class.
     * @return Room implementation of the DogPostDao class.
     */
    abstract fun dogPostDao(): DogPostDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        /**
         * Allows access to the local app database to other classes
         * or creates an instance if it is null.
         * @return The app local database instance.
         */
        fun getInstance(ctx: Context) = instance ?: synchronized(this) {
            instance ?: buildDatabase(ctx).also { instance = it }
        }

        /**
         * Function that creates the local database instance for the app.
         * @return A local database instance.
         */
        private fun buildDatabase(ctx: Context) =
                Room.databaseBuilder(ctx, AppDatabase::class.java,
                        "Database${BuildConfig.VERSION_NAME}")
                        .fallbackToDestructiveMigration().build()
    }
}