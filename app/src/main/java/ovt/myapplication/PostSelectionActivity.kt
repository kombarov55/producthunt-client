package ovt.myapplication

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.ListView
import android.widget.Spinner
import component
import ovt.myapplication.dao.PostDao
import ovt.myapplication.dao.TopicDao
import ovt.myapplication.model.Post
import ovt.myapplication.model.Topic
import rx.Observable
import rx.subjects.PublishSubject
import setOnItemClickListener
import setOnItemSelectedListener
import javax.inject.Inject

class PostSelectionActivity : AppCompatActivity(), PostSelectionView {


    @Inject lateinit var postDao: PostDao
    @Inject lateinit var topicDao: TopicDao

    override fun displayTopics(topics: List<Topic>) {
        topicAdapter.topics = topics
        topicAdapter.notifyDataSetChanged()
    }

    override fun displayPosts(posts: List<Post>) {
        val adapter = PostAdapter(posts, layoutInflater)
        postListView.setAdapter(adapter)
    }

    override val topicClicked: Observable<Topic> get() = topicClickedSubject
    override val postClicked: Observable<Post> get() = postClickedSubject

    private val topicClickedSubject = PublishSubject.create<Topic>()
    private val postClickedSubject = PublishSubject.create<Post>()

    private lateinit var spinner: Spinner
    private lateinit var postListView: ListView

    private lateinit var topicAdapter: TopicAdapter
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_selection)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = ""

        component.inject(this)


        spinner = findViewById(R.id.topicSelection)
        spinner.setOnItemSelectedListener { i -> topicClickedSubject.onNext(topicAdapter.topics[i]) }

        topicAdapter = TopicAdapter(emptyList(), layoutInflater)
        postAdapter = PostAdapter(emptyList(), layoutInflater)

        postListView = findViewById(R.id.postList)
        postListView.setOnItemClickListener { i -> postClickedSubject.onNext(postAdapter.posts[i]) }

        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.post_selection_menu, menu)
        return true
    }
}
