package ovt.myapplication

import android.app.Application

/**
 * Created by nikolay on 06/04/2018.
 */
class App: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(applicationContext))
                .build()
    }

}