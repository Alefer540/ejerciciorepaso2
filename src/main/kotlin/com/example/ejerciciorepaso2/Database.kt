package com.example.ejerciciorepaso2


import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.*

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.IOException
@Configuration
class Database {
    @Bean
    fun initDatabase(personajesRepository: PersonajesRepository): CommandLineRunner {
        return CommandLineRunner {

            val lista = todosPersonajes()
            lista.forEach {
                println("println en bucle for each"+it)
                personajesRepository.save(it)
            }
            println("print personajeRepoository"+personajesRepository.findAll())
        }
    }
    fun todosPersonajes():List<Personajes> {

        var lista = listOf<Personajes>()

        val client = OkHttpClient()

        val request = Request.Builder()
        request.url("https://swapi.dev/api/people")


        val call = client.newCall(request.build())

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e.toString())
                runBlocking(Dispatchers.Main) {


                }

            }

            override fun onResponse(call: Call, response: Response) {
                println(response.toString())
                response.body?.let { responseBody ->

                    val body = responseBody.string()
                    val gson= Gson()
                    val personajeResponse = gson.fromJson(body,PersonajesResponse::class.java)
                    lista = personajeResponse.results
                    println("resultado del request"+lista)
                }
            }
        })
        println("resultado después de request "+lista)
        return lista
    }
}