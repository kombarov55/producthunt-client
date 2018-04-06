import org.json.JSONArray
import org.json.JSONObject

val API = "https://api.producthunt.com/v1"
val accessToken = "591f99547f569b05ba7d8777e2e0824eea16c440292cce1f8dfb3952cc9937ff"

fun <T> JSONArray.map(f: (JSONObject) -> T): List<T> {
    val result = mutableListOf<T>()
    for (i in 0 until this.length()) {
        result.add(f.invoke(this.getJSONObject(i)))
    }
    return result
}
