package ovt.myapplication.dao.impl

import android.content.Context
import map
import org.json.JSONObject
import ovt.myapplication.R
import ovt.myapplication.dao.TopicDao
import ovt.myapplication.model.Topic
import readToString
import javax.inject.Inject

/**
 * Created by nikolay on 06/04/2018.
 */
class TopicDaoMock @Inject constructor(private val ctx: Context): TopicDao {

    override fun getTrendingTopics(): List<Topic> {
        return parse(requestTrendingTopics())
    }

    private fun requestTrendingTopics(): JSONObject {
        val str = ctx.resources.openRawResource(R.raw.sample_trending_topics).readToString()
        return JSONObject(str)
    }

    private fun parse(json: JSONObject): List<Topic> {
        return json
                .getJSONArray("topics")
                .map { jsonObject -> Topic(jsonObject) }
    }

}