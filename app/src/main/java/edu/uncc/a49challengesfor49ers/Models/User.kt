package edu.uncc.a49challengesfor49ers.Models

/**
 * Created by sunand on 4/11/18.
 */
class User {

    var firstName: String? = null
    var lastName: String? = null
    var description: String? = null
    var email: String? = null
    var photo: String? = null
    var gender: String? = null
    var score: Int? = 0
    var id: String? = null
    var currentChallenge: Int? = null
    var completedChallenges: ArrayList<Int>? = null

    constructor() {
    }

    constructor(_firstName: String?,
                _lastName: String?,
                _description: String?,
                _email: String?,
                _photo: String?,
                _gender: String?,
                _score: Int?,
                _id: String?) {

        this.firstName = _firstName
        this.lastName = _lastName
        this.description = _description
        this.email = _email
        this.photo = _photo
        this.gender= _gender
        this.score = _score
        this.id = _id

        this.completedChallenges = ArrayList<Int>()
        this.currentChallenge = -1
    }

}