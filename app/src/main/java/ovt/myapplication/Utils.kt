import android.app.Activity
import android.graphics.*
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import org.json.JSONArray
import org.json.JSONObject
import ovt.myapplication.App
import ovt.myapplication.config.AppComponent
import java.io.InputStream
import android.widget.Spinner


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

fun Spinner.setOnItemSelectedListener(f: (Int) -> Unit) {
    onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) { }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, i: Int, id: Long) {
            f.invoke(i)
        }
    }
}

fun ListView.setOnItemClickListener(f: (Int) -> Unit) {
    onItemClickListener = object : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, i: Int, id: Long) {
            f.invoke(i)
        }
    }
}