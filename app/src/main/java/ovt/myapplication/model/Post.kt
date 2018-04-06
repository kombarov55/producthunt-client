package ovt.myapplication.model

import org.json.JSONObject

/**
 * Created by nikolay on 06/04/2018.
 */
data class Post(
        val name: String,
        val description: String,
        val upvoteCount: Int,
        val thumbnailSrc: String,
        val redirectUrl: String
) {

    constructor(obj: JSONObject) : this(
            name = obj.getString("name"),
            description = obj.getString("tagline"),
            upvoteCount = obj.getInt("votes_count"),
            thumbnailSrc = obj.getJSONObject("thumbnail").getString("image_url"),
            redirectUrl = obj.getString("redirect_url")
    )

}