package jjmypro.com.github.kotlinyoutubelbta

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.video_row.view.*


// modifying MainAdapter so it can hold a reference of our HomeFeed
// this is done by adding a constructor to MainAdapter that takes in the properties of
// val homeFeed: HomeFeed
class MainAdapter(val homeFeed: HomeFeed): RecyclerView.Adapter<CustomViewHolder>() {

    val videoTitles = listOf("First title", "Second", "3rd", "More titles...yeah")

    // numberOfItems
    override fun getItemCount(): Int {
        return homeFeed.videos.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // how do we even create a view
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
 //       val videoTitle = videoTitles.get(position)
        val video = homeFeed.videos.get(position)
        holder.view.textView_video_title?.text = video.name
    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}