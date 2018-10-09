package me.alfredobejarano.elgordo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass that contains a ViewPager for displaying lost
 * and found dog posts and also contains a FAB that sends to the AddPostFragment.
 *
 * @author Alfredo Bejarano
 * @since October 8th, 2018 - 11:20 PM
 * @version 2.0
 */
class PostsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }
}
