package ovt.myapplication.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import ovt.myapplication.R
import ovt.myapplication.model.Post

/**
 * Created by nikolay on 07/04/2018.
 */
class PostAdapter(
        private val posts: List<Post>,
        private val inflater: LayoutInflater
): BaseAdapter() {

    override fun getView(i: Int, view: View?, parent: ViewGroup?): View {
        val v = view ?: inflater.inflate(R.layout.post_list_layout, parent, false)

        val post = posts[i]

        v.findViewById<TextView>(R.id.name).setText(post.name)
        v.findViewById<TextView>(R.id.likes).setText(post.upvoteCount.toString())
        v.findViewById<TextView>(R.id.description).setText(post.description)

        return v
    }

    override fun getItem(i: Int): Any {
        return posts[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return posts.size
    }
}