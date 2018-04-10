package ovt.myapplication.screens.post_selection

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.ListView
import android.widget.Spinner
import component
import ovt.myapplication.R
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

    override val topicClicked: Observable<Int> get() = topicClickedSubject
    override val postClicked: Observable<Int> get() = postClickedSubject

    private val topicClickedSubject = PublishSubject.create<Int>()
    private val postClickedSubject = PublishSubject.create<Int>()

    private lateinit var spinner: Spinner
    private lateinit var postListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_selection)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = ""

        component.inject(this)
        val presenter = PostSelectionPresenter(this, postDao, topicDao)

        spinner = findViewById(R.id.topicSelection)
        postListView = findViewById(R.id.postList)


        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
        }

        presenter.onCreate()
    }

    override fun displayTopics(topics: List<Topic>) {
        spinner.adapter = TopicAdapter(topics, layoutInflater)
        spinner.setOnItemSelectedListener { i -> topicClickedSubject.onNext(i) }
    }

    override fun displayPosts(posts: List<Post>) {
        postListView.adapter = PostAdapter(posts, layoutInflater)
        postListView.setOnItemClickListener { i -> postClickedSubject.onNext(i) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.post_selection_menu, menu)
        return true
    }
}