package ovt.myapplication.dao

import API
import accessToken
import android.content.Context
import android.renderscript.ScriptGroup
import com.android.volley.Request.Method.GET
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.RequestFuture
import map
import org.json.JSONObject
import ovt.myapplication.R
import ovt.myapplication.model.Topic

/**
 * Created by nikolay on 06/04/2018.
 */
object TopicDao {

    private val trendingUrl = "${API}/topics?search[trending]=true&access_token=$accessToken"

    fun getTrending(ctx: Context): List<Topic> {
        return parse(requestTrendingTopics(ctx))
    }

    private fun requestTrendingTopics(ctx: Context): JSONObject {
        val file = ctx.resources.openRawResource(R.raw.sample_trending_topics)
        val bytes = ByteArray(file.available())
        file.read(bytes)
        val str = String(bytes)

        return JSONObject(str)
    }

    private fun parse(json: JSONObject): List<Topic> {
        return json.getJSONArray("topics").map {obj ->
                    Topic(
                            name = obj.getString("name"),
                            description = obj.getString("description"),
                            postsCount = obj.getInt("posts_count"),
                            followersCount = obj.getInt("followers_count"),
                            imageSrc = obj.getString("image")
                    )
        }
    }

}