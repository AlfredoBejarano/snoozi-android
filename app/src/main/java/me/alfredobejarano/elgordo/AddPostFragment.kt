package me.alfredobejarano.elgordo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass that allows a user to add a dog post.
 *
 *
 * @author Alfredo Bejarano
 * @since October 8th, 2018 - 11:19 PM
 * @version 2.0
 */
class AddPostFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_post, container, false)
    }
}
