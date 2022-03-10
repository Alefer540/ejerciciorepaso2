package com.example.ejerciciorepaso2

import com.google.gson.Gson
import org.springframework.web.bind.annotation.*

@RestController

class PersonajesController(private val personajesRepository: PersonajesRepository) {//le pasamos el mensaje repository

//curl --request POST  --header "Content-type:application/json" --data "{\"name\":\"Pikachu\",\"height\":\"55\",\"mass\":\"12\",\"hairColor\":\"brown\",\"skinColor\":\"yellow\",\"eyeColor\":\"blue\",\"birthYear\":\"1999\",\"gender\":\"male\",\"homeworld\":\"https/....\",\"created\":\"2014-12-09T13:50:51.644000Z\",\"edited\":\"2014-12-09T13:50:51.644000Z\",\"url\":\"desconocido\"}" localhost:8083/insertPeople
    @GetMapping("people")
    fun mostrar():List<Personajes>{
        return personajesRepository.findAll()
    }
    @GetMapping("people/{id}")
     fun mostrarporid(@PathVariable id:Int):Personajes{
         val pokemon=personajesRepository.getById(id)
        return pokemon
    }
    @PostMapping("insertPeople")
    fun introducir(@RequestBody datos:String){
        val gson=Gson()
        val personajes=gson.fromJson(datos,Personajes::class.java)
        personajesRepository.save(personajes)
        println(personajesRepository.findAll())
    }


}