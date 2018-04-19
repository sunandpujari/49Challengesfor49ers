package edu.uncc.a49challengesfor49ers

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import edu.uncc.a49challengesfor49ers.Models.Question
import edu.uncc.a49challengesfor49ers.Models.User
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*
import kotlin.collections.ArrayList
import com.hendraanggrian.pikasso.picasso
import android.graphics.Color
import android.text.Html
import android.view.*
import com.hendraanggrian.pikasso.circle
import kotlinx.android.synthetic.main.actionbar_layout.view.*


class HomeActivity : BaseActivity() {

    var userRef : DatabaseReference? = null
    var currentUser : FirebaseUser? = null
    var userProfile : User? = null
    var listQuestions = ArrayList<Question>()
    var listAvailableQuestions = ArrayList<Int>()
    val defaultURL = "https://firebasestorage.googleapis.com/v0/b/challenges-for-49ers.appspot.com/o/images%2FNoImageAvailable.jpg?alt=media&token=9362f7d6-88ac-4b74-996d-1298e7ffd1a6"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentUser = getFirebaseAuth()!!.currentUser

        if(currentUser == null){
            finish()
            return
        }
        setContentView(R.layout.activity_home)

        val userId = getFirebaseAuth().currentUser!!.uid
        userRef = getDatabaseReference().child("Users").child(userId)
    }


    override fun onResume() {
        super.onResume()

        if (currentUser != null) {

            userRef!!.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    userProfile = dataSnapshot.getValue<User>(User::class.java)
                    loadActionBar()
                    loadQuestions()
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })


        } else {
            Toast.makeText(this@HomeActivity, "Something Went Wrong", Toast.LENGTH_LONG).show()
            finish()
        }
    }


    fun loadActionBar(){

        val actionBar = supportActionBar

        val actionBarLayout = getLayoutInflater().inflate(R.layout.actionbar_layout, null)

        actionBar!!.setDisplayShowTitleEnabled(false);
        actionBar!!.setDisplayShowCustomEnabled(true);
        actionBar!!.setDisplayHomeAsUpEnabled(false);
        actionBar!!.setCustomView(actionBarLayout);

        actionBarLayout!!.tvName.text = userProfile!!.firstName!! + " " + userProfile!!.lastName!!

        if(!userProfile!!.photo.isNullOrEmpty())
            picasso.load(userProfile!!.photo!!).circle().into(actionBarLayout.imgProfilePic)
        else
           actionBarLayout.imgProfilePic.setImageResource(R.mipmap.ic_logo_49er_launcher_round)
    }

    fun loadQuestions(){

        getDatabaseReference().child("Questions").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                for (postSnapshot in snapshot.getChildren()) {
                    val question = postSnapshot.getValue(Question::class.java)
                    listQuestions.add(question!!)
                }

                loadAvailableQuestions()
                buildUI()
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun loadAvailableQuestions(){
        listAvailableQuestions.clear()

        for(question in listQuestions){
            if(userProfile!!.completedChallenges == null ||  !userProfile!!.completedChallenges!!.contains(question.id)){
                listAvailableQuestions.add(question.id!!)
            }
        }
    }


    private fun buildUI() {


        txtScore.text = Html.fromHtml("<p>Total Score:<span style=\"font-weight:bold;font-size: 24sp;\">"+ userProfile!!.score!!.toString()+ "</span></p>")


        if(userProfile!!.completedChallenges != null && userProfile!!.completedChallenges!!.count() == 49){

            // Completed all
            val tv_dynamic = TextView(this)
            tv_dynamic.textSize = 20f
            tv_dynamic.text = "All Tasks Done"
            // add TextView to LinearLayout
            container.addView(tv_dynamic)
        }
        else if(userProfile!!.currentChallenge!! != -1){
            // ongoing challenge

            loadCurrentChallenge()

        }
        else{
            getNewChallenge()

        }

    }

    fun getNewChallenge(){

        val random = Random()
        val qtn = random.nextInt(listAvailableQuestions.size)

        loadUI(false,qtn)
    }

    fun loadCurrentChallenge(){

        loadUI(true,userProfile!!.currentChallenge!!)
    }

    fun loadUI(accepted : Boolean, question: Int){


        container.removeAllViews()

        var currentQuestion = listQuestions[question]

        val tv_Question = TextView(this)
        tv_Question.textSize = 20f
        tv_Question.setTextColor(Color.BLACK)
        tv_Question.text = currentQuestion.questionText +" (" + currentQuestion.score.toString() +" points)"
        container.addView(tv_Question)

        val tv_Description = TextView(this)
        tv_Description.textSize = 15f
        tv_Description.setPadding(0,20,0,20)
        tv_Description.text = currentQuestion.description
        container.addView(tv_Description)


        val imgQuestion = ImageView(this)
        container.addView(imgQuestion)
        imgQuestion.layoutParams.height = 600
        imgQuestion.setPadding(0,0,0,100)
        imgQuestion.scaleType = ImageView.ScaleType.FIT_CENTER


        picasso.load(if (!currentQuestion.imageURL.isNullOrEmpty()) currentQuestion!!.imageURL!! else defaultURL)
                    .into(imgQuestion)


        val ll_Buttons = LinearLayout(this)
        ll_Buttons.orientation = LinearLayout.HORIZONTAL
        ll_Buttons.layoutParams = TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT, 2f)

        val btnAcceptChallenge = Button(this)
        btnAcceptChallenge.text = if(accepted) "Completed" else "Accept"
        btnAcceptChallenge.gravity = Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL
        btnAcceptChallenge.background = getDrawable(R.drawable.button)
        btnAcceptChallenge.setTextColor(Color.WHITE)
        var params1 = TableRow.LayoutParams(0,ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
        params1.setMargins(20,0,20,0)
        btnAcceptChallenge.layoutParams = params1
        btnAcceptChallenge.setOnClickListener{

            if(accepted){

                userProfile!!.score = userProfile!!.score!! + currentQuestion!!.score!!
                if(userProfile!!.completedChallenges == null)
                    userProfile!!.completedChallenges = ArrayList()

                userProfile!!.completedChallenges!!.add(userProfile!!.currentChallenge!!)

                userProfile!!.currentChallenge = -1

                val currentUserDb = getDatabaseReference().child("Users").child(userProfile!!.id)
                currentUserDb.setValue(userProfile)

            }
            else {

                userProfile!!.currentChallenge = question

                val currentUserDb = getDatabaseReference().child("Users").child(userProfile!!.id)
                currentUserDb.setValue(userProfile)
            }

        }
        ll_Buttons.addView(btnAcceptChallenge)

        val btnTryOther = Button(this)
        btnTryOther.text = "Try Other"
        btnTryOther.gravity = Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL
        btnTryOther.setTextColor(Color.WHITE)
        btnTryOther.background = getDrawable(R.drawable.button)
        var params2 = TableRow.LayoutParams(0,ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
        params2.setMargins(20,0,20,0)
        btnTryOther.layoutParams = params2
        btnTryOther.setOnClickListener {
            getNewChallenge()
        }
        ll_Buttons.addView(btnTryOther)


        container.addView(ll_Buttons)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.stats -> {
                val intent = Intent(this, StatsActivity::class.java).apply {
                }
                startActivity(intent)
                finish()
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




