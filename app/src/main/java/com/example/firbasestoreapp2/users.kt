package com.example.firbasestoreapp2

class users {
    var name: String? = null
    var pass: String? = null
    var id : String? = null

    constructor(){}

    constructor(id: String?, pass: String?, name: String?) {
        this.id = id
        this.pass = pass
        this.name = name
    }


}