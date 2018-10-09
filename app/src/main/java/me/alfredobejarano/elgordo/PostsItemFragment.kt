package me.alfredobejarano.elgordo


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass that contains a list of posts filtered by lost or found.
 *
 * @author Alfredo Bejarano
 * @since August 8th, 2018 - 11:25 PM
 * @version 1.0
 */
class PostsItemFragment : Fragment() {
    companion object {
        /**
         * Constant value that defines the key for the list type extra.
         */
        internal const val LIST_TYPE_EXTRA = "me.alfredobejarano.elgordo.LIST_TYPE_EXTRA"

        /**
         * Creates a new instance of this fragment with a given type
         * as the type of posts to be shown.
         * @param type The type of post list.
         */
        fun newInstance(type: PostListType): PostsItemFragment {
            val arguments = Bundle()
            val fragment = PostsItemFragment()
            arguments.putBoolean(LIST_TYPE_EXTRA, type == PostListType.TYPE_LOST)
            fragment.arguments = arguments
            return fragment
        }
    }

    /**
     * Property that defines a reference to a listener when an item gets clicked.
     */
    private var mListener: OnPostSelectedListener? = null

    /**
     * Creates a RecyclerView and defines it as the root of this fragment,
     * it also creates a LinearLayoutManager for the RecyclerView.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) = RecyclerView(requireContext())
            .apply {
                this.layoutManager = GridLayoutManager(this.context, 2)
            }


    /**
     * Checks if the context of this fragment implements the
     * element click listener, if so, it gets assigned to this fragment.
     */
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnPostSelectedListener) {
            mListener = context
        }
    }

    /**
     * Destroys the reference to the item click listener interface for this fragment.
     */
    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * Enum class that defines the types of lists available.
     */
    enum class PostListType {
        /**
         * Enum value that defines a list of dogs that got lost from their owners.
         */
        TYPE_LOST,
        /**
         * Enum value that defines a list of dogs found by other people.
         */
        TYPE_FOUND
    }

    /**
     * Interface that defines method for when an element gets clicked.
     */
    interface OnPostSelectedListener {
        /**
         * Function that passes the id of the clicked post.
         * @param post Id of the clicked post.
         */
        fun onPostSelected(post: Long)
    }
}
