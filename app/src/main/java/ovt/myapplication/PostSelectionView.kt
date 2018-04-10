package ovt.myapplication

import ovt.myapplication.model.Post
import ovt.myapplication.model.Topic
import rx.Observable

/**
 * Created by nikolay on 10/04/2018.
 */
interface PostSelectionView {

    fun displayTopics(topics: List<Topic>)
    fun displayPosts(posts: List<Post>)

    val topicClicked: Observable<Topic>
    val postClicked: Observable<Post>

}