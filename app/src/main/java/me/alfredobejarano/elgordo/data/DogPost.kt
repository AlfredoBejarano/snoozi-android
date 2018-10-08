package me.alfredobejarano.elgordo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Model class that defines the data of a dog post.
 *
 * @author Alfredo Bejarano
 * @version 1.0
 * @since 08/10/2018 - 04:27 PM
 */
@Entity(tableName = "dog_posts")
class DogPost() {
    /**
     * Property used by Room, it defines the Id of the post.
     */
    @ColumnInfo(name = "pk")
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
    /**
     * Property that defines the title of the dog post.
     */
    private var mTitle: String = ""
    /**
     * Property that holds the reference of the dog photo as a base64.
     */
    private var mPhoto: String = ""
    /**
     * Boolean flag that defines if the post is for a lost dog (true)
     * or a found dog and the  user is looking for is owner (false).
     */
    private var mLost: Boolean = false
    /**
     * Property that contains more info about the dog, things like the breed
     * its size, where it was lost/found or the poster phone number.
     */
    private var mDescription: String = ""
    /**
     * Value that contains when this post was created.
     */
    @ColumnInfo(name = "created")
    val createdAt = Calendar.getInstance().time?.time

    /**
     * Constructor that allows creating a dog
     * object with a title, photo and a description.
     */
    constructor(title: String, photo: String, description: String, lost: Boolean) : this() {
        mLost = lost
        mTitle = title
        mPhoto = photo
        mDescription = description
    }
}