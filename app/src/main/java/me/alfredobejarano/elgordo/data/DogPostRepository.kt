package me.alfredobejarano.elgordo.data

import me.alfredobejarano.elgordo.utilities.runOnWorkerThread

/**
 * Repository class for the local stored dog posts, this class handles
 * when and where to retrieve the post, this class serves as the single
 * source of truth for the app.
 *
 * @author Alfredo Bejarano
 * @version 1.0
 * @since 08/10/2018 - 05:10 PM
 */
class DogPostRepository private constructor(private val dogPostDao: DogPostDao) {
    companion object {
        @Volatile
        private var instance: DogPostRepository? = null

        /**
         * Singleton access for this repository class.
         */
        fun getInstance(dogPostDao: DogPostDao) = instance ?: synchronized(this) {
            instance ?: DogPostRepository(dogPostDao).also { instance = it }
        }
    }

    /**
     * Inserts or updates a new / existing post into the local database.
     */
    fun createDogPost(post: DogPost) = runOnWorkerThread {
        dogPostDao.insertOrUpdate(post)
    }

    /**
     * Deletes a given post from the local database.
     */
    fun removeDogPost(post: DogPost) = runOnWorkerThread {
        dogPostDao.delete(post)
    }

    /**
     * Retrieves all the posts from the local storage database.
     */
    fun getDogPosts() = dogPostDao.getAll()

    /**
     * Retrieves a single dog post using its id.
     * @param id The dog post id.
     */
    fun getDogPost(id: Long) = dogPostDao.getDogPost(id)
}