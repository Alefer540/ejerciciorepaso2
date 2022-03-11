package com.example.ejerciciorepaso2

import com.google.gson.Gson
import org.springframework.web.bind.annotation.*

@RestController

class PersonajesController(private val personajesRepository: PersonajesRepository) {//le pasamos el mensaje repository

//curl --request POST  --header "Content-type:application/json" --data "{\"name\":\"Pikachu\",\"height\":\"55\",\"mass\":\"12\",\"hair_color\":\"brown\",\"skin_color\":\"yellow\",\"eye_color\":\"blue\",\"birth_year\":\"1999\",\"gender\":\"male\",\"homeworld\":\"https/....\",\"created\":\"2014-12-09T13:50:51.644000Z\",\"edited\":\"2014-12-09T13:50:51.644000Z\",\"url\":\"desconocido\"}" localhost:8083/insertPeople
    @GetMapping("people")
    fun mostrar():List<Personajes>{
        return personajesRepository.findAll()
    }
    @GetMapping("people/{id}")
     fun mostrarporid(@PathVariable id:Int):Personajes{
         val personajito=personajesRepository.getById(id)
        return personajito
    }
    @PostMapping("insertPeople")
    fun introducir(@RequestBody datos:String){
        val gson=Gson()
        val personajes=gson.fromJson(datos,Personajes::class.java)
        personajesRepository.save(personajes)
        println(personajes)
        println(personajesRepository.findAll())
    }


}