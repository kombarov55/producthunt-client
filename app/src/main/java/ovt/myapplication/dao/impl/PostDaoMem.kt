package ovt.myapplication.dao.impl

import loadJson
import map
import org.json.JSONObject
import ovt.myapplication.R
import ovt.myapplication.dao.PostDao
import ovt.myapplication.model.Post
import javax.inject.Inject

/**
 * Created by nikolay on 06/04/2018.
 */
class PostDaoMem @Inject constructor() : PostDao {

    @Inject
    lateinit var API: String

//    @Inject
//    lateinit var accessToken: String

    override fun getByTopic(topic: String): List<Post> {
        return parse(requestByTopic(topic))
    }

    private fun requestByTopic(topic: String): JSONObject {
        return loadJson(R.raw.sample_tech_posts)
    }

    private fun parse(obj: JSONObject): List<Post> {
        return obj
                .getJSONArray("posts")
                .map { jsonObject -> Post(jsonObject) }
    }

}