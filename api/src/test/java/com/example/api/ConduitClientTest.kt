package com.example.api

import com.example.api.models.Requests.SignUpRequest
import com.example.api.models.Entities.UserCredentials
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.random.Random

class ConduitClientTest {

    private val conduitClient = ConduitClient

    @Test
    fun `GET articles`(){
        runBlocking{
            val articles = conduitClient.public_api.getArticles()
            assertNotNull(articles.body()?.articles)
        }


    }

    @Test
    fun `GET articles by author`(){
        runBlocking{
            val articles = conduitClient.public_api.getArticles(author = "444")
            assertNotNull(articles.body()?.articles)
        }


    }

    @Test
    fun `GET articles by tag`(){
        runBlocking{
            val articles = conduitClient.public_api.getArticles(tag = "dragons")
            assertNotNull(articles.body()?.articles)
        }


    }

    @Test
    fun `POST users - create user`(){
        val userCredentials = UserCredentials(
            "testemail${Random.nextInt(10,9999)}@gmail.com",
            "pass_${Random.nextInt(9999,999999)}",
            "user_${Random.nextInt(99,999)}"
        )

        runBlocking{
            val response = conduitClient.public_api.signUpUser(SignUpRequest(userCredentials))
            assertEquals(userCredentials.username,response.body()?.user?.username)
        }


    }


}