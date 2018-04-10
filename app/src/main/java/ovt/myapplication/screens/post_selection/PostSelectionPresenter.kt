package ovt.myapplication.screens.post_selection

import ovt.myapplication.dao.PostDao
import ovt.myapplication.dao.TopicDao
import ovt.myapplication.model.Post
import ovt.myapplication.model.Topic

/**
 * Created by nikolay on 10/04/2018.
 */
class PostSelectionPresenter constructor (
        private val postSelectionView: PostSelectionView,
        private val postDao: PostDao,
        private val topicDao: TopicDao
) {

    private lateinit var topics: List<Topic>
    private lateinit var posts: List<Post>

    private lateinit var selectedTopic: Topic

    fun onCreate() {
        topics = topicDao.getTrendingTopics()
        postSelectionView.displayTopics(topics)

        selectedTopic = topics.first()
        posts = postDao.getByTopic(selectedTopic.name)
        postSelectionView.displayPosts(posts)

        postSelectionView.topicClicked
                .map { i -> topics[i]}
                .doOnNext { topic -> selectedTopic = topic }
                .map {topic -> postDao.getByTopic(topic.name) }
                .subscribe { posts -> postSelectionView.displayPosts(posts) }

        postSelectionView.pulledDownToRefresh
                .map { postDao.getByTopic(selectedTopic.name) }
                .subscribe { posts ->
                    postSelectionView.displayPosts(posts)
                    postSelectionView.endPullDownProgress()
                }


    }

}