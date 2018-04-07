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
import java.util.*
import javax.inject.Inject

class PostSelectionActivity : AppCompatActivity() {

    @Inject lateinit var postDao: PostDao
    @Inject lateinit var topicDao: TopicDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_selection)
        component.inject(this)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = ""


        val spinner = findViewById<Spinner>(R.id.topicSelection)
        spinner.setAdapter(TopicAdapter(topicDao.getTrendingTopics(), layoutInflater))

        val posts = postDao.getByTopic("Tech")
        val adapter = PostAdapter(posts, layoutInflater)
        val listview = findViewById<ListView>(R.id.postList)
        listview.setAdapter(adapter)

        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            //TODO: заглшука, далее заменить
            val newPosts = Random().let { r ->
                posts
                        .map { it to r.nextInt(posts.size) }
                        .sortedBy { it.second }
                        .map { it.first }
            }

            adapter.posts = newPosts
            adapter.notifyDataSetChanged()
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
}
