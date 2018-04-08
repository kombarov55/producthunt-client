package ovt.myapplication.dao.impl

import android.content.Context
import map
import org.json.JSONObject
import ovt.myapplication.R
import ovt.myapplication.dao.PostDao
import ovt.myapplication.model.Post
import readToString
import spaceDelimitedtoSnakeCase
import javax.inject.Inject

/**
 * Created by nikolay on 06/04/2018.
 */
class PostDaoMock @Inject constructor(private val ctx: Context): PostDao {

    override fun getByTopic(topic: String): List<Post> {
        return parse(requestByTopic(topic))
    }

    private fun requestByTopic(topic: String): JSONObject {
        val topicId = nameToResourceId[topic]!!
        val str = ctx.resources.openRawResource(topicId).readToString()
        return JSONObject(str)
    }

    private fun parse(obj: JSONObject): List<Post> {
        return obj
                .getJSONArray("posts")
                .map { jsonObject -> Post(jsonObject) }
    }

    private val nameToResourceId = mapOf(
            "Touch Bar Apps" to R.raw.sample_touch_bar_apps_posts,
            "Books" to R.raw.sample_books_posts,
            "Games" to R.raw.sample_games_posts,
            "Tech" to R.raw.sample_tech_posts,
            "Artificial Intelligence" to R.raw.sample_artificial_intelligence_posts,
            "Developer Tools" to R.raw.sample_developer_tools_posts,
            "Wearables" to R.raw.sample_wearables_posts,
            "Home" to R.raw.sample_home_posts,
            "Productivity" to R.raw.sample_productivity_posts
    )

}