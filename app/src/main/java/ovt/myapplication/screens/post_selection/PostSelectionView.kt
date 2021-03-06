package ovt.myapplication.screens.post_selection

import android.content.Intent
import android.os.Bundle
import ovt.myapplication.model.Post
import ovt.myapplication.model.Topic
import rx.Observable

/**
 * Created by nikolay on 10/04/2018.
 */
interface PostSelectionView {

    fun displayTopics(topics: List<Topic>)
    fun displayPosts(posts: List<Post>)
    fun endPullDownProgress()
    fun goToPostViewActivity(extra: Bundle)

    val topicClicked: Observable<Int>
    val postClicked: Observable<Int>
    val pulledDownToRefresh: Observable<Unit?>

}