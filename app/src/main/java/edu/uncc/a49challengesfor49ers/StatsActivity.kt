package edu.uncc.a49challengesfor49ers

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.*
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import edu.uncc.a49challengesfor49ers.Models.User
import kotlinx.android.synthetic.main.activity_stats.*
import com.hendraanggrian.pikasso.picasso
import kotlinx.android.synthetic.main.user_details.view.*


class StatsActivity : BaseActivity() {

    private var listUsers = ArrayList<User>()
    var currentUser : FirebaseUser? = null
    var statsAdapter : StatsAdapter? = null
    var sortedUsers = ArrayList<User>()
    private var mPopupWindow: PopupWindow? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentUser = getFirebaseAuth()!!.currentUser

        if(currentUser == null){
            finish()
            return
        }
        setContentView(R.layout.activity_stats)

        title = "Leadership Board"

        statsAdapter = StatsAdapter(this, sortedUsers)
        lvStats.adapter = statsAdapter

        lvStats.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->

            openWindow(sortedUsers.get(position))

        }
    }

    fun openWindow(user : User){

        val inflater = applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val customView = inflater.inflate(R.layout.user_details, null)

        mPopupWindow = PopupWindow(
                customView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        if(Build.VERSION.SDK_INT>=21){
            mPopupWindow!!.setElevation(5.0f);
        }

        val closeButton = customView.btnClose

        if(!user!!.photo.isNullOrEmpty())
            picasso.load(user!!.photo!!).into(customView.img_ProfilePic)

        customView!!.txtName.text = user.firstName +" "+user.lastName
        customView!!.txtDescription.text = user.description
        customView!!.txtScore.text = user.score.toString()

        closeButton.setOnClickListener {
            mPopupWindow!!.dismiss();
        }

        mPopupWindow!!.showAtLocation(root, Gravity.CENTER,0,0);


    }


    override fun onResume() {
        super.onResume()

        if (currentUser != null) {

          getDatabaseReference().child("Users").addValueEventListener(object : ValueEventListener{
              override fun onDataChange(snapshot: DataSnapshot) {

                  for (postSnapshot in snapshot.getChildren()) {
                      val user = postSnapshot.getValue(User::class.java)
                      listUsers.add(user!!)
                  }

                  sortedUsers.clear()
                  sortedUsers.addAll( listUsers.sortedByDescending { it.score })


                  statsAdapter!!.notifyDataSetChanged()
              }

              override fun onCancelled(error: DatabaseError) {}
          })

        } else {
            Toast.makeText(this@StatsActivity, "Something Went Wrong", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_stats, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.home -> {
                finish()
                val intent = Intent(this, HomeActivity::class.java).apply {
                }
                startActivity(intent)
                return true
            }
            R.id.logout -> {
                getFirebaseAuth().signOut()
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
