package ovt.myapplication.config

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import ovt.myapplication.dao.PostDao
import ovt.myapplication.dao.TopicDao
import ovt.myapplication.dao.impl.PostDaoImpl
import ovt.myapplication.dao.impl.PostDaoMock
import ovt.myapplication.dao.impl.TopicDaoMock
import javax.inject.Singleton

/**
 * Created by nikolay on 06/04/2018.
 */
@Module
class AppModule(private val ctx: Context) {

    @Provides
    fun providedContext() = ctx

    @Provides
    @Singleton
    fun postDao(ctx: Context, httpClient: OkHttpClient): PostDao {
        return PostDaoMock(ctx)
    }

    @Provides @Singleton
    fun topicDao(ctx: Context): TopicDao {
        return TopicDaoMock(ctx)
    }

    @Provides
    fun okHttpClient(): OkHttpClient = OkHttpClient()

//    @Provides
//    fun accessToken(): String = "591f99547f569b05ba7d8777e2e0824eea16c440292cce1f8dfb3952cc9937ff"

}