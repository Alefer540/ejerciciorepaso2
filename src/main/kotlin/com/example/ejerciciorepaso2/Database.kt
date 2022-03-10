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

             todosPersonajes(personajesRepository)
            println(personajesRepository)

        }
    }
    fun todosPersonajes(personajesRepository: PersonajesRepository) {


        val client = OkHttpClient()

        val request = Request.Builder()
        request.url("http://swapi.dev/api/people")


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
                    personajeResponse.results.forEach{
                        personajesRepository.save(it)
                    }
                    println(personajesRepository.findAll())


                }
            }
        })

    }
}

