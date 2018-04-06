import android.app.Activity
import org.json.JSONArray
import org.json.JSONObject
import ovt.myapplication.App
import ovt.myapplication.AppComponent

val API: String = "https://api.producthunt.com/v1"

fun <T> JSONArray.map(f: (JSONObject) -> T): List<T> {
    val result = mutableListOf<T>()
    for (i in 0 until this.length()) {
        result.add(f.invoke(this.getJSONObject(i)))
    }
    return result
}

val Activity.component: AppComponent
    get() = (application as App).appComponent
