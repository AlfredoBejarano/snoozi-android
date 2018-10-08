package me.alfredobejarano.elgordo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import me.alfredobejarano.elgordo.data.DogPost
import me.alfredobejarano.elgordo.data.DogPostRepository
import me.alfredobejarano.elgordo.utilities.runOnWorkerThread

/**
 * Simple [ViewModel] class that reports posts list changes to the UI.
 *
 * @author Alfredo Bejarano
 * @version 1.0
 * @since 08/10/2018 - 05:26 PM
 */
class DogPostViewModel(private val repository: DogPostRepository) : ViewModel() {
    var post: LiveData<DogPost>? = null
    var postList: LiveData<List<DogPost>>? = null

    /**
     * Fetches the list of all posts stored in the database.
     */
    fun getPosts() = runOnWorkerThread {
        postList = repository.getDogPosts()
    }

    /**
     * Retrieves a single dog post from the list.
     */
    fun getDogPost(id: Long) = runOnWorkerThread {
        post = repository.getDogPost(id)
    }

    /**
     * Create a dog post, it gets added to the database.
     */
    fun createPost(post: DogPost) = runOnWorkerThread {
        repository.createDogPost(post)
    }
}