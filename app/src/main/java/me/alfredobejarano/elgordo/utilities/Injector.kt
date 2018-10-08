package me.alfredobejarano.elgordo.utilities

import android.content.Context
import me.alfredobejarano.elgordo.data.AppDatabase
import me.alfredobejarano.elgordo.data.DogPostRepository
import me.alfredobejarano.elgordo.viewmodel.DogPostViewModelFactory

/**
 * Object that allows a pseudo dependency injection for the app.
 *
 * @author Alfredo Bejarano
 * @version 1.0
 * @since 08/10/2018 - 05:39 PM
 */
object Injector {
    private fun getDogPostRepository(ctx: Context) =
            DogPostRepository.getInstance(AppDatabase.getInstance(ctx).dogPostDao())

    /**
     * Provides a ViewModelFactory for creating instances
     * of the DogPostViewModel class.
     * @param ctx The UI controller requesting the factory class.
     */
    fun provideDogPostViewModelFactory(ctx: Context) {
        val repository = getDogPostRepository(ctx)
        DogPostViewModelFactory(repository)
    }

}