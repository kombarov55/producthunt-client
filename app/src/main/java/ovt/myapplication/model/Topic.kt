package ovt.myapplication.model

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
)