package jjmypro.com.github.kotlinyoutubelbta

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        recyclerView_main.setBackgroundColor(Color.BLUE)

        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclerView_main.adapter = MainAdapter()

        fetchJson()
    }

    fun fetchJson() {
        println("Attempting to Fetch JSON")

        val url = "https://api.letsbuildthatapp.com/youtube/home_feed"

        // constructing a request object, needed when making url request using the client below
        val request = Request.Builder().url(url).build()

        // constructing a client with a constant client
        val client = OkHttpClient()
        // making a url request with the client to get data from this url end point
        // kind of like a RESTapi service
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                println(body)
         // the moment we get the body we construct the gsonbuilder class and then gson performs all
         // of the work to get the json string from the body into our HomeFeed class.

         // constructing HomeFeed object from the body string using gson builder
                val gson = GsonBuilder().create()

                val homeFeed = gson.fromJson(body, HomeFeed::class.java)
            }

            override fun onFailure(call: Call?, e: IOException) {
                println("Failed to execute request")
            }


        })
    }
}

//id: 1,
//name: "Instagram Firebase - Learn How to Create Users, Follow, and Send Push Notifications",
//link: "https://www.letsbuildthatapp.com/course/instagram-firebase",
//imageUrl: "https://letsbuildthatapp-videos.s3-us-west-2.amazonaws.com/04782e30-d72a-4917-9d7a-c862226e0a93",
//numberOfViews: 20000,
//channel: {
//    name: "Lets Build That App",
//    profileImageUrl: "https://letsbuildthatapp-videos.s3-us-west-2.amazonaws.com/dda5bc77-327f-4944-8f51-ba4f3651ffdf",
//    numberOfSubscribers: 100000

// HomeFeed is going to contain a list of the video objects
class HomeFeed(val videos: List<Video>)

class Video(val id: Int, val name: String, val link: String, val imageUrl: String, val numberOfViews: Int, val channel: Channel)
// to capture the channel data which is a class inside the video class we create another model object
// called Channel holding the rest of the properties, numberOfSubscribers left out in this example
class Channel(val name: String, val profileImageUrl: String)