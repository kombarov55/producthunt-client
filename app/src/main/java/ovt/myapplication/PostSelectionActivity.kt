package ovt.myapplication

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.ListView
import android.widget.Spinner
import component
import kotlinx.android.synthetic.main.activity_post_selection.*
import ovt.myapplication.dao.PostDao
import ovt.myapplication.dao.TopicDao
import ovt.myapplication.model.Topic
import java.util.*
import javax.inject.Inject

import OnItemSelectedListenerAdapter
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.AdapterView

class PostSelectionActivity : AppCompatActivity() {

    @Inject lateinit var postDao: PostDao
    @Inject lateinit var topicDao: TopicDao

    lateinit var topics: List<Topic>
    lateinit var selectedTopic: Topic
    lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_selection)
        component.inject(this)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = ""

        topics = topicDao.getTrendingTopics()

        val spinner = findViewById<Spinner>(R.id.topicSelection)
        spinner.setAdapter(TopicAdapter(topics, layoutInflater))
        val onItemSelectedListenerAdapter = OnItemSelectedListenerAdapter(
                onItemSelectedCallback = { _, _, i, _ ->
                    selectedTopic = topics[i]
                    refreshPosts()
                }
        )
        spinner.onItemSelectedListener = onItemSelectedListenerAdapter

        selectedTopic = topics.first()

        val posts = postDao.getByTopic(topics.first().name)

        postAdapter = PostAdapter(posts, layoutInflater)
        val listview = findViewById<ListView>(R.id.postList)
        listview.setAdapter(postAdapter)
        listview.setOnItemClickListener { parent: AdapterView<*>?, item: View?, i: Int, id: Long ->
            startActivity(Intent(this@PostSelectionActivity, PostViewActivity::class.java))
        }

        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            refreshPosts()
            swipeRefreshLayout.isRefreshing = false
        }


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.post_selection_menu, menu)

        return true
    }

    private fun refreshPosts() {
        postAdapter.posts = postDao.getByTopic(selectedTopic.name)
        postAdapter.notifyDataSetChanged()
    }
}
