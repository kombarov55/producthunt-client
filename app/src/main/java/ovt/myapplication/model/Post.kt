package ovt.myapplication.model

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

/**
 * Created by nikolay on 06/04/2018.
 */
data class Post(
        val name: String,
        val description: String,
        val upvoteCount: Int,
        val thumbnailSrc: String,
        val redirectUrl: String,
        val screenshotSrc: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    constructor(obj: JSONObject) : this(
            name = obj.getString("name"),
            description = obj.getString("tagline"),
            upvoteCount = obj.getInt("votes_count"),
            thumbnailSrc = obj.getJSONObject("thumbnail").getString("image_url"),
            redirectUrl = obj.getString("redirect_url"),
            screenshotSrc = obj.getJSONObject("screenshot_url").getString("850px")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(upvoteCount)
        parcel.writeString(thumbnailSrc)
        parcel.writeString(redirectUrl)
        parcel.writeString(screenshotSrc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Post> {
        override fun createFromParcel(parcel: Parcel): Post {
            return Post(parcel)
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }

        val EXTRA_NAME = "post"
    }
}