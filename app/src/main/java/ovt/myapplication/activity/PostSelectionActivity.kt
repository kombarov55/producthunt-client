package ovt.myapplication.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import component
import kotlinx.android.synthetic.main.activity_post_selection.*
import ovt.myapplication.R
import ovt.myapplication.dao.PostDao
import javax.inject.Inject

class PostSelectionActivity : AppCompatActivity() {

    @Inject lateinit var postDao: PostDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_selection)
        setSupportActionBar(toolbar)
        component.inject(this)

        val posts = postDao.getByTopic("Tech")
        val adapter = PostAdapter(posts, layoutInflater)
        val listview = findViewById<ListView>(R.id.postList)
        listview.setAdapter(adapter)


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }



    }

}
