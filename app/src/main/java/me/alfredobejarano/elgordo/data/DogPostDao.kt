package me.alfredobejarano.elgordo.data

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Dao class that exposes basic CRUD operations
 * for dog posts in the local database.
 *
 * @author Alfredo Bejarano
 * @version 1.0
 * @since 08/10/2018 - 04:54 PM
 */
@Dao
interface DogPostDao {
    /**
     * Inserts a dot post in the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrUpdate(dogPost: DogPost)

    /**
     * Retrieves a single dog data from the database.
     */
    @Query("SELECT * FROM dog_posts WHERE pk = :id")
    abstract fun getDogPost(id: Long): LiveData<DogPost>

    /**
     * Retrieve all the available dog posts from the database
     * ordered by their created timestamp in descending order.
     */
    @Query("SELECT * FROM dog_posts ORDER BY created DESC")
    abstract fun getAll(): LiveData<List<DogPost>>

    /**
     * Deletes a local post record.
     */
    @Delete
    abstract fun delete(record: DogPost)
}