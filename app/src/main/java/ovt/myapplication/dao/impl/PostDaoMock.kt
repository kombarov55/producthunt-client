package ovt.myapplication.dao.impl

import android.content.Context
import map
import org.json.JSONObject
import ovt.myapplication.R
import ovt.myapplication.dao.PostDao
import ovt.myapplication.model.Post
import javax.inject.Inject

/**
 * Created by nikolay on 06/04/2018.
 */
class PostDaoMock @Inject constructor(private val ctx: Context): PostDao {

    override fun getByTopic(topic: String): List<Post> {
        return parse(requestByTopic(topic))
    }

    private fun requestByTopic(topic: String): JSONObject {
        val file = ctx.resources.openRawResource(R.raw.sample_tech_posts)
        val bytes = ByteArray(file.available())
        file.read(bytes)
        val str = String(bytes)
        return JSONObject(str)
    }

    private fun parse(obj: JSONObject): List<Post> {
        return obj
                .getJSONArray("posts")
                .map { jsonObject -> Post(jsonObject) }
    }

}