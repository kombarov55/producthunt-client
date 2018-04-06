package ovt.myapplication.model

import org.json.JSONObject
import java.util.*

/**
 * Created by nikolay on 06/04/2018.
 */
data class Topic(
        val name: String,
        val description: String,
        val postsCount: Int,
        val followersCount: Int,
        val imageSrc: String
) {
    constructor(obj: JSONObject) : this (
            name = obj.getString("name"),
            description = obj.getString("description"),
            postsCount = obj.getInt("posts_count"),
            followersCount = obj.getInt("followers_count"),
            imageSrc = obj.getString("image")
    )
}