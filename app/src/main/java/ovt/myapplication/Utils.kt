import android.app.Activity
import android.graphics.*
import android.view.View
import android.view.animation.Transformation
import android.widget.AdapterView
import org.json.JSONArray
import org.json.JSONObject
import ovt.myapplication.App
import ovt.myapplication.config.AppComponent
import java.io.InputStream
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth



val API: String = "https://api.producthunt.com/v1"
val accessToken: String = "591f99547f569b05ba7d8777e2e0824eea16c440292cce1f8dfb3952cc9937ff"

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

fun spaceDelimitedtoSnakeCase(s: String): String =
        s.map { c ->
            if (c == ' ')
                '_' else
                c
        }.joinToString("")

fun InputStream.readToString(): String {
    val bytes = ByteArray(available())
    read(bytes)
    return String(bytes)
}


val circleTransform = object : com.squareup.picasso.Transformation {
    override fun key(): String = "circle"

    override fun transform(source: Bitmap): Bitmap {
        val size = Math.min(source.getWidth(), source.getHeight())

        val x = (source.getWidth() - size) / 2
        val y = (source.getHeight() - size) / 2

        val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
        if (squaredBitmap != source) {
            source.recycle()
        }

        val bitmap = Bitmap.createBitmap(size, size, source.getConfig())

        val canvas = Canvas(bitmap)
        val paint = Paint()
        val shader = BitmapShader(squaredBitmap,
                Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.setShader(shader)
        paint.setAntiAlias(true)

        val r = size / 2f
        canvas.drawCircle(r, r, r, paint)

        squaredBitmap.recycle()
        return bitmap
    }
}