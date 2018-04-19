package edu.uncc.a49challengesfor49ers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.hendraanggrian.pikasso.picasso
import edu.uncc.a49challengesfor49ers.Models.User

/**
 * Created by sunand on 4/12/18.
 */


class StatsAdapter : BaseAdapter {

    private var usersList = ArrayList<User>()
    private var context: Context? = null

    constructor(context: Context, usersList: ArrayList<User>) : super() {
        this.usersList = usersList
        this.context = context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val vh: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(parent!!.getContext()).inflate(R.layout.user_stats_row, parent, false);
            vh = ViewHolder(view)
            view!!.tag = vh
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }

        vh.tvName.text = usersList[position].firstName + " " + usersList[position].lastName
        vh.tvScore.text = usersList[position].score.toString()

        if(!usersList[position]!!.photo.isNullOrEmpty())
            picasso.load(usersList[position]!!.photo!!).into(vh.imgImage)

        return view
    }

    override fun getItem(position: Int): Any {
        return usersList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return usersList.size
    }
}

private class ViewHolder(view: View?) {
    val tvName: TextView
    val tvScore: TextView
    val imgImage: ImageView

    init {
        this.tvName = view?.findViewById(R.id.txtName)!!
        this.tvScore = view?.findViewById(R.id.txtScore)!!
        this.imgImage = view?.findViewById(R.id.img_ProfilePic)!!
    }
}