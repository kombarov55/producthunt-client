package ovt.myapplication

import dagger.Binds
import dagger.Module
import dagger.Provides
import ovt.myapplication.dao.PostDao
import ovt.myapplication.dao.TopicDao
import ovt.myapplication.dao.impl.PostDaoMem
import ovt.myapplication.dao.impl.TopicDaoMem
import javax.inject.Singleton

/**
 * Created by nikolay on 06/04/2018.
 */
@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun postDao(postDao: PostDaoMem): PostDao

    @Binds @Singleton
    abstract fun topicDao(topicDao: TopicDaoMem): TopicDao

//    @Provides
//    fun accessToken(): String = "591f99547f569b05ba7d8777e2e0824eea16c440292cce1f8dfb3952cc9937ff"

}