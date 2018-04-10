package ovt.myapplication.dao.impl

import API
import accessToken
import map
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import ovt.myapplication.dao.PostDao
import ovt.myapplication.model.Post
import javax.inject.Inject

/**
 * Created by nikolay on 08/04/2018.
 */
class PostDaoImpl @Inject constructor(
        private val httpClient: OkHttpClient
): PostDao {

    override fun getByTopic(topic: String): List<Post> {

        val page = 1
        val perPage = 10

        return JSONObject(
                httpClient.newCall(Request.Builder()
                        .url("$API/posts/all?search[$topic]=1&page=$page&per_page=$perPage&access_token=$accessToken")
                        .build())
                        .execute().toString())
                .getJSONArray("posts")
                .map { jsonObject -> Post(jsonObject) }


    }


}