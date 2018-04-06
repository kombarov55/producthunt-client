package ovt.myapplication.dao

import ovt.myapplication.model.Topic

/**
 * Created by nikolay on 06/04/2018.
 */
interface TopicDao {

    fun getTrendingTopics(): List<Topic>

}