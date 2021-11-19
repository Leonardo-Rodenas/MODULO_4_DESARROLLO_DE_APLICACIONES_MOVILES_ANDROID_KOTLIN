package com.example.ejemploretrofit.cliente

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ejemploretrofit.FileReader
import com.google.common.truth.Truth
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MascotaClientTest {

    private var server = MockWebServer()
    private val body = FileReader.lectorJson("akita.json")

    @Before
    fun setUp() {
        server.start(8080)
        server.enqueue(MockResponse().setResponseCode(200).setBody(body))
        server.url("test/akita/images")
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun test_apiSuccess()
    {
        //http://localhost:8080/test/akita/images
        val call = MascotaClient.getCliente("http://localhost:8080/test/").getMascotas("akita")

        var mascota = call.execute().body()

        Truth.assertThat(mascota!!.message[0].toString()).isEqualTo("https://images.dog.ceo/breeds/akita/Akita_Inu_dog.jpg")

    }
}