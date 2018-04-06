package ovt.myapplication.dao.impl

import loadJson
import map
import org.json.JSONObject
import ovt.myapplication.R
import ovt.myapplication.model.Topic

/**
 * Created by nikolay on 06/04/2018.
 */
class TopicDaoMem {

    fun getTrending(): List<Topic> {
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