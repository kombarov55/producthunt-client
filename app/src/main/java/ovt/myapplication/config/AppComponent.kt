package ovt.myapplication.config

import dagger.Component
import ovt.myapplication.PostSelectionActivity
import javax.inject.Singleton

/**
 * Created by nikolay on 06/04/2018.
 */

@Singleton @Component(modules = [ AppModule::class ])
interface AppComponent {

    fun inject(activity: PostSelectionActivity)

}