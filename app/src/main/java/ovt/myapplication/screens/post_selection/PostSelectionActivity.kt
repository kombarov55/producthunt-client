package ovt.myapplication.screens.post_selection

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.ListView
import android.widget.Spinner
import component
import ovt.myapplication.PostViewActivity
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
    override val pulledDownToRefresh: Observable<Unit?> get() = pulledDownToRefreshSubject

    private val topicClickedSubject = PublishSubject.create<Int>()
    private val postClickedSubject = PublishSubject.create<Int>()
    private val pulledDownToRefreshSubject = PublishSubject.create<Unit?>()

    private lateinit var spinner: Spinner
    private lateinit var postListView: ListView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_selection)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = ""

        component.inject(this)
        val presenter = PostSelectionPresenter(this, postDao, topicDao)

        spinner = findViewById(R.id.topicSelection)
        postListView = findViewById(R.id.postList)


        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener { pulledDownToRefreshSubject.onNext(null) }

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

    override fun endPullDownProgress() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun goToPostViewActivity(extra: Bundle) {
        val intent = Intent(this, PostViewActivity::class.java)
        intent.putExtras(extra)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.post_selection_menu, menu)
        return true
    }
}
