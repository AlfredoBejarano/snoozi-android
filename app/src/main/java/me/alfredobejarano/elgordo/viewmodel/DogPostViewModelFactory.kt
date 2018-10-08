package me.alfredobejarano.elgordo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.alfredobejarano.elgordo.data.DogPostRepository

/**
 * Factory class that allows creating [DogPostViewModel] classes
 * as they have a custom constructor, they require being initialized
 * with this factory class.
 *
 * @author Alfredo Bejarano
 * @version 1.0
 * @since 08/10/2018 - 05:26 PM
 */
class DogPostViewModelFactory(private val repository: DogPostRepository) :
        ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DogPostViewModel(repository) as T
    }
}