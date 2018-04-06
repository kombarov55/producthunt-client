package ovt.myapplication

import dagger.Component
import ovt.myapplication.dao.impl.PostDaoMem
import javax.inject.Singleton

/**
 * Created by nikolay on 06/04/2018.
 */

@Singleton @Component(modules = [ AppModule::class ])
interface AppComponent {

    fun inject(activity: MainActivity)

}