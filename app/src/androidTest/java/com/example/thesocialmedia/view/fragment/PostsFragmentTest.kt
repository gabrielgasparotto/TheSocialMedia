package com.example.thesocialmedia.view.fragment

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.thesocialmedia.R
import com.example.thesocialmedia.api.call.PostsCall
import com.example.thesocialmedia.api.service.PostsService
import com.example.thesocialmedia.view.mock.MockPosts
import net.vidageek.mirror.dsl.Mirror
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.io.IOException

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.example.thesocialmedia.features.home.HomeActivity

@RunWith(AndroidJUnit4::class)
class PostsFragmentTest {

    private lateinit var postsServer: MockWebServer

    @Rule @JvmField
    var activityTestRule = ActivityTestRule(
        HomeActivity::class.java,
        false,
        false
    )

    @Before
    @Throws(Exception::class)
    fun setUp() {
        postsServer = MockWebServer()
        postsServer.start()
        setupServerUrl()
    }

    @Test
    fun quandoResultaComSucesso_mostraListaDePosts() {
        postsServer.enqueue(MockResponse().setResponseCode(200).setBody(MockPosts.SUCCESS))
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.recyclerPosts)).check(matches(isDisplayed()))
    }

    @Test
    fun quandoResultaComErro_mostrarTratamentoDeErro() {
        postsServer.enqueue(MockResponse().setResponseCode(400).setBody(MockPosts.ERROR))
        activityTestRule.launchActivity(Intent())
        //Mostrar tratamento de erro
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        postsServer.shutdown()
    }

    private fun setupServerUrl() {
        val url = postsServer.url("/").toString()

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val usersApi = PostsCall

        val postsService = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(PostsService::class.java)

        setField(usersApi, "postsService", postsService)
    }

    private fun setField(target: Any, fieldName: String, value: Any) {
        Mirror()
            .on(target)
            .set()
            .field(fieldName)
            .withValue(value)
    }
}
