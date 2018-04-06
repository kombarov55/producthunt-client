package ovt.myapplication.dao

import ovt.myapplication.model.Post

/**
 * Created by nikolay on 06/04/2018.
 */
interface PostDao {

    fun getByTopic(topic: String): List<Post>

}