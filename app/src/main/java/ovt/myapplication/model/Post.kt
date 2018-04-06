package ovt.myapplication.model

/**
 * Created by nikolay on 06/04/2018.
 */
data class Post(
        val name: String,
        val description: String,
        val upvoteCount: Int,
        val thumbnailSrc: String
)