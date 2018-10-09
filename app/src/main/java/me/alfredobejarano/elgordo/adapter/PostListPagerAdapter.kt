package me.alfredobejarano.elgordo.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import me.alfredobejarano.elgordo.PostsItemFragment

/**
 * Simple [FragmentStatePagerAdapter] subclass, it defines the list of lost dogs
 * first and the list of found dogs at the next position.
 *
 * Being a state pager adapter prevents the next list from being fetched if the user
 * does not want to see it.
 *
 * @author Alfredo Bejarano
 * @since August 8th, 2018 - 11:25 PM
 * @version 1.0
 */
class PostListPagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm) {

    companion object {
        /**
         * Constant value that defines the number of total pages for this adapter.
         */
        private const val PAGES = 2
    }

    /**
     * Creates a new item fragment, depending on the position it
     * is the type of list it will be.
     * @param position The current item position.
     * @return A [PostsItemFragment] instance with a defined list type.
     */
    override fun getItem(position: Int) = PostsItemFragment.newInstance(
            if (position == 0)
                PostsItemFragment.PostListType.TYPE_LOST
            else
                PostsItemFragment.PostListType.TYPE_FOUND
    )

    /**
     * Returns the number of total pages for this adapter.
     * @return 2
     * @see PAGES
     */
    override fun getCount() = PAGES
}