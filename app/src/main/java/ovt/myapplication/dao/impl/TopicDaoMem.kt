package ovt.myapplication.dao.impl

import loadJson
import map
import org.json.JSONObject
import ovt.myapplication.R
import ovt.myapplication.dao.TopicDao
import ovt.myapplication.model.Topic
import javax.inject.Inject

/**
 * Created by nikolay on 06/04/2018.
 */
class TopicDaoMem @Inject constructor(): TopicDao {

    override fun getTrendingTopics(): List<Topic> {
        return parse(requestTrendingTopics())
    }

    private fun requestTrendingTopics(): JSONObject {
        return loadJson(R.raw.sample_trending_topics)
    }

    private fun parse(json: JSONObject): List<Topic> {
        return json
                .getJSONArray("topics")
                .map { jsonObject -> Topic(jsonObject) }
    }

}