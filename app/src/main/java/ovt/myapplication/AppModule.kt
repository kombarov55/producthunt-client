package ovt.myapplication

import dagger.Module
import dagger.Provides

/**
 * Created by nikolay on 06/04/2018.
 */
@Module
class AppModule {

    @Provides
    fun API(): String = "https://api.producthunt.com/v1"

//    @Provides
//    fun accessToken(): String = "591f99547f569b05ba7d8777e2e0824eea16c440292cce1f8dfb3952cc9937ff"

}