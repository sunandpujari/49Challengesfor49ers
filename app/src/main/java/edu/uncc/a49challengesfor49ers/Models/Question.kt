package edu.uncc.a49challengesfor49ers.Models

import android.media.Image

/**
 * Created by sunand on 4/12/18.
 */
class Question {

    var questionText: String? = null
    var imageURL: String? = null
    var description: String? = null
    var score: Int? = 0
    var id: Int? = 0

    constructor(_id:Int?, _questionText: String?, _description: String?, _imageURL: String?, _Score: Int?) {

        this.id = _id
        this.questionText = _questionText
        this.description = _description
        this.imageURL = _imageURL
        this.score = _Score
    }


    constructor() {

    }


    fun getQuestions() : ArrayList<Question>{

        val questions = arrayListOf<Question>()



        return questions

    }

    public fun getQuestionsHardCode() : ArrayList<Question>{

        val questions = arrayListOf<Question>()

        questions.add(Question(0,"Graduate!","We know you want to stay forever, but we gotta let you go.","",0))
        questions.add(Question(1,"Thank a construction worker for all the 8 a.m.s they woke you up for","Even the weekend ones.","",0))
        questions.add(Question(2,"Write a better article than the Niner Times:","Anything you can do, we can do betteeeeer. (p.s. jokes, we love you, fam)","",0))
        questions.add(Question(3,"Engage in an argument with or be denounced by Preacher Gary:","No, Preacher Gary, we’re not lesbians because we have short hair. No, Preacher Gary, we’re not going to hell for wearing shorts.","",0))
        questions.add(Question(4,"Get a parking ticket:","Likely the easiest one on this list. We see you, PATs","",0))
        questions.add(Question(5,"Take a dip in the ponds:","Not recommended, but it’s been done.","",0))
        questions.add(Question(6,"Steal a full set of cutlery and dishes from the dining halls:","You can return them at the end of the semester if you feel real bad.","",0))
        questions.add(Question(7,"Find the secret tunnel between CHHS and COED:","It’s there, you know it’s there. Somewhere…","",0))
        questions.add(Question(8,"Play the old arcade system in Cone:","It’s near the piano and Candy Shop—battle someone on Pacman.","",0))
        questions.add(Question(9,"Watch a movie at the campus theater!:","Just some good ol’ fashioned sober fun!","",0))
        questions.add(Question(10,"Take a nap on campus:","Pods got you.","",0))
        questions.add(Question(11,"Get a snow cone at Pelican’s!:","","",0))
        questions.add(Question(12,"Take safe ride’s full route around campus for funsies:","May or may not be a huge waste of time, but at least you can say you did it!","",0))
        questions.add(Question(13,"Walk from one side of campus to the other without being out of breath:","We’re not even sure if this is possible.","",0))
        questions.add(Question(14,"Learn the UNCC fight song:","Because no one else will.","",0))
        questions.add(Question(15,"Break into your first year dorm and see who’s keeping your old bed warm:","Extra challenging if your building/high rise has been renovated. (Shout out to Sanford 202, I’m coming for you.)","",0))
        questions.add(Question(16,"Sunday…:","It’s the Lord’s day you heathens, take a rest. Go get some coffee and a pastry at Amelie’s, they’re open 24/7!","",0))
        questions.add(Question(17,"Bar Charlotte Saturdays!","Ride the bull and experience the ratchet-ness at least once. You’ll probably regret it, but you’re not a true Niner until you’ve survived.","",0))
        questions.add(Question(18,"Food Truck Fridays!","","",0))
        questions.add(Question(19,"Label-Me Thursdays!","","",0))
        questions.add(Question(20,"Level Wednesdays at Suite!","","",0))
        questions.add(Question(21,"Club Life Tuesdays at Whisky River!","","",0))
        questions.add(Question(22,"Go on a week long bender: Mondays at Flying Saucer’s for pint night!:","Extra point if you get a pic with Lauren, one of our bartenders of the week, or get your name on a ring of honor for tasting 200 beers!","",0))
        questions.add(Question(23,"End up at Cook Out after a long night out:","You’ve done this 1,000 times already, just do it once more. ","",0))
        questions.add(Question(24,"Interrupt a campus tour:","Knock their cups out of their hands in Crown.","",0))
        questions.add(Question(25,"Be on the Snapchat campus story:","Naughty_Norms has got you.","",0))
        questions.add(Question(26,"Hold up your pick-axes at a UNCC basketball game.","They’re not weapons, they’re props!","",0))
        questions.add(Question(27,"Whisper secret messages to your friends in the Star Quad over by Atkins and SAC:","","",0))
        questions.add(Question(28,"Defy all odds, and actually stay on campus for one weekend:","It’s fun (we’ve heard)! Give it a try (or don’t)! ","",0))
        questions.add(Question(29,"Knock on Chancellor Dubois’s door:","It’s so close… so proper and nice… it’s asking for a ding-dong-ditch.","",0))
        questions.add(Question(30,"Fight someone for a t-shirt at a football game:","We’re probably not winning, you might as well entertain yourself somehow.","",0))
        questions.add(Question(31,"Visit the Belk Tower:","Muahaha, you will never complete our bucket list now!","",0))
        questions.add(Question(32,"Bum a swipe into Crown Commons:","For old times sake!","",0))
        questions.add(Question(33,"Play the piano in the Union, poorly:","Show everyone who took lessons from 5th-8th grade and has slowly lost any and all musical talent since then! Then refuse to stop playing. ","",0))
        questions.add(Question(34,"Climb the rock wall in SAC:","No excuse, it’s free the first time once you sign your life over!","",0))
        questions.add(Question(35,"Selfie with Norm:","‘nuff said.","",0))
        questions.add(Question(36,"Go to a concert at PNC:","We are barely a mile from PNC Music Pavilion. If you didn’t go to at least one concert, you’re not going to graduate due to our immense disappointment in you. Okay, okay, fair enough; we’ll let it count if you left your dorm room/apartment windows open and overheard a concert.","",0))
        questions.add(Question(37,"Ride the party bus to Label!:","Get wasted and save money on that Uber.","",0))
        questions.add(Question(38,"Home at least 30 inchworms on you at one time:","Nothing says UNCC like our infestation of inchworms.","",0))
        questions.add(Question(39,"Explore the gardens:","Extra point if you pay respects to Bonnie Cone’s grave.","",0))
        questions.add(Question(40,"Chill in the community garden:","You probably didn’t find out about this garden until just now (unless you’re the 2% in architecture or visual arts), but it’s not too late! The giant hammocks are calling you!","",0))
        questions.add(Question(41,"Pull an all nighter in Atkins:","Break out the comfy clothes, down some Peet’s coffee, and choose the floor of your choice.","",0))
        questions.add(Question(42,"Attend an athletic event:","Please go to at least one, our game turn-out is so sad. Maybe even go to a women team’s game? Is that asking too much? ","",0))
        questions.add(Question(43,"Become one with the Self-Made Man Statue:","Drop to your knees and cry before your final exam. It’ll either bring you good luck or  looks of pity.","",0))
        questions.add(Question(44,"Make fun of the freshmen wearing lanyards:","You’ve probably been doing this since sophomore year. It’s a rite of passage. ","",0))
        questions.add(Question(45,"Sprint up the stairs to by SAC:","Extra points if you play the Rocky theme song.","",0))
        questions.add(Question(46,"Pet a UNCC goose:","We cannot guarantee your life.","",0))
        questions.add(Question(47,"Visit every building on campus:","Seriously, we’ve never even ventured to the other side of campus… we need to know what’s in EPIC. What kick-ass amenities are they giving the engineers? ","",0))
        questions.add(Question(48,"Eat at every dining location on campus:","Previously titled, “Get severe diarrhea and other digestion problems from eating at Crown and SoVi.”","",0))

        return questions

    }

}