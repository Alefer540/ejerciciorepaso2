package com.example.ejerciciorepaso2

import com.google.gson.Gson
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Personajes  (
    val name: String,
    val height: String,
    val mass: String,
    val hairColor: String,
    val skinColor: String,
    val eyeColor: String,
    val birthYear: String,
    val gender: String,
    val homeworld: String,
   // @ElementCollection
    //val films: ArrayList<String>,
    //@ElementCollection
   // val species: ArrayList<String>,
   // @ElementCollection
   // val vehicles: ArrayList<String>,
   // @ElementCollection
   //val starships: ArrayList<String>,
    val created: String,
    val edited: String,
    val url: String
)
{
    @Id
    @GeneratedValue
    var id=0

    override fun toString():String{
        val gson= Gson()
        return gson.toJson(this)
    }
}