package ovt.myapplication.screens.post_selection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import ovt.myapplication.R
import ovt.myapplication.model.Topic

/**
 * Created by nikolay on 07/04/2018.
 */
class TopicAdapter(val topics: List<Topic>,
                   val inflater: LayoutInflater): BaseAdapter() {


    override fun getView(i: Int, view: View?, parent: ViewGroup?): View {
        val v = view ?: inflater.inflate(R.layout.topic_list_layout, parent, false)

        val topic = topics[i]

//        Picasso.get().load(topic.imageSrc).placeholder(R.drawable.upvote).into(v.findViewById<ImageView>(R.id.topicImg))
        v.findViewById<TextView>(R.id.topicName).setText(topic.name)

        return v
    }

    override fun getItem(i: Int): Any {
        return topics[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return topics.size
    }
}