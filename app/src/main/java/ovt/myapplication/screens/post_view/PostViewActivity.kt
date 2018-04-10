package ovt.myapplication.screens.post_view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_post_view.*
import ovt.myapplication.R
import ovt.myapplication.model.Post

class PostViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_view)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->

        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val post = intent.getParcelableExtra<Post>(Post.EXTRA_NAME)

        supportActionBar?.title = post.name
        Picasso.get().load(post.screenshotSrc).into(findViewById<ImageView>(R.id.postScreenshot))
        findViewById<TextView>(R.id.postDescripton).setText(post.description)
    }
}
