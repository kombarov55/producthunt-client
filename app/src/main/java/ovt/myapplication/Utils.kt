import android.app.Activity
import android.view.View
import android.widget.AdapterView
import org.json.JSONArray
import org.json.JSONObject
import ovt.myapplication.App
import ovt.myapplication.config.AppComponent

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

class OnItemSelectedListenerAdapter(
        private val onItemSelectedCallback: ((AdapterView<*>?, View?, Int, Long) -> Unit)? = null,
        private val onNothingSelectedCallback: ((AdapterView<*>?) -> Unit)? = null
): AdapterView.OnItemSelectedListener {

    override fun onNothingSelected(parent: AdapterView<*>?) {
        onNothingSelectedCallback?.invoke(parent)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, i: Int, id: Long) {
        onItemSelectedCallback?.invoke(parent, view, i, id)
    }
}