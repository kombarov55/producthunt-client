package ovt.myapplication.dao.impl

import API
import accessToken
import map
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import ovt.myapplication.dao.TopicDao
import ovt.myapplication.model.Topic
import javax.inject.Inject

/**
 * Created by nikolay on 11/04/2018.
 */
class TopicDaoImpl @Inject constructor(
        private val httpClient: OkHttpClient
): TopicDao {

    override fun getTrendingTopics(): List<Topic>
    {
        return JSONObject(
                httpClient.newCall(
                        Request.Builder()
                                .url("$API/topics?search[trending]=true&access_token=$accessToken")
                                .build()
                ).execute().toString()
        )
                .getJSONArray("topics")
                .map { jsonObject -> Topic(jsonObject) }
    }
}